package app.graph;

import app.graph.utils.Direction;
import app.model.Node;

public class GraphCreator {
    public static Node[] createVerticesArray(int n) {
        Integer currentX = 600;
        Integer currentY = 400;
        Direction direction = Direction.FIRST_LEFT;

        Node[] nodes = new Node[n];
        for (int i = n; i > 0; i--) {
            nodes[i-1] = new Node(i, currentX, currentY);
            currentX += direction.x;
            currentY += direction.y;

            if (i == n) {
                direction = Direction.DOWN;
            } else if (i == n-1) {
                direction = Direction.RIGHT;
            } else if (i == n/2+2) {
                direction = Direction.UP;
            } else if (i == n/2) {
                direction = Direction.LEFT;
            }
        }

        return nodes;
    }

}
