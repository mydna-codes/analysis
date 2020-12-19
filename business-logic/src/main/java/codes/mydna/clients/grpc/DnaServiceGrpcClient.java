package codes.mydna.clients.grpc;

import codes.mydna.lib.Dna;
import codes.mydna.lib.Sequence;
import codes.mydna.lib.grpc.DnaServiceGrpc;
import codes.mydna.lib.grpc.DnaServiceProto;
import codes.mydna.status.Status;
import codes.mydna.utils.TransferEntity;
import com.kumuluz.ee.grpc.client.GrpcChannelConfig;
import com.kumuluz.ee.grpc.client.GrpcChannels;
import com.kumuluz.ee.grpc.client.GrpcClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.net.ssl.SSLException;
import java.util.logging.Logger;

@ApplicationScoped
public class DnaServiceGrpcClient {

    private final static Logger LOG = Logger.getLogger(DnaServiceGrpcClient.class.getName());

    private DnaServiceGrpc.DnaServiceBlockingStub dnaServiceBlockingStub;

    @PostConstruct
    public void init(){
        try {
            GrpcChannels clientPool = GrpcChannels.getInstance();
            GrpcChannelConfig config = clientPool.getGrpcClientConfig("sequence-bank-grpc-client");
            GrpcClient client = new GrpcClient(config);
            dnaServiceBlockingStub = DnaServiceGrpc.newBlockingStub(client.getChannel()).withWaitForReady();
            LOG.info("Grpc client " + DnaServiceGrpcClient.class.getSimpleName() + " initialized.");
        } catch (SSLException e) {
            LOG.warning(e.getMessage());
        }
    }

    public TransferEntity<Dna> getDna(String id){

        DnaServiceProto.DnaRequest request = DnaServiceProto.DnaRequest.newBuilder()
                .setId(id)
                .build();

        DnaServiceProto.DnaResponse response;
        try {
            response = dnaServiceBlockingStub.getDna(request);
        } catch (Exception e) {
            LOG.severe("Failed to connect to gRPC client: " + e.getMessage());
            return null;
        }

        TransferEntity<Dna> entity = new TransferEntity<>();
        entity.setStatus(Status.valueOf(response.getDna().getEntityStatus()));
        if(response.hasDna()) {

            Dna dna = new Dna();
            dna.setId(response.getDna().getBaseSequenceInfo().getId());
            dna.setName(response.getDna().getBaseSequenceInfo().getName());

            Sequence sequence = new Sequence();
            sequence.setValue(response.getDna().getSequence().getValue());
            dna.setSequence(sequence);

            entity.setEntity(dna);
        }

        return entity;
    }

}
