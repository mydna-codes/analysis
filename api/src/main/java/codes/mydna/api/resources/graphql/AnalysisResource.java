package codes.mydna.api.resources.graphql;

import codes.mydna.lib.Dna;
import codes.mydna.lib.Sequence;
import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;

@GraphQLClass
public class AnalysisResource {

    @GraphQLQuery
    public String hello(@GraphQLArgument(name = "input") String input) {
        return "world " + input;
    }

    @GraphQLQuery
    public Dna getDna() {
        Dna dna = new Dna();
        dna.setName("Test name");
        dna.setId("123");
        Sequence sequence = new Sequence();
        sequence.setValue("ACTG");
        dna.setSequence(sequence);
        return dna;
    }

}
