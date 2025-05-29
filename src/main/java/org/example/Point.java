package org.example;

public class Point {
    private int x;
    private int y;
    private boolean isWhite;

    public Point(int x, int y, boolean isWhite) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWhite() {
        return isWhite;
    }
}

