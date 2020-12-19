package codes.mydna.services.impl;

import codes.mydna.lib.AnalysisRequest;
import codes.mydna.lib.AnalysisResponse;
import codes.mydna.services.AnalysisService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class AnalysisServiceImpl implements AnalysisService {

    private static final Logger LOG = Logger.getLogger(AnalysisServiceImpl.class.getName());

    @PostConstruct
    private void postConstruct(){
        LOG.info(AnalysisServiceImpl.class.getSimpleName() + " initialized");
    }

    @Override
    public AnalysisResponse analyze(AnalysisRequest request) {
        return null;
    }
}
