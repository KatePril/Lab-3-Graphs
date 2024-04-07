package app.enums;

public enum VertexStatus {
    NEW(0),
    VISITED(1),
    ACTIVE(2),
    CLOSED(3);
    public final int val;

    VertexStatus(int val) {
        this.val = val;
    }
}
