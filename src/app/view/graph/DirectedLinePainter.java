package app.view.graph;

import app.entity.Node;
import app.view.graph.utils.ArrowDirection;

import java.awt.*;

public class DirectedLinePainter extends UndirectedLinePainter {

    public DirectedLinePainter(int len) {
        super(len);
    }

    @Override
    protected int[] paintCycleLine(Graphics g, Node node) {
        drawArrow(g, super.paintCycleLine(g, node));

        return null;
    }

    @Override
    protected int[] paintLineAvoidingMiddle(Graphics g, Node nodeOne, Node nodeTwo) {
        drawArrow(g, super.paintLineAvoidingMiddle(g, nodeOne, nodeTwo));

        return null;
    }

    @Override
    protected int[] paintLineDistOneX(Graphics g, Node nodeOne, Node nodeTwo) {
        drawArrow(g, super.paintLineDistOneX(g, nodeOne, nodeTwo));

        return null;
    }

    @Override
    protected int[] paintLineDistOneY(Graphics g, Node nodeOne, Node nodeTwo) {
        drawArrow(g, super.paintLineDistOneY(g, nodeOne, nodeTwo));

        return null;
    }

    @Override
    protected int[] paintSameXLine(Graphics g, Node nodeOne, Node nodeTwo) {
        drawArrow(g, super.paintSameXLine(g, nodeOne, nodeTwo));
        return null;
    }

    @Override
    protected int[] paintSameYLine(Graphics g, Node nodeOne, Node nodeTwo) {
        drawArrow(g, super.paintSameYLine(g, nodeOne, nodeTwo));
        return null;
    }

    @Override
    protected int[] paintFreeConditionLine(Graphics g, Node nodeOne, Node nodeTwo) {
        drawArrow(g, super.paintFreeConditionLine(g, nodeOne, nodeTwo));
        return null;
    }

    private void drawArrow(Graphics g, int[] coordinates) { // {x1, y1, x2, y2}
        double angle;
        if (coordinates[2] - coordinates[0] == 0) {
            angle = 90.0;
        } else {
            double slope = (double) (coordinates[3] - coordinates[1]) / (coordinates[2] - coordinates[0]);
            angle = Math.toDegrees(Math.atan(slope));
        }
        double fi = Math.PI * (180.0 - angle) / 180.0;
        int lx,ly,rx,ry;
        if (coordinates[4] == ArrowDirection.UP.val) {
            lx = (int) (coordinates[2] - 15 * Math.cos(fi + 0.3));
            rx = (int) (coordinates[2] - 15 * Math.cos(fi - 0.3));
            ly = (int) (coordinates[3] + 15 * Math.sin(fi + 0.3));
            ry = (int) (coordinates[3] + 15 * Math.sin(fi - 0.3));
//            ly = (int) (coordinates[3] - 15 * Math.sin(fi + 0.3));
//            ry = (int) (coordinates[3] - 15 * Math.sin(fi - 0.3));
//            g.drawLine(rx, ry, coordinates[2], coordinates[3]);
//            g.drawLine(coordinates[2], coordinates[3], lx, ly);
        } else {
            lx = (int) (coordinates[2] + 15 * Math.cos(fi + 0.3));
            rx = (int) (coordinates[2] + 15 * Math.cos(fi - 0.3));
            ly = (int) (coordinates[3] + 15 * Math.sin(fi + 0.3));
            ry = (int) (coordinates[3] + 15 * Math.sin(fi - 0.3));
        }
        g.drawLine(rx, ry, coordinates[2], coordinates[3]);
        g.drawLine(coordinates[2], coordinates[3], lx, ly);

    }
}
