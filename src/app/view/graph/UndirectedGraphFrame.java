package app.view.graph;

import app.model.graph.GraphCreator;
import app.entity.Vertex;
import app.utils.Constants;
import app.enums.Arrow;

import javax.swing.*;
import java.awt.*;

public class UndirectedGraphFrame extends JFrame {

    private Integer[][] graphMatrix;
    private VertexPainter vertexPainter = new VertexPainter();
    protected LinePainter linePainter;


    public UndirectedGraphFrame(String title, Integer[][] graphMatrix) throws HeadlessException {
        super(title);

        this.graphMatrix = graphMatrix;
        linePainter = new LinePainter(graphMatrix.length);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Vertex[] arr = GraphCreator.createVerticesArray(graphMatrix.length);
        paintNodes(g, arr);
        paintLines(g, arr, graphMatrix);
    }

    private void paintNodes(Graphics g, Vertex[] vertices) {
        for (Vertex vertex : vertices) {
            vertexPainter.paintNode(g, vertex);
        }
    }

    protected void paintLines(Graphics g, Vertex[] vertices, Integer[][] graphMatrix) {
        int k = 0;
        for (int i = 0; i < graphMatrix.length; i++) {
            Integer[] tmpArr = graphMatrix[i];
            for (int j = k; j < graphMatrix[0].length; j++) {
                if (tmpArr[j] == 1) {
                    linePainter.paintLine(g, vertices[i], vertices[j], Arrow.NONE);
                }
            }
            k++;
        }
    }
}
