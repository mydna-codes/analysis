package codes.mydna.clients.grpc;

import codes.mydna.lib.Gene;
import codes.mydna.lib.Sequence;
import codes.mydna.lib.grpc.GeneServiceGrpc;
import codes.mydna.lib.grpc.GeneServiceProto;
import codes.mydna.status.Status;
import codes.mydna.utils.TransferEntity;
import com.kumuluz.ee.grpc.client.GrpcChannelConfig;
import com.kumuluz.ee.grpc.client.GrpcChannels;
import com.kumuluz.ee.grpc.client.GrpcClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.net.ssl.SSLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class GeneServiceGrpcClient {

    private final static Logger LOG = Logger.getLogger(GeneServiceGrpcClient.class.getName());

    private GeneServiceGrpc.GeneServiceBlockingStub geneServiceBlockingStub;

    @PostConstruct
    public void init(){
        try {
            GrpcChannels clientPool = GrpcChannels.getInstance();
            GrpcChannelConfig config = clientPool.getGrpcClientConfig("sequence-bank-grpc-client");
            GrpcClient client = new GrpcClient(config);
            geneServiceBlockingStub = GeneServiceGrpc.newBlockingStub(client.getChannel()).withWaitForReady();
            LOG.info("Grpc client " + GeneServiceGrpcClient.class.getSimpleName() + " initialized.");
        } catch (SSLException e) {
            LOG.warning(e.getMessage());
        }
    }

    public List<TransferEntity<Gene>> getMultipleGenes(List<String> ids){

        // If ids are not passed, don't call grpc and return empty list
        if(ids == null || ids.isEmpty()){
            return new ArrayList<>();
        }

        GeneServiceProto.MultipleGenesRequest request = GeneServiceProto.MultipleGenesRequest.newBuilder()
                .addAllId(ids)
                .build();

        GeneServiceProto.MultipleGenesResponse response;
        try {
            response = geneServiceBlockingStub.getMultipleGenes(request);
        }catch (Exception e) {
            LOG.severe("Failed to connect to gRPC client: " + e.getMessage());
            return new ArrayList<>();
        }

        List<TransferEntity<Gene>> genes = new ArrayList<>();
        for(int i = 0; i < response.getGeneCount(); i++){
            genes.add(mapToTransferEntity(response.getGene(i)));
        }

        return genes;
    }

    private TransferEntity<Gene> mapToTransferEntity(GeneServiceProto.Gene protoGene){
        Gene gene = new Gene();
        gene.setId(protoGene.getBaseSequenceInfo().getId());
        gene.setName(protoGene.getBaseSequenceInfo().getName());

        Sequence sequence = new Sequence();
        sequence.setValue(protoGene.getSequence().getValue());
        gene.setSequence(sequence);

        TransferEntity<Gene> entity = new TransferEntity<>();
        entity.setEntity(gene);
        entity.setStatus(Status.valueOf(protoGene.getEntityStatus()));

        return entity;
    }

}
