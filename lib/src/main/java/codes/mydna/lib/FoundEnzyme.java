package codes.mydna.lib;

import java.util.List;

public class FoundEnzyme {

    private String id;
    private List<EnzymeCut> cuts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
