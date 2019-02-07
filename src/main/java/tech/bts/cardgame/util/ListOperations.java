package tech.bts.cardgame.util;

public class ListOperations {

    public int start ;
    public int end;
    public int increment;

    public ListOperations(int increment) {

        this.increment = increment;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }
}
