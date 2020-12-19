package codes.mydna.lib;

import java.util.List;

public class AnalysisRequest {

    private String sequenceId;
    private List<String> enzymeIds;
    private List<String> geneIds;

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
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
