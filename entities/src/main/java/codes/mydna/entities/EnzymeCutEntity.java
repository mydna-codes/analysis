package codes.mydna.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ENZYME_CUT_TABLE")
public class EnzymeCutEntity extends BaseEntity {

    @Column(name = "UPPER_CUT")
    private int upperCut;

    @Column(name = "LOWER_CUT")
    private int lowerCut;

    public int getUpperCut() {
        return upperCut;
    }

    public void setUpperCut(int upperCut) {
        this.upperCut = upperCut;
    }

    public int getLowerCut() {
        return lowerCut;
    }

    public void setLowerCut(int lowerCut) {
        this.lowerCut = lowerCut;
    }
}
