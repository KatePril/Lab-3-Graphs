package app.view.graph;

import app.entity.Vertex;
import app.enums.Arrow;

import java.awt.*;

public class DirectedGraphFrame extends UndirectedGraphFrame{
    public DirectedGraphFrame(String title, Integer[][] graphMatrix) throws HeadlessException {
        super(title, graphMatrix);
    }

    @Override
    protected void paintLines(Graphics g, Vertex[] vertices, Integer[][] graphMatrix) {
        int k = 0;
        for (int i = 0; i < graphMatrix.length; i++) {
            Integer[] tmpArr = graphMatrix[i];
            for (int j = k; j < graphMatrix[0].length; j++) {
                if (tmpArr[j] == 1) {
                    if (graphMatrix[j][i] == 1) {
                        linePainter.paintLine(g, vertices[i], vertices[j], Arrow.BOTH_VERTICES);
                    } else {
                        linePainter.paintLine(g, vertices[i], vertices[j], Arrow.VERTEX_TWO);
                    }
                    continue;
                }
                if (graphMatrix[j][i] == 1) {
                    linePainter.paintLine(g, vertices[i], vertices[j], Arrow.VERTEX_ONE);
                }
            }
            k++;
        }
    }

}
