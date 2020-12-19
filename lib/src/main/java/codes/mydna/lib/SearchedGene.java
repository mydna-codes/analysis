package codes.mydna.lib;

import java.util.List;

public class SearchedGene {

    private String geneId;
    private List<GeneOverlap> overlaps;

    public String getGeneId() {
        return geneId;
    }

    public void setGeneId(String geneId) {
        this.geneId = geneId;
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
