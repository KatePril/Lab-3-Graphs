package app.model;

public class Node {
    private Integer value;
    private Integer x;
    private Integer y;

    public Node(Integer value, Integer x, Integer y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }

    private final Integer SIZE = 50;

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
}




