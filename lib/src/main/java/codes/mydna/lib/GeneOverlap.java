package codes.mydna.lib;

import codes.mydna.lib.enums.Orientation;

public class GeneOverlap {

    private int fromIndex;
    private int toIndex;
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
