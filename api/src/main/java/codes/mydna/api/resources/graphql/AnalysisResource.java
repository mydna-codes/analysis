package codes.mydna.api.resources.graphql;

import codes.mydna.lib.AnalysisRequest;
import codes.mydna.lib.AnalysisResult;
import codes.mydna.services.AnalysisService;
import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
@GraphQLClass
public class AnalysisResource {

    @Inject
    private AnalysisService analysisService;

    // TODO: Partial analysis

    @GraphQLMutation
    public AnalysisResult analyzeDna(
            @GraphQLNonNull
            @GraphQLArgument(name = "request", description = "Request that contains id of DNA that " +
                    "will be analyzed and ids of enzymes/genes to be found in it."
            ) AnalysisRequest request) {
        return analysisService.analyze(request);
    }

}
