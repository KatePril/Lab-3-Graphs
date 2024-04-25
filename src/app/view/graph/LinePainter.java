package app.view.graph;

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

    public void paintLine(Graphics g, Vertex vertexOne, Vertex vertexTwo, int weight) {
        g.setColor(getRandomColor());

        System.out.printf("vertexOne = %d, vertexTwo = %d, weight = %d\n", vertexOne.getValue(), vertexTwo.getValue(), weight);
        if (vertexOne.getValue().equals(vertexTwo.getValue())) {
            System.out.println("case 1");
            paintCycleLine(g, vertexOne, weight);
        } else if (Math.abs(vertexOne.getY() - vertexTwo.getY()) == Direction.DOWN.y) {
            System.out.println("case 2");
            paintLineDistOneY(g, vertexOne, vertexTwo, weight);
        } else if (Math.abs(vertexOne.getValue() - vertexTwo.getValue()) == middleIndicator) {
            System.out.println("case 3");
            paintLineAvoidingMiddle(g, vertexOne, vertexTwo, weight);
        }
        else if (Math.abs(vertexOne.getX() - vertexTwo.getX()) == Direction.RIGHT.x
                || Math.abs(vertexOne.getX() - vertexTwo.getX()) == Math.abs(Direction.FIRST_LEFT.x)) {
            System.out.println("case 4");
            paintLineDistOneX(g, vertexOne, vertexTwo, weight);
        }
        else if (vertexOne.getX().equals(vertexTwo.getX())) {
            System.out.println("case 5");
            paintSameXLine(g, vertexOne, vertexTwo, weight);
        } else if (vertexOne.getY().equals(vertexTwo.getY())) {
            System.out.println("case 6");
            paintSameYLine(g, vertexOne, vertexTwo, weight);
        } else {
            System.out.println("case 7");
            paintNoConditionLine(g, vertexOne, vertexTwo, weight);
        }
    }

    private void paintCycleLine(Graphics g, Vertex vertex, int weight) {
        int len = 30;

        int x1 = vertex.getX() + vertex.getSIZE()/2;
        int y1 = vertex.getY();
        int x2 = x1 - len;
        int y2 = y1 - len;
        int x3 = x1 + len;

        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x3, y2);
        g.drawLine(x3, y2, x1, y1);
        printWeightOnCycleLine(g, weight, x2, y2);
    }

    private void paintLineAvoidingMiddle(Graphics g, Vertex vertexOne, Vertex vertexTwo, int weight) {
        int x1 = vertexOne.getX() + vertexOne.getSIZE() / 2;
        int x2 = vertexTwo.getX() + vertexTwo.getSIZE() / 2;
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

        drawPolygonalLine(g, x1, y1, x2, y2, x3, y3);
        printWeightOnPolygonalLine(g, weight, x3, y3);
    }

    private void paintLineDistOneX(Graphics g, Vertex vertexOne, Vertex vertexTwo, int weight) {
        if (vertexOne.getY().equals(vertexTwo.getY())) {
            int x1, x2;
            if (vertexOne.getX() < vertexTwo.getX()) {
                x1 = vertexOne.getX() + vertexOne.getSIZE();
                x2 = vertexTwo.getX();

            } else {
                x1 = vertexTwo.getX() + vertexTwo.getSIZE();
                x2 = vertexOne.getX();
            }

            int y1 = vertexOne.getY() + vertexOne.getSIZE() / 2;
            int y2 = vertexTwo.getY() + vertexOne.getSIZE() / 2;

            drawStraightLine(g, x1, y1, x2, y2);
            printWeightOnStraightLine(g, weight, x1, y1, x2, y2);
        } else {
            paintLineDistOneY(g, vertexOne, vertexTwo, weight);
        }
    }

    private void paintLineDistOneY(Graphics g, Vertex vertexOne, Vertex vertexTwo, int weight) {
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

        drawStraightLine(g,x1, y1, x2, y2);
        printWeightOnStraightLine(g, weight, x1, y1, x2, y2);
    }

    private void paintSameXLine(Graphics g, Vertex vertexOne, Vertex vertexTwo, int weight) {
        int x1 = vertexOne.getX() + vertexOne.getSIZE() / 2;
        int x2 = vertexTwo.getX() + vertexTwo.getSIZE() / 2;
        int y1 = vertexOne.getY() + vertexOne.getSIZE();
        int y2 = vertexTwo.getY();

        int x3 = vertexOne.getX() - vertexOne.getSIZE();
        int y3 = calculateY3(y1, y2);

        drawPolygonalLine(g, x1, y1, x2, y2, x3, y3);
        printWeightOnPolygonalLine(g, weight, x3, y3);
    }

    private void paintSameYLine(Graphics g, Vertex vertexOne, Vertex vertexTwo, int weight) {
        int x1 = vertexOne.getX() + vertexOne.getSIZE() / 2;
        int x2 = vertexTwo.getX() + vertexTwo.getSIZE() / 2;
        int y1 = vertexOne.getY();
        int y2 = vertexTwo.getY();

        int x3 = (Math.abs(vertexOne.getX() - vertexTwo.getX()) / 2) + Math.min(vertexOne.getX(), vertexTwo.getX());
        int y3 = y1 - vertexOne.getSIZE();

        drawPolygonalLine(g, x1, y1, x2, y2, x3, y3);
        printWeightOnPolygonalLine(g, weight, x3, y3);
    }

    private void paintNoConditionLine(Graphics g, Vertex vertexOne, Vertex vertexTwo, int weight) {
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

        drawPolygonalLine(g, x1, y1, x2, y2, x3, y3);
        printWeightOnPolygonalLine(g, weight, x3, y3);
    }

    private int calculateY3(int y1, int y2) {
        return (Math.abs(y1 - y2) / 2) + (Math.min(y1, y2));
    }

    private void drawStraightLine(Graphics g, int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);
    }

    private void drawPolygonalLine(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3) {
            g.drawLine(x1, y1, x3, y3);
            g.drawLine(x3, y3, x2, y2);
    }

    private void printWeightOnStraightLine(Graphics g, int weight, int x1, int y1, int x2, int y2) {
        int x = (Math.abs(x1 - x2) / 2) + Math.min(x1, x2);
        int y = (Math.abs(y1 - y2) / 2) + Math.min(y1, y2);
        System.out.printf("\tx1 = %d, y1 = %d;\n\tx2 = %d, y2 = %d;\n\tx = %d, y = %d;\n", x1, y1, x2, y2, x, y);
        if (x1 - x2 > 0 && Math.abs(y1 - y2) != (Constants.DISTANCE-50)) {
            x -= 15;
            y -= 15;
        }

        System.out.printf("\tx1 = %d, y1 = %d;\n\tx2 = %d, y2 = %d;\n\tx = %d, y = %d;\n", x1, y1, x2, y2, x, y);

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

    private Font getFont() {
        return  new Font("Calibry", Font.BOLD, 16);
    }
}
