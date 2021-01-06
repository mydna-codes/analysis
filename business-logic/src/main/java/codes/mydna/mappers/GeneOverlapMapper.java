package codes.mydna.mappers;

import codes.mydna.entities.GeneOverlapEntity;
import codes.mydna.lib.GeneOverlap;

public class GeneOverlapMapper {

    public static GeneOverlap fromEntity(GeneOverlapEntity entity){
        if (entity == null)
            return null;
        GeneOverlap overlap = new GeneOverlap();
        overlap.setFromIndex(entity.getFromIndex());
        overlap.setToIndex(entity.getToIndex());
        overlap.setOrientation(entity.getOrientation());
        return overlap;
    }

    public static GeneOverlapEntity toEntity(GeneOverlap overlap){
        if(overlap == null)
            return null;
        GeneOverlapEntity entity = new GeneOverlapEntity();
        entity.setToIndex(overlap.getToIndex());
        entity.setFromIndex(overlap.getFromIndex());
        entity.setOrientation(overlap.getOrientation());
        return entity;
    }

}
