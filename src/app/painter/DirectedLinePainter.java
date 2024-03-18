package app.painter;

import app.model.Node;

import java.awt.*;

public class DirectedLinePainter extends UndirectedLinePainter {

    public DirectedLinePainter(int len) {
        super(len);
    }

    @Override
    protected void paintCycleLine(Graphics g, Node node) {
        super.paintCycleLine(g, node);
    }

    @Override
    protected void paintLineAvoidingMiddle(Graphics g, Node nodeOne, Node nodeTwo) {
        super.paintLineAvoidingMiddle(g, nodeOne, nodeTwo);
    }

    @Override
    protected void paintLineDistOneX(Graphics g, Node nodeOne, Node nodeTwo) {
        super.paintLineDistOneX(g, nodeOne, nodeTwo);
    }

    @Override
    protected void paintLineDistOneY(Graphics g, Node nodeOne, Node nodeTwo) {
        super.paintLineDistOneY(g, nodeOne, nodeTwo);
    }

    @Override
    protected void paintSameXLine(Graphics g, Node nodeOne, Node nodeTwo) {
        super.paintSameXLine(g, nodeOne, nodeTwo);
    }

    @Override
    protected void paintSameYLine(Graphics g, Node nodeOne, Node nodeTwo) {
        super.paintSameYLine(g, nodeOne, nodeTwo);
    }

    @Override
    protected void paintFreeConditionLine(Graphics g, Node nodeOne, Node nodeTwo) {
        super.paintFreeConditionLine(g, nodeOne, nodeTwo);
    }
}
