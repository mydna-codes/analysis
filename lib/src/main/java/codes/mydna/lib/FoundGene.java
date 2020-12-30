package codes.mydna.lib;

import java.util.List;

public class FoundGene {

    private Gene gene;
    private List<GeneOverlap> overlaps;

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
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
