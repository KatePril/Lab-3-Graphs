package app.view.graph;

import app.entity.Node;
import app.view.graph.utils.Arrow;

import java.awt.*;

public class DirectedGraphFrame extends UndirectedGraphFrame{
    public DirectedGraphFrame(String title, Integer[][] graphMatrix) throws HeadlessException {
        super(title, graphMatrix);
    }

    @Override
    protected void paintLines(Graphics g, Node[] nodes, Integer[][] graphMatrix) {
        int k = 0;
        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = k; j < graphMatrix[0].length; j++) {
                if (graphMatrix[i][j] == 1) {
                    if (graphMatrix[j][i] == 1) {
                        linePainter.paintLine(g, nodes[i], nodes[j], Arrow.BOTH_VERTICES);
                    } else {
                        linePainter.paintLine(g, nodes[i], nodes[j], Arrow.VERTEX_ONE);
                    }
                    continue;
                }
                if (graphMatrix[j][i] == 1) {
                    linePainter.paintLine(g, nodes[i], nodes[j], Arrow.VERTEX_TWO);
                }
            }
            k++;
        }
    }
}
