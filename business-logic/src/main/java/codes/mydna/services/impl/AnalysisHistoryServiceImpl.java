package codes.mydna.services.impl;

import codes.mydna.entities.AnalysisRequestEntity;
import codes.mydna.lib.AnalysisRequest;
import codes.mydna.services.AnalysisHistoryService;
import codes.mydna.utils.EntityList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

@ApplicationScoped
public class AnalysisHistoryServiceImpl implements AnalysisHistoryService {

    public static final Logger LOG = Logger.getLogger(AnalysisHistoryService.class.getName());

    @Inject
    private EntityManager em;

    @Override
    public EntityList<AnalysisRequest> getAnalysis() {
        return null;
    }

    @Override
    public AnalysisRequest getAnalysis(String id) {
        return null;
    }

    @Override
    public AnalysisRequest insertAnalysis(AnalysisRequest request) {
        return null;
    }

    @Override
    public boolean deleteAnalysis(String id) {
        return false;
    }

    private AnalysisRequestEntity getAnalysisRequestEntity(String id){
        if (id == null)
            return null;
        return em.find(AnalysisRequestEntity.class, id);
    }

}
