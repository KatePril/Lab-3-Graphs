package app.view;

import app.model.graphVisitors.Visitor;
import app.view.graph.DirectedGraphFrame;
import app.view.graph.GraphVisitFrame;
import app.view.graph.UndirectedGraphFrame;
import app.view.forCollections.MatrixPrinter;

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

    public void drawDirectedGraphOfVisit(String title, Integer[][] matrix, Visitor visitor) {
        new GraphVisitFrame(title, matrix, visitor);
    }

}
