package app.entity;

import static java.lang.String.format;

public class Vertex {
    private Integer value;
    private Integer x;
    private Integer y;

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

    @Override
    public String toString() {
        return format("value = %d, x = %d, y = %d", value, x, y);
    }
}

