package codes.mydna.services;

import codes.mydna.lib.AnalysisRequest;
import codes.mydna.services.impl.AnalysisRequestServiceImpl;
import codes.mydna.utils.EntityList;
import com.kumuluz.ee.rest.beans.QueryParameters;

/**
 * @see AnalysisRequestServiceImpl
 */
public interface AnalysisRequestService {

    EntityList<AnalysisRequest> getAnalysisRequests(QueryParameters qp);
    AnalysisRequest getAnalysisRequest(String id);
    AnalysisRequest insertAnalysisRequest(AnalysisRequest request);
    boolean removeAnalysisRequest(String id);

}
