package gui;

public class difCoorPair implements Comparable<difCoorPair> {
     public int difficulty=0;
    public xyPair pair;


    public difCoorPair(int difficulty, xyPair pair) {
        this.difficulty = difficulty;
        this.pair = pair;
    }

    @Override
    public int compareTo(difCoorPair o) {
        return Integer.compare(difficulty,o.difficulty);
    }
}
