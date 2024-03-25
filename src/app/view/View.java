package app.view;

import app.view.graph.DirectedGraphFrame;
import app.view.graph.UndirectedGraphFrame;
import app.view.matrix.MatrixPrinter;

import java.util.ArrayList;

public class View<T> {
    public void printMatrix(String title, T[][] matrix) {
        MatrixPrinter<T> matrixPrinter = new MatrixPrinter<>();
        printTitle(title);
        matrixPrinter.printMatrix(matrix);
    }

    public void drawUndirectedGraph(Integer[][] matrix) {
        new UndirectedGraphFrame("Undirected graph", matrix);
    }

    public void drawDirectedGraph(Integer[][] matrix) {
        new DirectedGraphFrame("Directed graph", matrix);
    }

    public void printVertexPower(Integer[] vertices, String title) {
        printTitle(title);
        for (int i = 0; i < vertices.length; i++) {
            System.out.printf("Vertex %d\tpower: %d\n", i+1, vertices[i]);
        }
    }

    public void printEndVertices(ArrayList<Integer> vertices, String title) {
        printTitle(title);
        if (vertices.isEmpty()) {
            System.out.println("There is no end vertices in the given graph");
        } else {
            System.out.print("End vertices: ");
            for (Integer vertex : vertices) {
                System.out.print(vertex + " ");
            }
            System.out.println();
        }
    }

    private void printTitle(String title) {
        System.out.println(title);
    }

}
