package codes.mydna.entities;

import codes.mydna.entities.converters.StringListConverter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ANALYSIS_TABLE")
public class AnalysisRequestEntity extends BaseEntity{

    @Column(name = "DNA_ID", updatable = false)
    private String dnaId;

    @Column(name = "FOUND_ENZYME_IDS", updatable = false)
    @Convert(converter = StringListConverter.class)
    private List<String> enzymeIds;

    @Column(name = "FOUND_GENE_IDS", updatable = false)
    @Convert(converter = StringListConverter.class)
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
