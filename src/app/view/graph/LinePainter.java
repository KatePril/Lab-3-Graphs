package app.view.graph;

import app.enums.Arrow;
import app.enums.Direction;
import app.entity.Vertex;
import app.utils.Constants;

import java.awt.*;
import java.util.Random;

public class LinePainter {

    private final int middleIndicator;
    private final Random colorGenerator = new Random();

    public LinePainter(int len) {
        this.middleIndicator = len / 2;
    }

    public void paintLine(Graphics g, Vertex vertexOne, Vertex vertexTwo, int weight, Arrow arrow) {
        g.setColor(getRandomColor());

        if (vertexOne.getValue().equals(vertexTwo.getValue())) {
            paintCycleLine(g, vertexOne, weight, arrow);
        } else if (Math.abs(vertexOne.getY() - vertexTwo.getY()) == Direction.DOWN.y) {
            paintLineDistOneY(g, vertexOne, vertexTwo, weight, arrow);
        } else if (Math.abs(vertexOne.getValue() - vertexTwo.getValue()) == middleIndicator) {
            paintLineAvoidingMiddle(g, vertexOne, vertexTwo, weight, arrow);
        }
        else if (Math.abs(vertexOne.getX() - vertexTwo.getX()) == Direction.RIGHT.x
                || Math.abs(vertexOne.getX() - vertexTwo.getX()) == Math.abs(Direction.FIRST_LEFT.x)) {
            paintLineDistOneX(g, vertexOne, vertexTwo, weight, arrow);
        }
        else if (vertexOne.getX().equals(vertexTwo.getX())) {
            paintSameXLine(g, vertexOne, vertexTwo, weight, arrow);
        } else if (vertexOne.getY().equals(vertexTwo.getY())) {
            paintSameYLine(g, vertexOne, vertexTwo, weight, arrow);
        } else {
            paintNoConditionLine(g, vertexOne, vertexTwo, weight, arrow);
        }
    }

    private void paintCycleLine(Graphics g, Vertex vertex, int weight, Arrow arrow) {
        int len = 30;

        int x1 = vertex.getX() + Constants.RADIUS;
        int y1 = vertex.getY();
        int x2 = x1 - len;
        int y2 = y1 - len;
        int x3 = x1 + len;

        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x3, y2);
        g.drawLine(x3, y2, x1, y1);
        printWeightOnCycleLine(g, weight, x2, y2);

