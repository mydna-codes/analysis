package codes.mydna.lib;

import java.util.List;

public class SearchedEnzyme {

    private String enzymeId;
    private List<EnzymeCut> cuts;

    public String getEnzymeId() {
        return enzymeId;
    }

    public void setEnzymeId(String enzymeId) {
        this.enzymeId = enzymeId;
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
