package app.view.graph;

import app.entity.Node;

import java.awt.*;

public class NodePainter {
    public void paintNode(Graphics g, Node node){
        g.setColor(Color.BLACK);
        g.drawOval(node.getX(), node.getY(), node.getSIZE(), node.getSIZE());

        g.setColor(Color.BLACK);
        Font font = new Font("Calibry", Font.BOLD, 18);
        g.setFont(font);
        g.drawString(String.valueOf(node.getValue()), node.getX()+20, node.getY()+30);

    }
}