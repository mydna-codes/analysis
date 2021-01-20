package codes.mydna.analysis.services;

import codes.mydna.analysis.services.impl.AnalysisServiceImpl;
import codes.mydna.auth.common.models.User;
import codes.mydna.analysis_result.lib.AnalysisRequest;
import codes.mydna.analysis_result.lib.AnalysisResult;

/**
 * @see AnalysisServiceImpl
 */
public interface AnalysisService {

    AnalysisResult analyze(AnalysisRequest request, User user);

}
