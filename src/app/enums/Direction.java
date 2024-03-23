package app.enums;

public enum Direction {
    FIRST_LEFT(-300, 0),
    RIGHT(200, 0),
    LEFT(-200, 0),
    UP(0, -200),
    DOWN(0, 200);

    public final Integer x;
    public final Integer y;

    Direction(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
}
