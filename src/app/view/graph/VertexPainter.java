package app.view.graph;

import app.entity.Vertex;
import app.utils.Constants;

import java.awt.*;

public class VertexPainter {
    public void paintNode(Graphics g, Vertex vertex){
        g.setColor(Color.BLACK);
        g.drawOval(vertex.getX(), vertex.getY(), Constants.DIAMETER, Constants.DIAMETER);

        g.setColor(Color.BLACK);
        Font font = new Font("Calibry", Font.BOLD, 18);
        g.setFont(font);
        g.drawString(String.valueOf(vertex.getValue()), vertex.getX()+20, vertex.getY()+30);
    }
}
