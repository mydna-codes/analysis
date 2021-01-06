package codes.mydna.api.resources;

import codes.mydna.http.Headers;
import codes.mydna.lib.AnalysisResultSummary;
import codes.mydna.services.AnalysisResultService;
import codes.mydna.utils.EntityList;
import codes.mydna.utils.QueryParametersBuilder;
import com.kumuluz.ee.rest.beans.QueryParameters;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("results")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AnalysisResultResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private AnalysisResultService analysisResultService;

    @GET
    public Response getAnalysisResultSummaries(){
        QueryParameters qp = QueryParametersBuilder.buildDefault(uriInfo.getRequestUri().getRawQuery());
        EntityList<AnalysisResultSummary> summaries = analysisResultService.getAnalysisResultSummaries(qp);
        return Response.ok()
                .entity(summaries.getList())
                .header(Headers.XTotalCount, summaries.getCount())
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteAnalysisReport(@PathParam("id") String id){
        return Response.ok()
                .entity(analysisResultService.removeAnalysisResult(id))
                .build();
    }

}
