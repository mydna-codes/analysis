package codes.mydna.api.resources.graphql;

import codes.mydna.clients.grpc.DnaServiceGrpcClient;
import codes.mydna.lib.Dna;
import codes.mydna.utils.TransferEntity;
import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
@GraphQLClass
public class AnalysisResource {

    @Inject
    private DnaServiceGrpcClient dnaServiceGrpcClient;

    @GraphQLQuery
    public String hello(@GraphQLArgument(name = "input") String input) {
        return "world " + input;
    }

    @GraphQLQuery
    public TransferEntity<Dna> getDna(@GraphQLArgument(name = "id") String id) {
        return dnaServiceGrpcClient.getDna(id);
    }

}
