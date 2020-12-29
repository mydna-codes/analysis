package codes.mydna.services.impl;

import codes.mydna.clients.grpc.DnaServiceGrpcClient;
import codes.mydna.clients.grpc.EnzymeServiceGrpcClient;
import codes.mydna.clients.grpc.GeneServiceGrpcClient;
import codes.mydna.lib.*;
import codes.mydna.lib.enums.Orientation;
import codes.mydna.lib.enums.SequenceType;
import codes.mydna.lib.util.BasePairUtil;
import codes.mydna.services.AnalysisService;
import codes.mydna.status.Status;
import codes.mydna.utils.TransferEntity;
import codes.mydna.validation.Assert;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class AnalysisServiceImpl implements AnalysisService {

    private static final Logger LOG = Logger.getLogger(AnalysisServiceImpl.class.getName());

    @PostConstruct
    private void postConstruct(){
        LOG.info(AnalysisServiceImpl.class.getSimpleName() + " initialized");
    }

    @Inject
    private DnaServiceGrpcClient dnaServiceGrpcClient;

    @Inject
    private EnzymeServiceGrpcClient enzymeServiceGrpcClient;

    @Inject
    private GeneServiceGrpcClient geneServiceGrpcClient;

    @Override
    public AnalysisResponse analyze(AnalysisRequest request) {

        AnalysisResponse response = new AnalysisResponse();

        Assert.fieldNotEmpty(request.getDnaId(), "dnaId");

        TransferEntity<Dna> receivedDna = dnaServiceGrpcClient.getDna(request.getDnaId());
        response.setDnaId(request.getDnaId());
        response.setStatus(receivedDna.getStatus());

        // Dna sequence response validation
        if(receivedDna.getStatus() != Status.OK){
            return response;
        }

        String sequence = receivedDna.getEntity().getSequence().getValue();

        List<TransferEntity<Enzyme>> receivedEnzymes = enzymeServiceGrpcClient.getMultipleEnzymes(request.getEnzymeIds());
        response.setEnzymes(findEnzymes(sequence, receivedEnzymes));

        List<TransferEntity<Gene>> receivedGenes = geneServiceGrpcClient.getMultipleGenes(request.getGeneIds());
        response.setGenes(findGenes(sequence, receivedGenes));

        return response;
    }

    private List<FoundEnzyme> findEnzymes(String sequence, List<TransferEntity<Enzyme>> enzymes){

        List<FoundEnzyme> foundEnzymes = new ArrayList<>();

        if(enzymes == null)
            return foundEnzymes;

        for(TransferEntity<Enzyme> enzyme : enzymes){
            TransferEntity<FoundEnzyme> transferEntity = new TransferEntity<>();
            transferEntity.setStatus(enzyme.getStatus());

            // If enzyme status is not valid, skip searching cuts
            if(transferEntity.getStatus() != Status.OK){
                continue;
            }

            // Get enzyme sequence as a string
            String enzymeSequence = enzyme.getEntity().getSequence().getValue();

            // Find all cut indexes for provided enzyme
            List<Integer> indexes = BasePairUtil.findAll(sequence, enzymeSequence, SequenceType.ENZYME);

            // Create cuts for all found indexes
            List<EnzymeCut> enzymeCuts = new ArrayList<>();
            for(int i : indexes){

                // Calculate real cuts
                EnzymeCut cut = new EnzymeCut();
                cut.setUpperCut(i + enzyme.getEntity().getUpperCut());
                cut.setLowerCut(i + enzyme.getEntity().getLowerCut());
                enzymeCuts.add(cut);
            }

            // Create new searchedEnzyme
            FoundEnzyme foundEnzyme = new FoundEnzyme();
            foundEnzyme.setId(enzyme.getEntity().getId());
            foundEnzyme.setCuts(enzymeCuts);

            // Add enzyme to the list
            foundEnzymes.add(foundEnzyme);
        }
        return foundEnzymes;
    }

    private List<FoundGene> findGenes(String sequence, List<TransferEntity<Gene>> genes){

        List<FoundGene> foundGenes = new ArrayList<>();

        if(genes == null)
            return foundGenes;

        for(TransferEntity<Gene> gene : genes){
            TransferEntity<FoundEnzyme> transferEntity = new TransferEntity<>();
            transferEntity.setStatus(gene.getStatus());

            // If gene status is not valid, skip searching overlaps
            if(transferEntity.getStatus() != Status.OK){
                continue;
            }

            // Get gene sequence as a string
            String geneSequence = gene.getEntity().getSequence().getValue();

            // Find all overlap indexes for provided gene
            List<Integer> indexes = BasePairUtil.findAll(sequence, geneSequence, SequenceType.GENE);

            // Create overlaps for all found indexes
            List<GeneOverlap> geneOverlaps = new ArrayList<>();
            for(int i : indexes){

                // Calculate real cuts
                GeneOverlap overlap = new GeneOverlap();
                overlap.setFromIndex(i);
                overlap.setToIndex(i + geneSequence.length());
                overlap.setOrientation(Orientation.POSITIVE);
                geneOverlaps.add(overlap);
            }

            // Reverse sequence
            String geneReverseSequence = BasePairUtil.reverse(geneSequence);

            // Find all overlap indexes for provided gene for reverseSequence
            indexes = BasePairUtil.findAll(sequence, geneReverseSequence, SequenceType.GENE);

            // Add overlaps for all found indexes for reverse sequence
            for(int i : indexes){

                // Calculate real cuts
                GeneOverlap overlap = new GeneOverlap();
                overlap.setFromIndex(i + geneSequence.length()); // From index is higher for reverse sequences
                overlap.setToIndex(i);
                overlap.setOrientation(Orientation.NEGATIVE);
                geneOverlaps.add(overlap);
            }

            // Create new searchedEnzyme
            FoundGene foundGene = new FoundGene();
            foundGene.setId(gene.getEntity().getId());
            foundGene.setOverlaps(geneOverlaps);

            // Add gene to the list
            foundGenes.add(foundGene);
        }
        return foundGenes;
    }



}
