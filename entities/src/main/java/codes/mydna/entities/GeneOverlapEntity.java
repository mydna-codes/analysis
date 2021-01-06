package codes.mydna.entities;

import codes.mydna.lib.enums.Orientation;

import javax.persistence.*;

@Entity
@Table(name = "GENE_OVERLAP_TABLE")
public class GeneOverlapEntity extends BaseEntity{

    @Column(name = "FROM_INDEX")
    private int fromIndex;

    @Column(name = "TO_INDEX")
    private int toIndex;

    @Column(name = "ORIENTATION")
    @Enumerated(EnumType.STRING)
    private Orientation orientation;

    public int getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(int fromIndex) {
        this.fromIndex = fromIndex;
    }

    public int getToIndex() {
        return toIndex;
    }

    public void setToIndex(int toIndex) {
        this.toIndex = toIndex;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
