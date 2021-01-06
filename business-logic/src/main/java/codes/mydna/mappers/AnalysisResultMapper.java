package codes.mydna.mappers;

import codes.mydna.entities.AnalysisResultEntity;
import codes.mydna.lib.AnalysisResult;
import codes.mydna.lib.AnalysisResultSummary;
import codes.mydna.status.Status;

import java.util.stream.Collectors;

public class AnalysisResultMapper {

    public static AnalysisResultSummary fromEntityToSummary(AnalysisResultEntity entity){
        if(entity == null)
            return null;
        AnalysisResultSummary summary = new AnalysisResultSummary();
        BaseMapper.fromEntity(entity, summary);
        summary.setAnalysisName(entity.getAnalysisName());
        summary.setAnalysisExecutionTime(entity.getAnalysisExecutionTime());
        summary.setTotalExecutionTime(entity.getTotalExecutionTime());
        return summary;
    }

    public static AnalysisResult fromEntity(AnalysisResultEntity entity){
        AnalysisResult result = new AnalysisResult();
        BaseMapper.fromEntity(entity, result);
        result.setAnalysisName(entity.getAnalysisName());
        result.setAnalysisExecutionTime(entity.getAnalysisExecutionTime());
        result.setTotalExecutionTime(entity.getTotalExecutionTime());
        result.setStatus(Status.OK); // Otherwise it there would be no results
        result.setEnzymes(entity.getFoundEnzymes()
                .stream()
                .map(FoundEnzymeMapper::fromEntity)
                .collect(Collectors.toList()));
        result.setGenes(entity.getFoundGenes()
                .stream()
                .map(FoundGeneMapper::fromEntity)
                .collect(Collectors.toList()));
        return result;
    }

    public static AnalysisResultEntity toEntity(AnalysisResult result){

        if(result == null)
            return null;

        AnalysisResultEntity entity = new AnalysisResultEntity();

        entity.setAnalysisName(result.getAnalysisName());
        entity.setTotalExecutionTime(result.getTotalExecutionTime());
        entity.setAnalysisExecutionTime(result.getAnalysisExecutionTime());
        entity.setDnaId(result.getDna().getId());
        entity.setFoundEnzymes(result.getEnzymes()
                .stream()
                .map(FoundEnzymeMapper::toEntity)
                .collect(Collectors.toList()));
        entity.setFoundGenes(result.getGenes()
                .stream()
                .map(FoundGeneMapper::toEntity)
                .collect(Collectors.toList()));
        return entity;
    }

}
