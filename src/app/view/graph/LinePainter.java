package app.view.graph;

import app.model.graph.utils.Direction;
import app.entity.Vertex;
import app.view.graph.utils.Arrow;

import java.awt.*;
import java.util.Random;

public class LinePainter {

    private final int middleIndicator;
    private final Random colorGenerator = new Random();

    public LinePainter(int len) {
        this.middleIndicator = len / 2;
    }

    public void paintLine(Graphics g, Vertex vertexOne, Vertex vertexTwo, Arrow arrow) {
        g.setColor(getRandomColor());

        if (vertexOne.getValue().equals(vertexTwo.getValue())) {
            paintCycleLine(g, vertexOne, arrow);
        } else if (Math.abs(vertexOne.getY() - vertexTwo.getY()) == Direction.DOWN.y) {
            paintLineDistOneY(g, vertexOne, vertexTwo, arrow);
        } else if (Math.abs(vertexOne.getValue() - vertexTwo.getValue()) == middleIndicator) {
            paintLineAvoidingMiddle(g, vertexOne, vertexTwo, arrow);
        } else if (Math.abs(vertexOne.getX() - vertexTwo.getX()) == Direction.RIGHT.x
                || Math.abs(vertexOne.getX() - vertexTwo.getX()) == Math.abs(Direction.FIRST_LEFT.x)) {
            paintLineDistOneX(g, vertexOne, vertexTwo, arrow);
        } else if (vertexOne.getX().equals(vertexTwo.getX())) {
            paintSameXLine(g, vertexOne, vertexTwo, arrow);
        } else if (vertexOne.getY().equals(vertexTwo.getY())) {
            paintSameYLine(g, vertexOne, vertexTwo, arrow);
        } else {
            paintFreeConditionLine(g, vertexOne, vertexTwo, arrow);
        }
    }

    private void paintCycleLine(Graphics g, Vertex vertex, Arrow arrow) {
        int len = 30;

        int x1 = vertex.getX() + vertex.getSIZE()/2;
        int y1 = vertex.getY();
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

    private void paintLineAvoidingMiddle(Graphics g, Vertex vertexOne, Vertex vertexTwo, Arrow arrow) {
        int x1 = vertexOne.getX() + vertexOne.getSIZE() / 2;
        int x2 = vertexTwo.getX() + vertexTwo.getSIZE()  / 2;
        int y1 = vertexOne.getY() + vertexOne.getSIZE();
        int y2 = vertexTwo.getY();

        int x3, y3;
        int distX = 60;
        int distY = 80;

        if (x1 < x2) {
            x3 = vertexOne.getX() + (Math.abs(vertexOne.getX() - vertexTwo.getX()) / 2) - distX;
        } else {
            x3 = vertexTwo.getX() + (Math.abs(vertexOne.getX() - vertexTwo.getX()) / 2) + distX;
        }
        if (y1 < y2) {
            y3 = vertexOne.getY() + (Math.abs(vertexOne.getY() - y2) / 2) + distY;
        } else {
            y3 = y2 + (Math.abs(vertexOne.getY() - y2) / 2) - distY;
        }

        drawPolygonalLine(g, arrow, x1, y1, x2, y2, x3, y3);
    }

    private void paintLineDistOneX(Graphics g, Vertex vertexOne, Vertex vertexTwo, Arrow arrow) {
        if (vertexOne.getY().equals(vertexTwo.getY())) {
            int x1, x2, y1, y2;
            if (vertexOne.getX() < vertexTwo.getX()) {
                x1 = vertexOne.getX() + vertexOne.getSIZE();
                x2 = vertexTwo.getX();

            } else {
                x1 = vertexTwo.getX() + vertexTwo.getSIZE();
                x2 = vertexOne.getX();
            }

            y1 = vertexOne.getY() + vertexOne.getSIZE() / 2;
            y2 = vertexTwo.getY() + vertexOne.getSIZE() / 2;

            drawStraightLine(g, arrow, x1, y1, x2, y2);
        } else {
            paintLineDistOneY(g, vertexOne, vertexTwo, arrow);
        }
    }

    private void paintLineDistOneY(Graphics g, Vertex vertexOne, Vertex vertexTwo, Arrow arrow) {
        int x1 = vertexOne.getX() + vertexOne.getSIZE() / 2;
        int x2 = vertexTwo.getX() + vertexTwo.getSIZE() / 2;

        int y1, y2;
        if (vertexOne.getY() < vertexTwo.getY()) {
            y1 = vertexOne.getY() + vertexOne.getSIZE();
            y2 = vertexTwo.getY();
        } else {
            y1 = vertexOne.getY();
            y2 = vertexTwo.getY() + vertexTwo.getSIZE();
        }

        drawStraightLine(g, arrow, x1, y1, x2, y2);
    }

    private void paintSameXLine(Graphics g, Vertex vertexOne, Vertex vertexTwo, Arrow arrow) {
        int x1 = vertexOne.getX() + vertexOne.getSIZE() / 2;
        int x2 = vertexTwo.getX() + vertexTwo.getSIZE() / 2;
        int y1 = vertexOne.getY();
        int y2 = vertexTwo.getY();

        int x3 = vertexOne.getX() - vertexOne.getSIZE();
        int y3 = calculateY3(y1, y2);

        drawPolygonalLine(g, arrow, x1, y1, x2, y2, x3, y3);
    }

    private void paintSameYLine(Graphics g, Vertex vertexOne, Vertex vertexTwo, Arrow arrow) {
        int x1 = vertexOne.getX() + vertexOne.getSIZE() / 2;
        int x2 = vertexTwo.getX() + vertexTwo.getSIZE() / 2;
        int y1 = vertexOne.getY();
        int y2 = vertexTwo.getY();

        int x3 = (Math.abs(vertexOne.getX() - vertexTwo.getX()) / 2) + Math.min(vertexOne.getX(), vertexTwo.getX());
        int y3 = y1 - vertexOne.getSIZE();

        drawPolygonalLine(g, arrow, x1, y1, x2, y2, x3, y3);
    }

    private void paintFreeConditionLine(Graphics g, Vertex vertexOne, Vertex vertexTwo, Arrow arrow) {
        int x1 = vertexOne.getX() + vertexOne.getSIZE() / 2;
        int x2 = vertexTwo.getX() + vertexTwo.getSIZE() / 2;
        int y1 = vertexOne.getY() + vertexOne.getSIZE();
        int y2 = vertexTwo.getY();

        int x3;
        if (x1 < x2) {
            x3 = vertexOne.getX() + (Math.abs(vertexOne.getX() - vertexTwo.getX()) / 2) - 40;
        } else {
            x3 = vertexTwo.getX() + (Math.abs(vertexOne.getX() - vertexTwo.getX()) / 2) + 80;
        }
        int y3 = calculateY3(vertexOne.getY(), y2);

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
