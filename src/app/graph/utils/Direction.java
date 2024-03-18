package app.graph.utils;

public enum Direction {
    FIRST_LEFT(-225 - 75, 0),
    RIGHT(150 + 50, 0),
    LEFT(-150 - 50, 0),
    UP(0, -150 - 50),
    DOWN(0, 150 + 50);

    public final Integer x;
    public final Integer y;

    Direction(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
}
