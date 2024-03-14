package app.graph.utils;

public enum Direction {
    FIRST_LEFT(-225, 0),
    RIGHT(150, 0),
    LEFT(-150, 0),
    UP(0, -150),
    DOWN(0, 150);

    public final Integer x;
    public final Integer y;

    Direction(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
}
