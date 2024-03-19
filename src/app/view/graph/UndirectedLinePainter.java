package app.view.graph;

import app.model.graph.utils.Direction;
import app.entity.Node;
import app.view.graph.utils.ArrowDirection;

import java.awt.*;
import java.util.Random;

public class UndirectedLinePainter {

    private int middleIndicator;
    private final Random colorGenerator = new Random();

    public UndirectedLinePainter(int len) {
        this.middleIndicator = len / 2;
    }

    public void paintLine(Graphics g, Node nodeOne, Node nodeTwo) {
        g.setColor(new Color(0, 75, 18));
        if (nodeOne.getValue().equals(nodeTwo.getValue())) {
            paintCycleLine(g, nodeOne);
        } else if (Math.abs(nodeOne.getY() - nodeTwo.getY()) == Direction.DOWN.y) {
            paintLineDistOneY(g, nodeOne, nodeTwo);
        } else if (Math.abs(nodeOne.getValue() - nodeTwo.getValue()) == middleIndicator) {
            paintLineAvoidingMiddle(g, nodeOne, nodeTwo);
        } else if (Math.abs(nodeOne.getX() - nodeTwo.getX()) == Direction.RIGHT.x
                || Math.abs(nodeOne.getX() - nodeTwo.getX()) == Math.abs(Direction.FIRST_LEFT.x)) {
            paintLineDistOneX(g, nodeOne, nodeTwo);
        } else if (nodeOne.getX().equals(nodeTwo.getX())) {
            paintSameXLine(g, nodeOne, nodeTwo);
        } else if (nodeOne.getY().equals(nodeTwo.getY())) {
            paintSameYLine(g, nodeOne, nodeTwo);
        } else {
            paintFreeConditionLine(g, nodeOne, nodeTwo);
        }
    }

    protected int[] paintCycleLine(Graphics g, Node node) {
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
        return new int[] {x3, y3, x1, y1, ArrowDirection.NO_DIRECTION.val};
    }

    protected int[] paintLineAvoidingMiddle(Graphics g, Node nodeOne, Node nodeTwo) {
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

        if (x2 < x1) {
            return new int[]{x3, y3, x2, y2, ArrowDirection.DOWN.val};
        } else {
            return new int[]{x3, y3, x2, y2, ArrowDirection.UP.val};
        }
    }

    protected int[] paintLineDistOneX(Graphics g, Node nodeOne, Node nodeTwo) { // fix edge from 2 to 7
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

        if (x2 > x1) {
            return new int[]{x1, y1, x2, y2, ArrowDirection.DOWN.val};
        } else {
            return new int[]{x1, y1, x2, y2, ArrowDirection.UP.val};
        }
    }

    protected int[] paintLineDistOneY(Graphics g, Node nodeOne, Node nodeTwo) {
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

        if (x2 > x1) {
            return new int[]{x1, y1, x2, y2, ArrowDirection.DOWN.val};
        } else {
            return new int[]{x1, y1, x2, y2, ArrowDirection.UP.val};
        }
    }

    protected int[] paintSameXLine(Graphics g, Node nodeOne, Node nodeTwo) {
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

        if (x2 > x1) {
            return new int[]{x3, y3, x2, y2, ArrowDirection.DOWN.val};
        } else {
            return new int[]{x3, y3, x2, y2, ArrowDirection.UP.val};
        }
    }

    protected int[] paintSameYLine(Graphics g, Node nodeOne, Node nodeTwo) {
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

        if (x2 > x1) {
            return new int[]{x3, y3, x2, y2, ArrowDirection.DOWN.val};
        } else {
            return new int[]{x3, y3, x2, y2, ArrowDirection.UP.val};
        }
    }

    protected int[] paintFreeConditionLine(Graphics g, Node nodeOne, Node nodeTwo) {
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

        if (x2 > x1) {
            return new int[]{x3, y3, x2, y2, ArrowDirection.DOWN.val};
        } else {
            return new int[]{x3, y3, x2, y2, ArrowDirection.UP.val};
        }
    }
}
