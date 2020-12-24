package codes.mydna.lib;

import codes.mydna.status.Status;

import java.util.List;

public class AnalysisResponse {

    private String dnaId;
    private Status status;
    private List<FoundEnzyme> enzymes;
    private List<FoundGene> genes;

    public String getDnaId() {
        return dnaId;
    }

    public void setDnaId(String dnaId) {
        this.dnaId = dnaId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<FoundEnzyme> getEnzymes() {
        return enzymes;
    }

    public void setEnzymes(List<FoundEnzyme> enzymes) {
        this.enzymes = enzymes;
    }

    public List<FoundGene> getGenes() {
        return genes;
    }

    public void setGenes(List<FoundGene> genes) {
        this.genes = genes;
    }
}
