package org.nextgen.hackerranksolution.placechesspieces;

public class BoardPosition {
    private final int y;
    private final int x;

    public BoardPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }


    public BoardPosition upperLeft(){
        return new BoardPosition(x-1, y-1);
    }
    public BoardPosition upperCenter(){
        return new BoardPosition(x-1, y);
    }
    public BoardPosition upperRight(){
        return new BoardPosition(x-1, y+1);
    }
    public BoardPosition left(){
        return new BoardPosition(x, y-1);
    }
    public BoardPosition right(){
        return new BoardPosition(x, y+1);
    }
    public BoardPosition lowerLeft(){
        return new BoardPosition(x+1, y-1);
    }
    public BoardPosition lowerCenter(){
        return new BoardPosition(x+1, y);
    }
    public BoardPosition lowerRight(){
        return new BoardPosition(x+1, y+1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardPosition)) return false;

        BoardPosition that = (BoardPosition) o;

        if (y != that.y) return false;
        return x == that.x;

    }

    @Override
    public int hashCode() {
        int result = y;
        result = 31 * result + x;
        return result;
    }

    @Override
    public String toString() {
        return "BoardPosition{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
