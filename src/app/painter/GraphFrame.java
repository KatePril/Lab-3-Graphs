package app.painter;

import app.graph.GraphCreator;
import app.model.Node;

import javax.swing.*;
import java.awt.*;

public class GraphFrame extends JFrame {

    private LinePainter linePainter = new LinePainter();
    private NodePainter nodePainter = new NodePainter();

    public GraphFrame(String title) throws HeadlessException {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintNodes(g, GraphCreator.createVerticesArray(11));
//        nodePainter.paintNode(g, new Node(1, 100, 100));
//        nodePainter.paintNode(g);
//        linePainter.paintLine(g);

    }

    private void paintNodes(Graphics g, Node[] nodes) {
        for (Node node : nodes) {
            System.out.println(node);
            nodePainter.paintNode(g, node);
        }
    }
}
