package codes.mydna.mappers;

import codes.mydna.entities.FoundEnzymeEntity;
import codes.mydna.lib.FoundEnzyme;

import java.util.stream.Collectors;

public class FoundEnzymeMapper {

    public static FoundEnzyme fromEntity(FoundEnzymeEntity entity) {
        FoundEnzyme foundEnzyme = new FoundEnzyme();
        foundEnzyme.setCuts(entity.getEnzymeCuts()
                .stream()
                .map(EnzymeCutMapper::fromEntity)
                .collect(Collectors.toList()));
        return foundEnzyme;
    }

    public static FoundEnzymeEntity toEntity(FoundEnzyme foundEnzyme){
        if(foundEnzyme == null)
            return null;
        FoundEnzymeEntity entity = new FoundEnzymeEntity();
        entity.setEnzymeId(foundEnzyme.getEnzyme().getId());
        entity.setEnzymeCuts(foundEnzyme.getCuts()
                .stream()
                .map(EnzymeCutMapper::toEntity)
                .collect(Collectors.toList()));
        return entity;
    }

}