        if (arrow != Arrow.NONE) {
            drawArrow(g, x3, y2, x1, y1, Arrow.BOTH_VERTICES);
        }
    }

    private void paintLineAvoidingMiddle(Graphics g, Vertex vertexOne, Vertex vertexTwo, int weight, Arrow arrow) {
        int x1 = vertexOne.getX() + Constants.RADIUS;
        int x2 = vertexTwo.getX() + Constants.RADIUS;
        int y1 = vertexOne.getY() + Constants.DIAMETER;
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
        printWeightOnPolygonalLine(g, weight, x3, y3);
    }

    private void paintLineDistOneX(Graphics g, Vertex vertexOne, Vertex vertexTwo, int weight, Arrow arrow) {
        if (vertexOne.getY().equals(vertexTwo.getY())) {
            int x1, x2;
            if (vertexOne.getX() < vertexTwo.getX()) {
                x1 = vertexOne.getX() + Constants.DIAMETER;
                x2 = vertexTwo.getX();

            } else {
                x1 = vertexTwo.getX() + Constants.DIAMETER;
                x2 = vertexOne.getX();
            }

            int y1 = vertexOne.getY() + Constants.RADIUS;
            int y2 = vertexTwo.getY() + Constants.RADIUS;

            if (arrow != Arrow.BOTH_VERTICES) {
                if (vertexOne.getValue() < vertexTwo.getValue())
                    arrow = Arrow.VERTEX_ONE;
                drawStraightLine(g, arrow, x1, y1, x2, y2);
                printWeightOnStraightLine(g, weight, x1, y1, x2, y2);
            } else {
                int x3 = (Math.abs(vertexOne.getX() - vertexTwo.getX()) / 2) + Math.min(vertexOne.getX(), vertexTwo.getX());
                drawPolygonalLine(g, arrow, x1, y1, x2, y2, x3, y1);
                printWeightOnPolygonalLine(g, weight, x3, y1);
            }

        } else {
            paintLineDistOneY(g, vertexOne, vertexTwo, weight, arrow);
        }
    }

    private void paintLineDistOneY(Graphics g, Vertex vertexOne, Vertex vertexTwo, int weight, Arrow arrow) {
        int x1 = vertexOne.getX() + Constants.RADIUS;
        int x2 = vertexTwo.getX() + Constants.RADIUS;

        int y1, y2;
        if (vertexOne.getY() < vertexTwo.getY()) {
            y1 = vertexOne.getY() + Constants.DIAMETER;
            y2 = vertexTwo.getY();
        } else {
            y1 = vertexOne.getY();
            y2 = vertexTwo.getY() + Constants.DIAMETER;
        }
        if (arrow != Arrow.BOTH_VERTICES) {
            drawStraightLine(g, arrow, x1, y1, x2, y2);
            printWeightOnStraightLine(g, weight, x1, y1, x2, y2);
        } else {
            paintPolygonalLineMiddleX(g, vertexOne.getX(), vertexTwo.getX(), weight, arrow, x1, x2, y1, y2);
        }

    }

    private void paintSameYLine(Graphics g, Vertex vertexOne, Vertex vertexTwo, int weight, Arrow arrow) {
        int x1 = vertexOne.getX() + Constants.RADIUS;
        int x2 = vertexTwo.getX() + Constants.RADIUS;
        int y1 = vertexOne.getY();
        int y2 = vertexTwo.getY();

        paintPolygonalLineMiddleX(g, vertexOne.getX(), vertexTwo.getX(), weight, arrow, x1, x2, y1, y2);
    }

    private void paintPolygonalLineMiddleX(Graphics g, int vertexOneX, int vertexTwoX, int weight, Arrow arrow, int x1, int x2, int y1, int y2) {
        int x3 = (Math.abs(vertexOneX - vertexTwoX) / 2) + Math.min(vertexOneX, vertexTwoX);
        int y3 = y1 - Constants.DIAMETER;
        drawPolygonalLine(g, arrow, x1, y1, x2, y2, x3, y3);
        printWeightOnPolygonalLine(g, weight, x3, y3);
    }



    private void paintSameXLine(Graphics g, Vertex vertexOne, Vertex vertexTwo, int weight, Arrow arrow) {
        int x1 = vertexOne.getX() + Constants.RADIUS;
        int x2 = vertexTwo.getX() + Constants.RADIUS;
        int y1 = vertexOne.getY();
        int y2 = vertexTwo.getY() + Constants.DIAMETER;

        int x3 = vertexOne.getX() - Constants.DIAMETER;
        int y3 = calculateY3(y1, y2);

        drawPolygonalLine(g, arrow, x1, y1, x2, y2, x3, y3);
        printWeightOnPolygonalLine(g, weight, x3, y3);
    }

    private void paintNoConditionLine(Graphics g, Vertex vertexOne, Vertex vertexTwo, int weight, Arrow arrow) {
        int x1 = vertexOne.getX() + Constants.RADIUS;
        int x2 = vertexTwo.getX() + Constants.RADIUS;
        int y1 = vertexOne.getY() + Constants.DIAMETER;
        int y2 = vertexTwo.getY();

        int x3;
        if (x1 < x2) {
            x3 = vertexOne.getX() + (Math.abs(vertexOne.getX() - vertexTwo.getX()) / 2) - 40;
        } else {
            x3 = vertexTwo.getX() + (Math.abs(vertexOne.getX() - vertexTwo.getX()) / 2) + 80;
        }
        int y3 = calculateY3(vertexOne.getY(), y2);

        drawPolygonalLine(g, arrow, x1, y1, x2, y2, x3, y3);
        printWeightOnPolygonalLine(g, weight, x3, y3);
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
        if (arrow != Arrow.BOTH_VERTICES) {
            g.drawLine(x1, y1, x3, y3);
            g.drawLine(x3, y3, x2, y2);

            executeDrawArrow(g, arrow, Arrow.VERTEX_ONE, x3, y3, x1, y1);
            executeDrawArrow(g, arrow, Arrow.VERTEX_TWO, x3, y3, x2, y2);
        } else {
            int dist = 17;
            g.drawLine(x1, y1, x3, y3 + dist);
            g.drawLine(x3, y3 + dist, x2, y2);
            executeDrawArrow(g, arrow, Arrow.VERTEX_ONE, x3, y3 + dist, x1, y1);

            g.drawLine(x1, y1, x3, y3 - dist);
            g.drawLine(x3, y3 - dist, x2, y2);
            executeDrawArrow(g, arrow, Arrow.VERTEX_TWO, x3, y3 - dist, x2, y2);
        }
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
        return (arrow.equals(Arrow.VERTEX_TWO) && checkVertexTwoAngle(angle))
                || (arrow.equals(Arrow.VERTEX_ONE) &&  checkVertexOneAngle(angle));
    }

    private boolean checkVertexTwoAngle(double angle) {
        return (angle < -74 || (angle > -65 && angle < -51) || (angle > -40 && angle < -37)
                || (angle > -30 && angle < -11) || (angle > 0 && angle < 12)
                || (angle > 16 && angle < 55) || (angle > 60 && angle < 75) || angle > 89);
    }

    private boolean checkVertexOneAngle(double angle) {
        return (angle < -30 || (angle > -26 && angle < -15) || (angle > -14 && angle < -11)
                || (angle > -10 && angle < -9) || (angle > -6 && angle < 0)
                || (angle > 11 && angle < 12) || (angle > 44 && angle < 46));
    }

    private void printWeightOnStraightLine(Graphics g, int weight, int x1, int y1, int x2, int y2) {
        int x = (Math.abs(x1 - x2) / 2) + Math.min(x1, x2);
        int y = (Math.abs(y1 - y2) / 2) + Math.min(y1, y2);
        if (x1 - x2 > 0 && Math.abs(y1 - y2) != (Constants.DISTANCE - 50)) {
            x -= 15;
            y -= 15;
        }

        g.drawString(String.valueOf(weight), x, y);
    }

    private void printWeightOnPolygonalLine(Graphics g, int weight, int x, int y) {
        g.drawString(String.valueOf(weight), x, y);
    }

    private void printWeightOnCycleLine(Graphics g, int weight, int x, int y) {
        g.drawString(String.valueOf(weight), x-3, y-1);
    }

    private Color getRandomColor() {
        return new Color(colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256));
    }
}
