package app.view.graph;

import app.enums.VertexStatus;
import app.model.graph.GraphCreator;
import app.entity.Vertex;
import app.utils.ColorResources;
import app.utils.Constants;
import app.enums.Arrow;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class UndirectedGraphFrame extends JFrame {

    private Integer[][] graphMatrix;
    private VertexPainter vertexPainter = new VertexPainter();
    protected LinePainter linePainter;
    private VertexStatus[] vertexStatuses;



    public UndirectedGraphFrame(String title, Integer[][] graphMatrix) throws HeadlessException {
        super(title);

        this.graphMatrix = graphMatrix;

        linePainter = new LinePainter(graphMatrix.length);
        this.vertexStatuses = new VertexStatus[graphMatrix.length];
        Arrays.fill(vertexStatuses, VertexStatus.NEW);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Vertex[] vertices = GraphCreator.createVerticesArray(graphMatrix.length);

        vertexPainter.paintNodes(g, vertices, vertexStatuses);
        paintLines(g, vertices, graphMatrix);
    }

    protected void paintLines(Graphics g, Vertex[] vertices, Integer[][] graphMatrix) {
        int k = 0;
        for (int i = 0; i < graphMatrix.length; i++) {
            Integer[] tmpArr = graphMatrix[i];
            for (int j = k; j < graphMatrix[0].length; j++) {
                if (tmpArr[j] == 1) {
                    linePainter.paintLine(g, vertices[i], vertices[j], Arrow.NONE, getLineColor());
                }
            }
            k++;
        }
    }

    public void setGraphMatrix(Integer[][] graphMatrix) {
        this.graphMatrix = graphMatrix;
    }

    public void setVertexStatuses(VertexStatus[] vertexStatuses) {
        this.vertexStatuses = vertexStatuses;
        repaint();
    }

    protected Color getLineColor() {
        return ColorResources.getRandomColor();
    }
}
