package codes.mydna.lib;

import java.util.List;

public class FoundEnzyme {

    private Enzyme enzyme;
    private List<EnzymeCut> cuts;

    public Enzyme getEnzyme() {
        return enzyme;
    }

    public void setEnzyme(Enzyme enzyme) {
        this.enzyme = enzyme;
    }

    public List<EnzymeCut> getCuts() {
        return cuts;
    }

    public void setCuts(List<EnzymeCut> cuts) {
        this.cuts = cuts;
    }

    public int getFrequency() {
        return (cuts == null) ? 0 : cuts.size();
    }
}
