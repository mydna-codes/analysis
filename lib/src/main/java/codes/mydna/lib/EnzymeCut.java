package codes.mydna.lib;

public class EnzymeCut {

    // Restriction site (cut) in template sequence
    private int upperCut;

    // Restriction site (cut) in complimentary sequence
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
