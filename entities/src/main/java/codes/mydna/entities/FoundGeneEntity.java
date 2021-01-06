package codes.mydna.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FOUND_GENE_TABLE")
public class FoundGeneEntity extends BaseEntity {

    @Column(name = "GENE_ID")
    private String geneId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FOUND_GENE_ID")
    private List<GeneOverlapEntity> geneOverlaps;

    public String getGeneId() {
        return geneId;
    }

    public void setGeneId(String geneId) {
        this.geneId = geneId;
    }

    public List<GeneOverlapEntity> getGeneOverlaps() {
        return geneOverlaps;
    }

    public void setGeneOverlaps(List<GeneOverlapEntity> geneOverlaps) {
        this.geneOverlaps = geneOverlaps;
    }
}
