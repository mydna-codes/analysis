package codes.mydna.api.resources;

import codes.mydna.clients.grpc.DnaServiceGrpcClient;
import codes.mydna.clients.grpc.EnzymeServiceGrpcClient;
import codes.mydna.lib.Dna;
import codes.mydna.lib.Enzyme;
import codes.mydna.utils.TransferEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("grpc-test")
public class TestResource {

    private static final Logger LOG = Logger.getLogger(TestResource.class.getName());

    @Inject
    private DnaServiceGrpcClient dnaServiceGrpcClient;

    @GET
    @Path("{id}")
    public Response getDna(@PathParam("id") String id){
        LOG.info("grpc-test endpoint called");
        LOG.info("Test id: " + id);
        TransferEntity<Dna> dna = dnaServiceGrpcClient.getDna(id);
        return Response.ok().entity(dna).build();
    }

    @Inject
    private EnzymeServiceGrpcClient enzymeServiceGrpcClient;

    @GET
    public Response getMultipleEnzymes(List<String> ids){
        List<TransferEntity<Enzyme>> enzymes = enzymeServiceGrpcClient.getMultipleEnzymes(ids);
        return Response.ok().entity(enzymes).build();
    }

}
