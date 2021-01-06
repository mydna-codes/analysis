package codes.mydna.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FOUND_ENZYME_TABLE")
public class FoundEnzymeEntity extends BaseEntity {

    @Column(name = "ENZYME_ID")
    private String enzymeId;

    @OneToMany
    @JoinColumn(name = "FOUND_ENZYME_ID")
    private List<EnzymeCutEntity> enzymeCuts;

    public String getEnzymeId() {
        return enzymeId;
    }

    public void setEnzymeId(String enzymeId) {
        this.enzymeId = enzymeId;
    }

    public List<EnzymeCutEntity> getEnzymeCuts() {
        return enzymeCuts;
    }

    public void setEnzymeCuts(List<EnzymeCutEntity> enzymeCuts) {
        this.enzymeCuts = enzymeCuts;
    }
}
