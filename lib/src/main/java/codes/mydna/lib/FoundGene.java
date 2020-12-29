package codes.mydna.lib;

import java.util.List;

public class FoundGene {

    private String id;
    private List<GeneOverlap> overlaps;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<GeneOverlap> getOverlaps() {
        return overlaps;
    }

    public void setOverlaps(List<GeneOverlap> overlaps) {
        this.overlaps = overlaps;
    }

    public int getFrequency(){
        return (overlaps == null) ? 0 : overlaps.size();
    }

}
