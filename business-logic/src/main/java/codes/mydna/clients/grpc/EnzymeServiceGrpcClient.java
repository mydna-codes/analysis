package codes.mydna.clients.grpc;

import codes.mydna.lib.Enzyme;
import codes.mydna.lib.Sequence;
import codes.mydna.lib.grpc.EnzymeServiceGrpc;
import codes.mydna.lib.grpc.EnzymeServiceProto;
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
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
public class EnzymeServiceGrpcClient {

    private final static Logger LOG = Logger.getLogger(EnzymeServiceGrpcClient.class.getName());

    private EnzymeServiceGrpc.EnzymeServiceBlockingStub enzymeServiceBlockingStub;

    @PostConstruct
    public void init(){
        try {
            GrpcChannels clientPool = GrpcChannels.getInstance();
            GrpcChannelConfig config = clientPool.getGrpcClientConfig("sequence-bank-grpc-client");
            GrpcClient client = new GrpcClient(config);
            enzymeServiceBlockingStub = EnzymeServiceGrpc.newBlockingStub(client.getChannel()).withWaitForReady();
            LOG.info("Grpc client " + EnzymeServiceGrpcClient.class.getSimpleName() + " initialized.");
        } catch (SSLException e) {
            LOG.warning(e.getMessage());
        }
    }

    public List<Enzyme> getMultipleEnzymes(List<String> ids){

        // If ids are not passed, don't call grpc and return empty list
        if(ids == null || ids.isEmpty()){
            return new ArrayList<>();
        }

        EnzymeServiceProto.MultipleEnzymesRequest request = EnzymeServiceProto.MultipleEnzymesRequest.newBuilder()
                .addAllId(ids)
                .build();

        EnzymeServiceProto.MultipleEnzymesResponse response;
        try {
            response = enzymeServiceBlockingStub.getMultipleEnzymes(request);
        }catch (Exception e) {
            LOG.severe("Failed to connect to gRPC client: " + e.getMessage());
            return new ArrayList<>();
        }

        return response.getEnzymeList()
                .stream()
                .map(this::fromProto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Enzyme fromProto(EnzymeServiceProto.Enzyme protoEnzyme){

        Status status = Status.valueOf(protoEnzyme.getEntityStatus());
        if(status != Status.OK)
            return null;

        Sequence sequence = new Sequence();
        sequence.setValue(protoEnzyme.getSequence().getValue());

        Enzyme enzyme = new Enzyme();
        enzyme.setSequence(sequence);
        enzyme.setId(protoEnzyme.getBaseSequenceInfo().getId());
        enzyme.setName(protoEnzyme.getBaseSequenceInfo().getName());
        enzyme.setUpperCut(protoEnzyme.getUpperCut());
        enzyme.setLowerCut(protoEnzyme.getLowerCut());

        return enzyme;
    }

}
