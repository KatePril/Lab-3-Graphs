package app.view.graph;

import app.entity.Vertex;
import app.enums.VertexStatus;
import app.utils.Constants;

import java.awt.*;

public class VertexPainter {
    public void paintNodes(Graphics g, Vertex[] vertices, VertexStatus[] statuses) {
        for (int i = 0; i < vertices.length; i++) {
            paintVertex(g, vertices[i], getVertexColor(statuses[i]));
        }
    }

    public void paintVertex(Graphics g, Vertex vertex, Color color){
        g.setColor(color);
        g.drawOval(vertex.getX(), vertex.getY(), Constants.VERTEX_SIZE, Constants.VERTEX_SIZE);

        Font font = new Font("Calibry", Font.BOLD, 18);
        g.setFont(font);
        g.drawString(String.valueOf(vertex.getValue()), vertex.getX()+20, vertex.getY()+30);
    }
    public Color getVertexColor(VertexStatus status) {
        Color output;
        switch (status) {
            case NEW -> output = Color.BLACK;
            case VISITED -> output = new Color(0, 50, 147);
            case ACTIVE -> output = new Color(22, 87, 0);
            case CLOSED -> output = new  Color(161, 0, 0);
            default -> throw new IllegalStateException("Unexpected value: " + status);
        }
        return output;
    }
}
