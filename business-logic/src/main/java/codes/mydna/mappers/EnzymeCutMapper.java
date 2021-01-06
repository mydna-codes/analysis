package codes.mydna.mappers;

import codes.mydna.entities.EnzymeCutEntity;
import codes.mydna.lib.EnzymeCut;

public class EnzymeCutMapper {

    public static EnzymeCut fromEntity(EnzymeCutEntity entity){
        EnzymeCut enzymeCut = new EnzymeCut();
        enzymeCut.setUpperCut(entity.getUpperCut());
        enzymeCut.setLowerCut(entity.getLowerCut());
        return enzymeCut;
    }
    public static EnzymeCutEntity toEntity(EnzymeCut cut){
        if(cut == null)
            return null;
        EnzymeCutEntity entity = new EnzymeCutEntity();
        entity.setUpperCut(cut.getUpperCut());
        entity.setLowerCut(cut.getLowerCut());
        return entity;
    }

}
