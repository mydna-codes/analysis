package codes.mydna.entities;

import codes.mydna.entities.converters.StringListConverter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ANALYSIS_TABLE")
public class AnalysisEntity extends BaseEntity{

    @Column(name = "DNA_ID", updatable = false)
    private String dnaId;

    @Column(name = "FOUND_ENZYME_IDS", updatable = false)
    @Convert(converter = StringListConverter.class)
    private List<String> foundEnzymeIds;

    @Column(name = "FOUND_GENE_IDS", updatable = false)
    @Convert(converter = StringListConverter.class)
    private List<String> foundGeneIds;

    public String getDnaId() {
        return dnaId;
    }

    public void setDnaId(String dnaId) {
        this.dnaId = dnaId;
    }

    public List<String> getFoundEnzymeIds() {
        return foundEnzymeIds;
    }

    public void setFoundEnzymeIds(List<String> foundEnzymeIds) {
        this.foundEnzymeIds = foundEnzymeIds;
    }

    public List<String> getFoundGeneIds() {
        return foundGeneIds;
    }

    public void setFoundGeneIds(List<String> foundGeneIds) {
        this.foundGeneIds = foundGeneIds;
    }
}
