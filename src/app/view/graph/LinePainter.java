package app.view.graph;

import app.model.graph.utils.Direction;
import app.entity.Node;
import app.view.graph.utils.Arrow;

import java.awt.*;
import java.util.Random;

public class LinePainter {

    private final int middleIndicator;
    private final Random colorGenerator = new Random();

    public LinePainter(int len) {
        this.middleIndicator = len / 2;
    }

    public void paintLine(Graphics g, Node nodeOne, Node nodeTwo, Arrow arrow) {
        g.setColor(getRandomColor());

        if (nodeOne.getValue().equals(nodeTwo.getValue())) {
            paintCycleLine(g, nodeOne, arrow);
        } else if (Math.abs(nodeOne.getY() - nodeTwo.getY()) == Direction.DOWN.y) {
            paintLineDistOneY(g, nodeOne, nodeTwo, arrow);
        } else if (Math.abs(nodeOne.getValue() - nodeTwo.getValue()) == middleIndicator) {
            paintLineAvoidingMiddle(g, nodeOne, nodeTwo, arrow);
        } else if (Math.abs(nodeOne.getX() - nodeTwo.getX()) == Direction.RIGHT.x
                || Math.abs(nodeOne.getX() - nodeTwo.getX()) == Math.abs(Direction.FIRST_LEFT.x)) {
            paintLineDistOneX(g, nodeOne, nodeTwo, arrow);
        } else if (nodeOne.getX().equals(nodeTwo.getX())) {
            paintSameXLine(g, nodeOne, nodeTwo, arrow);
        } else if (nodeOne.getY().equals(nodeTwo.getY())) {
            paintSameYLine(g, nodeOne, nodeTwo, arrow);
        } else {
            paintFreeConditionLine(g, nodeOne, nodeTwo, arrow);
        }
    }

    private void paintCycleLine(Graphics g, Node node, Arrow arrow) {
        int len = 30;

        int x1 = node.getX() + node.getSIZE()/2;
        int y1 = node.getY();
        int x2 = x1 - len;
        int y2 = y1 - len;

        int x3 = x1 + len;

        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x3, y2);
        g.drawLine(x3, y2, x1, y1);

