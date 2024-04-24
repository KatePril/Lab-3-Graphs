package app.view.graph;

import app.entity.Vertex;

import java.awt.*;

public class WeightedGraphFrame extends UndirectedGraphFrame {

    private final Integer[][] weightMatrix;

    public WeightedGraphFrame(String title, Integer[][] graphMatrix, Integer[][] weightMatrix) throws HeadlessException {
        super(title, graphMatrix);
        this.weightMatrix = weightMatrix;
    }

    @Override
    protected void paintLines(Graphics g, Vertex[] vertices, Integer[][] graphMatrix) {
        int k = 0;
        for (int i = 0; i < graphMatrix.length; i++) {
            Integer[] tmpArr = graphMatrix[i];
            for (int j = k; j < graphMatrix[0].length; j++) {
                if (tmpArr[j] == 1) {
                    linePainter.paintLine(g, vertices[i], vertices[j], weightMatrix[i][j]);
                }
            }
            k++;
        }
    }
}