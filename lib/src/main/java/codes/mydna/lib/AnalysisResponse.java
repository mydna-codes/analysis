package codes.mydna.lib;

import codes.mydna.utils.TransferEntity;

import java.util.List;

public class AnalysisResponse {

    private String sequenceId;
    private List<TransferEntity<SearchedEnzyme>> enzymes;
    private List<TransferEntity<SearchedGene>> genes;

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    public List<TransferEntity<SearchedEnzyme>> getEnzymes() {
        return enzymes;
    }

    public void setEnzymes(List<TransferEntity<SearchedEnzyme>> enzymes) {
        this.enzymes = enzymes;
    }

    public List<TransferEntity<SearchedGene>> getGenes() {
        return genes;
    }

    public void setGenes(List<TransferEntity<SearchedGene>> genes) {
        this.genes = genes;
    }
}
