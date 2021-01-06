package codes.mydna.lib;

import java.util.List;

public class AnalysisRequest extends BaseType {

    private String dnaId;
    private List<String> enzymeIds;
    private List<String> geneIds;

    public String getDnaId() {
        return dnaId;
    }

    public void setDnaId(String dnaId) {
        this.dnaId = dnaId;
    }

    public List<String> getEnzymeIds() {
        return enzymeIds;
    }

    public void setEnzymeIds(List<String> enzymeIds) {
        this.enzymeIds = enzymeIds;
    }

    public List<String> getGeneIds() {
        return geneIds;
    }

    public void setGeneIds(List<String> geneIds) {
        this.geneIds = geneIds;
    }
}
