package codes.mydna.api.resources;

import codes.mydna.clients.GrpcDnaServiceClient;
import codes.mydna.lib.Dna;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("grpc-test")
public class TestResource {

    private static final Logger LOG = Logger.getLogger(TestResource.class.getName());

    @Inject
    private GrpcDnaServiceClient grpcClient;

    @GET
    @Path("{id}")
    public Response getDna(@PathParam("id") String id){
        LOG.info("Test id: " + id);
        Dna dna = grpcClient.getDna(id);
        return Response.ok().entity(dna).build();
    }

}
