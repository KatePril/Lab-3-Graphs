package app.entity;

import static java.lang.String.format;

public class Vertex {
    private final Integer value;
    private final Integer x;
    private final Integer y;
    private final Integer SIZE = 50;

    public Vertex(Integer value, Integer x, Integer y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getSIZE() {
        return SIZE;
    }

    @Override
    public String toString() {
        return format("value = %d, x = %d, y = %d", value, x, y);
    }
}




