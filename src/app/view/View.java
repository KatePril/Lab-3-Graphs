package app.view;

import app.view.graph.DirectedGraphFrame;
import app.view.graph.UndirectedGraphFrame;
import app.view.matrix.MatrixPrinter;

public class View<T> {
    public void printMatrix(String title, T[][] matrix) {
        MatrixPrinter<T> matrixPrinter = new MatrixPrinter<>();
        matrixPrinter.printMatrixTitle(title);
        matrixPrinter.printMatrix(matrix);
    }

    public void drawUndirectedGraph(Integer[][] matrix) {
        new UndirectedGraphFrame("Undirected graph", matrix);
    }

    public void drawDirectedGraph(Integer[][] matrix) {
        new DirectedGraphFrame("Directed graph", matrix);
    }

}
