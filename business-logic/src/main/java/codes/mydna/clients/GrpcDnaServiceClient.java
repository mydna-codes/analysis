package codes.mydna.clients;

import codes.mydna.exceptions.NotFoundException;
import codes.mydna.exceptions.RestException;
import codes.mydna.lib.Dna;
import codes.mydna.lib.Sequence;
import codes.mydna.lib.grpc.DnaServiceGrpc;
import codes.mydna.lib.grpc.DnaServiceProto;
import com.kumuluz.ee.grpc.client.GrpcChannelConfig;
import com.kumuluz.ee.grpc.client.GrpcChannels;
import com.kumuluz.ee.grpc.client.GrpcClient;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.net.ssl.SSLException;
import java.util.logging.Logger;

@ApplicationScoped
public class GrpcDnaServiceClient {

    private final static Logger LOG = Logger.getLogger(GrpcDnaServiceClient.class.getName());

    private DnaServiceGrpc.DnaServiceBlockingStub dnaServiceBlockingStub;

    @PostConstruct
    public void init(){
        try {
            GrpcChannels clientPool = GrpcChannels.getInstance();
            GrpcChannelConfig config = clientPool.getGrpcClientConfig("sequence-bank-grpc-client");
            GrpcClient client = new GrpcClient(config);
            dnaServiceBlockingStub = DnaServiceGrpc.newBlockingStub(client.getChannel()).withWaitForReady();
            LOG.info("Grpc client " + GrpcDnaServiceClient.class.getSimpleName() + " initialized.");
        } catch (SSLException e) {
            LOG.warning(e.getMessage());
        }
    }
    public Dna getDna(String id){

        DnaServiceProto.Request request = DnaServiceProto.Request.newBuilder()
                .setId(id)
                .build();

        DnaServiceProto.Response response;
        try {
            response = dnaServiceBlockingStub.getDna(request);
        } catch (Exception e) {
            if(e instanceof StatusRuntimeException) {
                StatusRuntimeException err = (StatusRuntimeException) e;
                if(err.getStatus().getCode().equals(Status.NOT_FOUND.getCode()))
                    LOG.info("Entity with id '" + id + "' not found.");
                else
                    LOG.info(err.getStatus().getCode() + ": " + err.getStatus().getDescription());
            }else {
                LOG.severe("Could not retrieve data from gRPC server.");
            }
            return null;
        }

        if(!response.hasDna())
            LOG.severe("NO DNA");

        DnaServiceProto.Dna protoDna = response.getDna();
        Dna dna = new Dna();

        DnaServiceProto.Sequence grpcSequence = protoDna.getSequence();
        Sequence sequence = new Sequence();

        dna.setId(protoDna.getId());
        dna.setName(protoDna.getName());
        sequence.setValue(grpcSequence.getValue());
        dna.setSequence(sequence);

        return dna;
    }

}
