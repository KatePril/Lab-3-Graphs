package app.view.graph.utils;

public enum ArrowDirection {
    UP(1),
    NO_DIRECTION(0),
    DOWN(-1);
    public int val;

    ArrowDirection(int val) {
        this.val = val;
    }
}
