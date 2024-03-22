package app.view.graph;

import app.model.graph.GraphCreator;
import app.entity.Node;
import app.view.graph.utils.Arrow;

import javax.swing.*;
import java.awt.*;

public class UndirectedGraphFrame extends JFrame {

    private Integer[][] graphMatrix;
    private NodePainter nodePainter = new NodePainter();
    protected LinePainter linePainter;


    public UndirectedGraphFrame(String title, Integer[][] graphMatrix) throws HeadlessException {
        super(title);

        this.graphMatrix = graphMatrix;

        linePainter = new LinePainter(graphMatrix.length);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
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
            nodePainter.paintNode(g, node);
        }
    }

    protected void paintLines(Graphics g, Node[] nodes, Integer[][] graphMatrix) {
        int k = 0;
        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = k; j < graphMatrix[0].length; j++) {
                if (graphMatrix[i][j] == 1) {
                    linePainter.paintLine(g, nodes[i], nodes[j], Arrow.NONE);
                }
            }
            k++;
        }
    }
}
