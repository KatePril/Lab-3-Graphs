package app.painter;

import app.graph.GraphCreator;
import app.model.Node;

import javax.swing.*;
import java.awt.*;

public class GraphFrame extends JFrame {

    private Integer[][] graphMatrix;
    private NodePainter nodePainter = new NodePainter();
    private UndirectedLinePainter linePainter;


    public GraphFrame(String title, Integer[][] graphMatrix) throws HeadlessException {
        super(title);

        this.graphMatrix = graphMatrix;

        linePainter = new UndirectedLinePainter(graphMatrix.length);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setResizable(false);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Node[] arr = GraphCreator.createVerticesArray(graphMatrix.length);
        paintNodes(g, arr);
        paintLines(g, arr, graphMatrix);

    }

    private void paintNodes(Graphics g, Node[] nodes) {
        for (Node node : nodes) {
            System.out.println(node);
            nodePainter.paintNode(g, node);
        }
    }

    private void paintLines(Graphics g, Node[] nodes, Integer[][] graphMatrix) {
        int k = 0;
        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = k; j < graphMatrix[0].length; j++) {
                if (graphMatrix[i][j] == 1) {
                    linePainter.paintLine(g, nodes[i], nodes[j]);
                }
            }
            k++;
        }
    }
}
