package app.view.graph;

import app.entity.Vertex;
import app.enums.VertexStatus;
import app.utils.ColorResources;
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
        g.drawOval(vertex.getX(), vertex.getY(), Constants.DIAMETER, Constants.DIAMETER);

        Font font = new Font("Calibry", Font.BOLD, 18);
        g.setFont(font);
        g.drawString(String.valueOf(vertex.getValue()), vertex.getX()+20, vertex.getY()+30);
    }
    public Color getVertexColor(VertexStatus status) {
        Color output;
        switch (status) {
            case NEW -> output = ColorResources.BROWN;
            case VISITED -> output = ColorResources.BLUE;
            case ACTIVE -> output = ColorResources.GREEN;
            case CLOSED -> output = ColorResources.RED;
            default -> throw new IllegalStateException("Unexpected value: " + status);
        }
        System.out.println(status);
        return output;
    }
}
