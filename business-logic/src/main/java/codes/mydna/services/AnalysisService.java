package codes.mydna.services;

import codes.mydna.lib.AnalysisRequest;
import codes.mydna.lib.AnalysisResponse;

/**
 * @see codes.mydna.services.impl
 */
public interface AnalysisService {

    AnalysisResponse analyze(AnalysisRequest request);

}
