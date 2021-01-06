package codes.mydna.services.impl;

import codes.mydna.clients.grpc.DnaServiceGrpcClient;
import codes.mydna.clients.grpc.EnzymeServiceGrpcClient;
import codes.mydna.clients.grpc.GeneServiceGrpcClient;
import codes.mydna.lib.*;
import codes.mydna.lib.enums.Orientation;
import codes.mydna.lib.enums.SequenceType;
import codes.mydna.lib.util.BasePairUtil;
import codes.mydna.services.AnalysisResultService;
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

    @Inject
    private AnalysisResultService analysisResultService;

    @Override
    public AnalysisResult analyze(AnalysisRequest request) {

        AnalysisResult response = new AnalysisResult();

        Assert.fieldNotEmpty(request.getDnaId(), "dnaId");

        TransferEntity<Dna> receivedDna = dnaServiceGrpcClient.getDna(request.getDnaId());
        response.setStatus(receivedDna.getStatus());

        // Dna sequence response validation
        if(receivedDna.getStatus() != Status.OK){
            return response;
        }

        response.setDna(receivedDna.getEntity());
        String sequence = receivedDna.getEntity().getSequence().getValue();

        List<Enzyme> receivedEnzymes = enzymeServiceGrpcClient.getMultipleEnzymes(request.getEnzymeIds());
        response.setEnzymes(findEnzymes(sequence, receivedEnzymes));

        List<Gene> receivedGenes = geneServiceGrpcClient.getMultipleGenes(request.getGeneIds());
        response.setGenes(findGenes(sequence, receivedGenes));

        analysisResultService.insertAnalysisResult(response);

        return response;
    }

    private List<FoundEnzyme> findEnzymes(String sequence, List<Enzyme> enzymes){

        List<FoundEnzyme> foundEnzymes = new ArrayList<>();

        if(enzymes == null)
            return foundEnzymes;

        for(Enzyme enzyme : enzymes){

            // Get enzyme sequence as a string
            String enzymeSequence = enzyme.getSequence().getValue();

            // Find all cut indexes for provided enzyme
            List<Integer> indexes = BasePairUtil.findAll(sequence, enzymeSequence, SequenceType.ENZYME);

            // Create cuts for all found indexes
            List<EnzymeCut> enzymeCuts = new ArrayList<>();
            for(int i : indexes){

                // Calculate real cuts
                EnzymeCut cut = new EnzymeCut();
                cut.setUpperCut(i + enzyme.getUpperCut());
                cut.setLowerCut(i + enzyme.getLowerCut());
                enzymeCuts.add(cut);
            }

            // Skip adding redundant genes
            if (enzymeCuts.isEmpty())
                continue;

            // Create new searchedEnzyme
            FoundEnzyme foundEnzyme = new FoundEnzyme();
            foundEnzyme.setEnzyme(enzyme);
            foundEnzyme.setCuts(enzymeCuts);

            // Add enzyme to the list
            foundEnzymes.add(foundEnzyme);
        }
        return foundEnzymes;
    }

    private List<FoundGene> findGenes(String sequence, List<Gene> genes){

        List<FoundGene> foundGenes = new ArrayList<>();

        if(genes == null)
            return foundGenes;

        for(Gene gene : genes){

            // Get gene sequence as a string
            String geneSequence = gene.getSequence().getValue();

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

            // Skip adding redundant genes
            if (geneOverlaps.isEmpty())
                continue;

            // Create new searchedEnzyme
            FoundGene foundGene = new FoundGene();
            foundGene.setGene(gene);
            foundGene.setOverlaps(geneOverlaps);

            // Add gene to the list
            foundGenes.add(foundGene);
        }
        return foundGenes;
    }



}
