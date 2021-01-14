package codes.mydna.services;

import codes.mydna.auth.common.models.User;
import codes.mydna.lib.AnalysisResult;
import codes.mydna.lib.AnalysisResultSummary;
import codes.mydna.utils.EntityList;
import com.kumuluz.ee.rest.beans.QueryParameters;

/**
 * @see codes.mydna.services.impl.AnalysisResultServiceImpl
 */
public interface AnalysisResultService {

    EntityList<AnalysisResultSummary> getAnalysisResultSummaries(QueryParameters qp);
    AnalysisResult getAnalysisResult(String id, User user);
    AnalysisResult insertAnalysisResult(AnalysisResult result);
    boolean removeAnalysisResult(String id);

}
