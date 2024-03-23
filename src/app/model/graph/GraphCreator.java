package app.model.graph;

import app.enums.Direction;
import app.entity.Vertex;
import app.utils.Constants;

public class GraphCreator {
    public static Vertex[] createVerticesArray(int n) {
        Integer currentX = Constants.FRAME_WIDTH / 2;
        Integer currentY = Constants.FRAME_HEIGHT / 2;
        Direction direction = Direction.FIRST_LEFT;

        Vertex[] vertices = new Vertex[n];
        for (int i = n; i > 0; i--) {
            vertices[i-1] = new Vertex(i, currentX, currentY);
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

        return vertices;
    }

}
