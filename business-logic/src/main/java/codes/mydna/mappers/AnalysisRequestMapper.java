package codes.mydna.mappers;

import codes.mydna.entities.AnalysisRequestEntity;
import codes.mydna.lib.AnalysisRequest;

public class AnalysisRequestMapper {

    public static AnalysisRequest fromEntity(AnalysisRequestEntity entity) {

        if(entity == null)
            return null;

        AnalysisRequest analysisRequest = new AnalysisRequest();
        BaseMapper.fromEntity(entity, analysisRequest);

        analysisRequest.setDnaId(entity.getDnaId());
        analysisRequest.setEnzymeIds(entity.getEnzymeIds());
        analysisRequest.setGeneIds(entity.getGeneIds());

        return analysisRequest;
    }

    public static AnalysisRequestEntity toEntity(AnalysisRequest analysisRequest){

        if(analysisRequest == null)
            return null;

        AnalysisRequestEntity entity = new AnalysisRequestEntity();
        entity.setDnaId(analysisRequest.getDnaId());
        entity.setEnzymeIds(analysisRequest.getEnzymeIds());
        entity.setGeneIds(analysisRequest.getGeneIds());

        return entity;
    }

}
