package app.view;

import app.view.graph.GraphFrame;
import app.view.matrix.MatrixPrinter;

public class View {
    public void printMatrix(Integer[][] matrix) {
        MatrixPrinter<Integer> matrixPrinter = new MatrixPrinter<>();
        matrixPrinter.printMatrix(matrix);
    }

    public void drawUndirectedGraph(Integer[][] matrix) {
        GraphFrame graphFrame = new GraphFrame("Undirected graph", matrix, false);
    }

    public void drawDirectedGraph(Integer[][] matrix) {
        GraphFrame graphFrame = new GraphFrame("Directed graph", matrix, true);
    }

}