        if (arrow != Arrow.NONE) {
            drawArrow(g, x3, y2, x1, y1, Arrow.BOTH_VERTICES);
        }
    }

    private void paintLineAvoidingMiddle(Graphics g, Node nodeOne, Node nodeTwo, Arrow arrow) {
        int x1 = nodeOne.getX() + nodeOne.getSIZE() / 2;
        int x2 = nodeTwo.getX() + nodeTwo.getSIZE() / 2;
        int y1 = nodeOne.getY() + nodeOne.getSIZE();
        int y2 = nodeTwo.getY();

        int x3, y3;
        int distX = 60;
        int distY = 80;

        if (x1 < x2) {
            x3 = nodeOne.getX() + (Math.abs(nodeOne.getX() - nodeTwo.getX()) / 2) - distX;
        } else {
            x3 = nodeTwo.getX() + (Math.abs(nodeOne.getX() - nodeTwo.getX()) / 2) + distX;
        }
        if (y1 < y2) {
            y3 = nodeOne.getY() + (Math.abs(nodeOne.getY() - y2) / 2) + distY;
        } else {
            y3 = y2 + (Math.abs(nodeOne.getY() - y2) / 2) - distY;
        }

        drawPolygonalLine(g, arrow, x1, y1, x2, y2, x3, y3);
    }

    private void paintLineDistOneX(Graphics g, Node nodeOne, Node nodeTwo, Arrow arrow) {
        if (nodeOne.getY().equals(nodeTwo.getY())) {
            int x1, x2, y1, y2;
            if (nodeOne.getX() < nodeTwo.getX()) {
                x1 = nodeOne.getX() + nodeOne.getSIZE();
                x2 = nodeTwo.getX();

            } else {
                x1 = nodeTwo.getX() + nodeTwo.getSIZE();
                x2 = nodeOne.getX();
            }

            y1 = nodeOne.getY() + nodeOne.getSIZE() / 2;
            y2 = nodeTwo.getY() + nodeOne.getSIZE() / 2;

            drawStraightLine(g, arrow, x1, y1, x2, y2);
        } else {
            paintLineDistOneY(g, nodeOne, nodeTwo, arrow);
        }
    }

    private void paintLineDistOneY(Graphics g, Node nodeOne, Node nodeTwo, Arrow arrow) {
        int x1 = nodeOne.getX() + nodeOne.getSIZE() / 2;
        int x2 = nodeTwo.getX() + nodeTwo.getSIZE() / 2;

        int y1, y2;
        if (nodeOne.getY() < nodeTwo.getY()) {
            y1 = nodeOne.getY() + nodeOne.getSIZE();
            y2 = nodeTwo.getY();
        } else {
            y1 = nodeOne.getY();
            y2 = nodeTwo.getY() + nodeTwo.getSIZE();
        }

        drawStraightLine(g, arrow, x1, y1, x2, y2);
    }

    private void paintSameXLine(Graphics g, Node nodeOne, Node nodeTwo, Arrow arrow) {
        int x1 = nodeOne.getX() + nodeOne.getSIZE() / 2;
        int x2 = nodeTwo.getX() + nodeTwo.getSIZE() / 2;
        int y1 = nodeOne.getY();
        int y2 = nodeTwo.getY();

        int x3 = nodeOne.getX() - nodeOne.getSIZE();
        int y3 = calculateY3(y1, y2);

        drawPolygonalLine(g, arrow, x1, y1, x2, y2, x3, y3);
    }

    private void paintSameYLine(Graphics g, Node nodeOne, Node nodeTwo, Arrow arrow) {
        int x1 = nodeOne.getX() + nodeOne.getSIZE() / 2;
        int x2 = nodeTwo.getX() + nodeTwo.getSIZE() / 2;
        int y1 = nodeOne.getY();
        int y2 = nodeTwo.getY();

        int x3 = (Math.abs(nodeOne.getX() - nodeTwo.getX()) / 2) + Math.min(nodeOne.getX(), nodeTwo.getX());
        int y3 = y1 - nodeOne.getSIZE();

        drawPolygonalLine(g, arrow, x1, y1, x2, y2, x3, y3);
    }

    private void paintFreeConditionLine(Graphics g, Node nodeOne, Node nodeTwo, Arrow arrow) {
        int x1 = nodeOne.getX() + nodeOne.getSIZE() / 2;
        int x2 = nodeTwo.getX() + nodeTwo.getSIZE() / 2;
        int y1 = nodeOne.getY() + nodeOne.getSIZE();
        int y2 = nodeTwo.getY();

        int x3;
        if (x1 < x2) {
            x3 = nodeOne.getX() + (Math.abs(nodeOne.getX() - nodeTwo.getX()) / 2) - 40;
        } else {
            x3 = nodeTwo.getX() + (Math.abs(nodeOne.getX() - nodeTwo.getX()) / 2) + 80;
        }
        int y3 = calculateY3(nodeOne.getY(), y2);

        drawPolygonalLine(g, arrow, x1, y1, x2, y2, x3, y3);
    }

    private int calculateY3(int y1, int y2) {
        return (Math.abs(y1 - y2) / 2) + (Math.min(y1, y2));
    }

    private void drawStraightLine(Graphics g, Arrow arrow, int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);

        executeDrawArrow(g, arrow, Arrow.VERTEX_ONE, x2, y2, x1, y1);
        executeDrawArrow(g, arrow, Arrow.VERTEX_TWO, x1, y1, x2, y2);
    }

    private void  drawPolygonalLine(Graphics g, Arrow arrow, int x1, int y1, int x2, int y2, int x3, int y3) {
        g.drawLine(x1, y1, x3, y3);
        g.drawLine(x3, y3, x2, y2);

        executeDrawArrow(g, arrow, Arrow.VERTEX_ONE, x3, y3, x1, y1);
        executeDrawArrow(g, arrow, Arrow.VERTEX_TWO, x3, y3, x2, y2);
    }

    private void executeDrawArrow(Graphics g, Arrow arrow, Arrow checkArrow, int x1, int y1, int x2, int y2) {
        if (arrow.equals(checkArrow) || arrow.equals(Arrow.BOTH_VERTICES)) {
            drawArrow(g, x1, y1, x2, y2, checkArrow);
        }
    }

    private void drawArrow(Graphics g, int x1, int y1, int x2, int y2, Arrow arrow) {
        int arrowLen = 30;
        double angle;
        if (x2 - x1 == 0) {
            angle = 90.0;
        } else {
            double slope = (double) (y2 - y1) / (x2 - x1);
            angle = Math.toDegrees(Math.atan(slope));
        }
        double fi = Math.PI * (180.0 - angle) / 180.0;

        int xCoefficient, yCoefficient;
        if (rotateArrow(arrow, angle)) {
            yCoefficient = -1;
            xCoefficient = 1;
        } else {
            yCoefficient = 1;
            if (angle == 0) {
                xCoefficient = x1 > x2 ? -1 : 1;
            } else {
                xCoefficient = -1;
            }
        }

        int ly = (int) (y2 + yCoefficient * arrowLen * Math.sin(fi + 0.3));
        int ry = (int) (y2 + yCoefficient * arrowLen * Math.sin(fi - 0.3));
        int lx = (int) (x2 + xCoefficient * arrowLen * Math.cos(fi + 0.3));
        int rx = (int) (x2 + xCoefficient * arrowLen * Math.cos(fi - 0.3));

        g.drawLine(rx, ry, x2, y2);
        g.drawLine(x2, y2, lx, ly);
    }

    private boolean rotateArrow(Arrow arrow, double angle) {
        return (arrow.equals(Arrow.VERTEX_TWO) &&
                !((angle < 60 && angle > 45) || (angle > -11 && angle < 15) || (angle > -45 && angle < -30)))
                || arrow.equals(Arrow.VERTEX_ONE)
                && (angle < -30 || (angle > -26 && angle < -16) || (angle > -15 && angle < 0));
    }

    private Color getRandomColor() {
        return new Color(colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256));
    }
}
