package app.view;

import app.view.graph.GraphFrame;
import app.view.matrix.MatrixPrinter;

public class View {
    public void printMatrix(String title, Integer[][] matrix) {
        MatrixPrinter<Integer> matrixPrinter = new MatrixPrinter<>();
        matrixPrinter.printMatrixTitle(title);
        matrixPrinter.printMatrix(matrix);
    }

    public void drawUndirectedGraph(Integer[][] matrix) {
        new GraphFrame("Undirected graph", matrix, false);
    }

    public void drawDirectedGraph(Integer[][] matrix) {
        new GraphFrame("Directed graph", matrix, true);
    }

}
