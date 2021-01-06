package codes.mydna.services;

import codes.mydna.lib.AnalysisRequest;
import codes.mydna.lib.AnalysisResult;

/**
 * @see codes.mydna.services.impl.AnalysisServiceImpl
 */
public interface AnalysisService {

    AnalysisResult analyze(AnalysisRequest request);

}
