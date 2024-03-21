package app.view.graph;

import app.model.graph.utils.Direction;
import app.entity.Node;
import app.view.graph.utils.Arrow;

import java.awt.*;
import java.util.Random;
import java.util.RandomAccess;

public class UndirectedLinePainter {

    private int middleIndicator;
    private final Random colorGenerator = new Random();

    public UndirectedLinePainter(int len) {
        this.middleIndicator = len / 2;
    }

    public void paintLine(Graphics g, Node nodeOne, Node nodeTwo, Arrow arrow) {
        g.setColor(new Color(0, 75, 18));

        System.out.printf("\nnodeOne = %d, nodeTwo = %d\n arrow = %s\n", nodeOne.getValue(), nodeTwo.getValue(), arrow.name());
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

    protected void paintCycleLine(Graphics g, Node node, Arrow arrow) {
        int len = 30;
        int x1 = node.getX() + node.getSIZE()/2;
        int y1 = node.getY();

        int x2 = x1 - len;
        int y2 = y1 - len;

        int x3 = x1 + len;
        int y3 = y1 - len;

        g.setColor(new Color(colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256)));
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x3, y3);
        g.drawLine(x3, y3, x1, y1);

        if (arrow != Arrow.NONE) {
            drawArrow(g, x3, y3, x1, y1, Arrow.BOTH_VERTICES);
        }
    }

    protected void paintLineAvoidingMiddle(Graphics g, Node nodeOne, Node nodeTwo, Arrow arrow) {
        int x1 = nodeOne.getX() + nodeOne.getSIZE()/2;
        int x2 = nodeTwo.getX() + nodeTwo.getSIZE()/2;
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
            y3 = nodeOne.getY() + (Math.abs(nodeOne.getY() - nodeTwo.getY()) / 2) + distY;
        } else {
            y3 = nodeTwo.getY() + (Math.abs(nodeOne.getY() - nodeTwo.getY()) / 2) - distY;
        }

        g.setColor(new Color(colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256)));
        g.drawLine(x1, y1, x3, y3);
        g.drawLine(x3, y3, x2, y2);

        if (arrow.equals(Arrow.VERTEX_ONE) || arrow.equals(Arrow.BOTH_VERTICES)) {
            drawArrow(g, x3, y3, x1, y1, Arrow.VERTEX_ONE);
        }
        if (arrow.equals(Arrow.VERTEX_TWO) || arrow.equals(Arrow.BOTH_VERTICES)) {
            drawArrow(g, x3, y3, x2, y2, Arrow.VERTEX_TWO);
        }
    }

    protected void paintLineDistOneX(Graphics g, Node nodeOne, Node nodeTwo, Arrow arrow) { // fix edge from 2 to 7
        int x1, x2, y1, y2;
        if (nodeOne.getY().equals(nodeTwo.getY())) {
            if (nodeOne.getX() < nodeTwo.getX()) {
                x1 = nodeOne.getX() + nodeOne.getSIZE();
                x2 = nodeTwo.getX();

            } else {
                x1 = nodeTwo.getX() + nodeTwo.getSIZE();
                x2 = nodeOne.getX();
            }

            y1 = nodeOne.getY() + (nodeOne.getSIZE() / 2);
            y2 = nodeTwo.getY() + (nodeOne.getSIZE() / 2);

        } else {
            x1 = nodeOne.getX() + nodeOne.getSIZE() / 2;
            x2 = nodeTwo.getX() + nodeOne.getSIZE() / 2;

            if (nodeOne.getY() < nodeTwo.getY()) {
                y1 = nodeOne.getY() + (nodeOne.getSIZE());
                y2 = nodeTwo.getY();
            } else {
                y1 = nodeOne.getY();
                y2 = nodeTwo.getY() + nodeTwo.getSIZE();
            }
        }

        g.setColor(new Color(colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256)));
        g.drawLine(x1, y1, x2, y2);

        if (arrow.equals(Arrow.VERTEX_ONE) || arrow.equals(Arrow.BOTH_VERTICES)) {
            drawArrow(g, x2, y2, x1, y1, Arrow.VERTEX_ONE);
        }
        if (arrow.equals(Arrow.VERTEX_TWO) || arrow.equals(Arrow.BOTH_VERTICES)) {
            drawArrow(g, x1, y1, x2, y2, Arrow.VERTEX_TWO);
        }
    }

    protected void paintLineDistOneY(Graphics g, Node nodeOne, Node nodeTwo, Arrow arrow) {
        int x1, x2, y1, y2;

        x1 = nodeOne.getX() + nodeOne.getSIZE() / 2;
        x2 = nodeTwo.getX() + nodeTwo.getSIZE() / 2;

        if (nodeOne.getY() < nodeTwo.getY()) {
            y1 = nodeOne.getY() + nodeOne.getSIZE();
            y2 = nodeTwo.getY();
//            System.out.println("case 1:");
        } else {
            y1 = nodeOne.getY();
            y2 = nodeTwo.getY() + nodeTwo.getSIZE();
//            System.out.println("case 2:");
        }

//        System.out.printf("nodeOne = %d, nodeTwo = %d\n", nodeOne.getValue(), nodeTwo.getValue());
//        System.out.printf("x1 = %d, y1 = %d, x2 = %d, y2 = %d\n", x1, y1, x2, y2);

        g.setColor(new Color(colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256)));
        g.drawLine(x1, y1, x2, y2);

        if (arrow.equals(Arrow.VERTEX_ONE) || arrow.equals(Arrow.BOTH_VERTICES)) {
            drawArrow(g, x2, y2, x1, y1, Arrow.VERTEX_ONE);
        }
        if (arrow.equals(Arrow.VERTEX_TWO) || arrow.equals(Arrow.BOTH_VERTICES)) {
            drawArrow(g, x1, y1, x2, y2, Arrow.VERTEX_TWO);
        }
    }

    protected void paintSameXLine(Graphics g, Node nodeOne, Node nodeTwo, Arrow arrow) {
        int x1 = nodeOne.getX() + nodeOne.getSIZE() / 2;
        int x2 = nodeTwo.getX() + nodeTwo.getSIZE() / 2;
        int y1 = nodeOne.getY();
        int y2 = nodeTwo.getY();

        int x3 = nodeOne.getX() - 50;
        int y3;
        if (y1 < y2) {
            y3 = nodeOne.getY() + (Math.abs(nodeOne.getY() - nodeTwo.getY()) / 2);
        } else {
            y3 = nodeTwo.getY() + (Math.abs(nodeOne.getY() - nodeTwo.getY()) / 2);
        }

        g.setColor(new Color(colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256)));

        g.drawLine(x1, y1, x3, y3);
        g.drawLine(x3, y3, x2, y2);

        if (arrow.equals(Arrow.VERTEX_ONE) || arrow.equals(Arrow.BOTH_VERTICES)) {
            drawArrow(g, x3, y3, x1, y1, Arrow.VERTEX_ONE);
        }
        if (arrow.equals(Arrow.VERTEX_TWO) || arrow.equals(Arrow.BOTH_VERTICES)) {
            drawArrow(g, x3, y3, x2, y2, Arrow.VERTEX_TWO);
        }

    }

    protected void paintSameYLine(Graphics g, Node nodeOne, Node nodeTwo, Arrow arrow) {
        int x1 = nodeOne.getX() + nodeOne.getSIZE() / 2;
        int x2 = nodeTwo.getX() + nodeTwo.getSIZE() / 2;
        int y1 = nodeOne.getY();
        int y2 = nodeTwo.getY();

        int x3;
        if (x1 < x2) {
            x3 = nodeOne.getX() + (Math.abs(nodeOne.getX() - nodeTwo.getX()) / 2);
        } else {
            x3 = nodeTwo.getX() + (Math.abs(nodeOne.getX() - nodeTwo.getX()) / 2);
        }
        int y3 = nodeOne.getY() - 50;

        g.setColor(new Color(colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256)));
        g.drawLine(x1, y1, x3, y3);
        g.drawLine(x3, y3, x2, y2);

        if (arrow.equals(Arrow.VERTEX_ONE) || arrow.equals(Arrow.BOTH_VERTICES)) {
            drawArrow(g, x3, y3, x1, y1, Arrow.VERTEX_ONE);
        }
        if (arrow.equals(Arrow.VERTEX_TWO) || arrow.equals(Arrow.BOTH_VERTICES)) {
            drawArrow(g, x3, y3, x2, y2, Arrow.VERTEX_TWO);
        }
    }

    protected void paintFreeConditionLine(Graphics g, Node nodeOne, Node nodeTwo, Arrow arrow) {
        int x1, x2, y1, y2;

        x1 = nodeOne.getX() + nodeOne.getSIZE() / 2;
        x2 = nodeTwo.getX() + nodeTwo.getSIZE() / 2;
        y1 = nodeOne.getY() + nodeOne.getSIZE();
        y2 = nodeTwo.getY();

        int x3, y3;

        if (x1 < x2) {
            x3 = nodeOne.getX() + (Math.abs(nodeOne.getX() - nodeTwo.getX()) / 2) - 40;
        } else {
            x3 = nodeTwo.getX() + (Math.abs(nodeOne.getX() - nodeTwo.getX()) / 2) + 80;
        }
        if (y1 < y2) {
            y3 = nodeOne.getY() + (Math.abs(nodeOne.getY() - nodeTwo.getY()) / 2);
        } else {
            y3 = nodeTwo.getY() + (Math.abs(nodeOne.getY() - nodeTwo.getY()) / 2);
        }

        System.out.printf("nodeOne = %d, nodeTwo = %d\n", nodeOne.getValue(), nodeTwo.getValue());
        System.out.printf("x1 = %d, y1 = %d, x2 = %d, y2 = %d\n", x1, y1, x2, y2);
        System.out.printf("x3 = %d, y3 = %d\n", x3, y3);

        g.setColor(new Color(colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256)));
        g.drawLine(x1, y1, x3, y3);
        g.drawLine(x3, y3, x2, y2);

        if (arrow.equals(Arrow.VERTEX_ONE) || arrow.equals(Arrow.BOTH_VERTICES)) {
            drawArrow(g, x3, y3, x1, y1, Arrow.VERTEX_ONE);
        }
        if (arrow.equals(Arrow.VERTEX_TWO) || arrow.equals(Arrow.BOTH_VERTICES)) {
            drawArrow(g, x3, y3, x2, y2, Arrow.VERTEX_TWO);
        }
    }

    private void drawArrow(Graphics g, int x1, int y1, int x2, int y2, Arrow arrow) {
        System.out.println("Enter");
        System.out.printf("arrow = %s\n", arrow.name());// {}
        int arrowLen = 30;
        double angle;
        if (x2 - x1 == 0) {
            angle = 90.0;
        } else {
            double slope = (double) (y2 - y1) / (x2 - x1);
//            angle = Math.abs(Math.toDegrees(Math.atan(slope)));
            angle = Math.toDegrees(Math.atan(slope));
        }
        System.out.printf("angle = %f\n", angle);
        double fi = Math.PI * (180.0 - angle) / 180.0;
        int lx,ly,rx,ry;
        if (angle == 0) {
            ly = (int) (y2 + arrowLen * Math.sin(fi + 0.3));
            ry = (int) (y2 + arrowLen * Math.sin(fi - 0.3));
            if (x1 > x2) {
                lx = (int) (x2 - arrowLen * Math.cos(fi + 0.3));
                rx = (int) (x2 - arrowLen * Math.cos(fi - 0.3));
            } else {
                lx = (int) (x2 + arrowLen * Math.cos(fi + 0.3));
                rx = (int) (x2 + arrowLen * Math.cos(fi - 0.3));
            }

        }
        else if (arrow.equals(Arrow.VERTEX_TWO)) {
            ly = (int) (y2 - arrowLen * Math.sin(fi + 0.3));
            ry = (int) (y2 - arrowLen * Math.sin(fi - 0.3));
            lx = (int) (x2 + arrowLen * Math.cos(fi + 0.3));
            rx = (int) (x2 + arrowLen * Math.cos(fi - 0.3));
            if ((angle < 60 && angle > 45) || (angle > -11 && angle < 15) || (angle > -45 && angle < -30)) {
                ly = (int) (y2 + arrowLen * Math.sin(fi + 0.3));
                ry = (int) (y2 + arrowLen * Math.sin(fi - 0.3));
                lx = (int) (x2 - arrowLen * Math.cos(fi + 0.3));
                rx = (int) (x2 - arrowLen * Math.cos(fi - 0.3));
            }

        }
        else if (arrow.equals(Arrow.VERTEX_ONE) && (angle < -30 || (angle > -26 && angle < -16) || (angle > -15 && angle < 0))) {
            ly = (int) (y2 - arrowLen * Math.sin(fi + 0.3));
            ry = (int) (y2 - arrowLen * Math.sin(fi - 0.3));
            lx = (int) (x2 + arrowLen * Math.cos(fi + 0.3));
            rx = (int) (x2 + arrowLen * Math.cos(fi - 0.3));
        }
        else {
            ly = (int) (y2 + arrowLen * Math.sin(fi + 0.3));
            ry = (int) (y2 + arrowLen * Math.sin(fi - 0.3));
            lx = (int) (x2 - arrowLen * Math.cos(fi + 0.3));
            rx = (int) (x2 - arrowLen * Math.cos(fi - 0.3));
        }
//        if (coordinates[4] == Arrow.UP.val) {
//            lx = (int) (coordinates[2] - 15 * Math.cos(fi + 0.3));
//            rx = (int) (coordinates[2] - 15 * Math.cos(fi - 0.3));
//            ly = (int) (coordinates[3] + 15 * Math.sin(fi + 0.3));
//            ry = (int) (coordinates[3] + 15 * Math.sin(fi - 0.3));
////            ly = (int) (coordinates[3] - 15 * Math.sin(fi + 0.3));
////            ry = (int) (coordinates[3] - 15 * Math.sin(fi - 0.3));
////            g.drawLine(rx, ry, coordinates[2], coordinates[3]);
////            g.drawLine(coordinates[2], coordinates[3], lx, ly);
//        } else {

//        }

        System.out.printf("px = %d, py = %d\nrx = %d, ry = %d\nlx = %d, ly = %d\n", x2, y2, rx, ry, lx, ly);
        g.drawLine(rx, ry, x2, y2);
        g.drawLine(x2, y2, lx, ly);

    }
}
