package codes.mydna.clients.kafka;

import codes.mydna.auth.common.models.User;
import codes.mydna.lib.AnalysisRequest;
import codes.mydna.lib.large_scale.LargeScaleAnalysisRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kumuluz.ee.streaming.common.annotations.StreamProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class KafkaLargeScaleAnalysisClient {

    @Inject
    @StreamProducer
    private Producer<String, String> producer;

    public static final Logger LOG = Logger.getLogger(KafkaLargeScaleAnalysisClient.class.getName());

    public void runLargeScaleAnalysis(AnalysisRequest analysisRequest, User user){

        LOG.info("RunLargeScaleAnalysis -> Received request...");

        LargeScaleAnalysisRequest request = new LargeScaleAnalysisRequest();
        request.setAnalysisRequest(analysisRequest);
        request.setUser(user);
        LOG.info("RunLargeScaleAnalysis -> Created LargeScaleAnalysisRequest");

        String jsonRequest;
        try {
            LOG.info("RunLargeScaleAnalysis -> Converting request to json...");
            ObjectMapper mapper = new ObjectMapper();
            jsonRequest = mapper.writeValueAsString(request);
            LOG.info("RunLargeScaleAnalysis -> Converted");
        } catch (JsonProcessingException e) {
            LOG.severe("Failed to serialize AnalysisResult object!");
            throw new InternalServerErrorException("Failed to serialize AnalysisResult object!");
        }

        LOG.info("RunLargeScaleAnalysis -> Creating producer...");
        ProducerRecord<String, String> record = new ProducerRecord<>(
                "large_scale_analysis",
                UUID.randomUUID().toString(),
                jsonRequest);
        LOG.info("RunLargeScaleAnalysis -> Producer created");

        LOG.info("RunLargeScaleAnalysis -> Sending message...");
        producer.send(record, (metadata, e) -> {
            if (e != null)
                LOG.severe(e.getMessage());
            else
                LOG.info("Message successfully sent");
        });
        LOG.info("RunLargeScaleAnalysis -> Sending message...");

    }

}
