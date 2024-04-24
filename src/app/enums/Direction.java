package app.enums;

import app.utils.Constants;

public enum Direction {
    FIRST_LEFT(-(Constants.DISTANCE * 3 / 2), 0),
    RIGHT(Constants.DISTANCE, 0),
    LEFT(-Constants.DISTANCE, 0),
    UP(0, -Constants.DISTANCE),
    DOWN(0, Constants.DISTANCE);

    public final Integer x;
    public final Integer y;

    Direction(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
}
