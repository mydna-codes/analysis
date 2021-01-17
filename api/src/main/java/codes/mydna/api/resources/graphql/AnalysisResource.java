package codes.mydna.api.resources.graphql;

import codes.mydna.auth.common.models.User;
import codes.mydna.auth.keycloak.KeycloakContext;
import codes.mydna.lib.AnalysisRequest;
import codes.mydna.lib.AnalysisResult;
import codes.mydna.services.AnalysisService;
import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@GraphQLClass
@RequestScoped
public class AnalysisResource {

    public static final Logger LOG = Logger.getLogger(AnalysisResource.class.getSimpleName());

    @Inject
    private AnalysisService analysisService;

    @Inject
    private KeycloakContext keycloakContext;

    private User user;

    @PostConstruct
    private void fetchUser() {
        user = keycloakContext.getUser();
    }

    @GraphQLMutation
    public AnalysisResult analyzeDna(
            @GraphQLNonNull
            @GraphQLArgument(name = "request", description = "Request that contains id of DNA that " +
                    "will be analyzed and ids of enzymes/genes to be found in it."
            ) AnalysisRequest request) {

        return analysisService.analyze(request, user);
    }

}
