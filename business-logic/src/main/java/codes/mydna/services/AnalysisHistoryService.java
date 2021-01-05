package codes.mydna.services;

import codes.mydna.lib.AnalysisRequest;
import codes.mydna.utils.EntityList;

/**
 * @see codes.mydna.services.impl.AnalysisHistoryServiceImpl
 */
public interface AnalysisHistoryService {

    EntityList<AnalysisRequest> getAnalysis();
    AnalysisRequest getAnalysis(String id);
    AnalysisRequest insertAnalysis(AnalysisRequest request);
    boolean deleteAnalysis(String id);

}
