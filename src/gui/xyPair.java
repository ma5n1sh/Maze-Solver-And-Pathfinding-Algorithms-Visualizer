package gui;

public class xyPair {
    public int x,y;

    public xyPair(int x, int y) {
        this.x = x;
        this.y=y;
    }

    public String toString() {
        return Integer.toString(x)+","+Integer.toString(y);
    }
}
