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

    public void printGraphRegularity(int regularity) {
        if (regularity == -1)
            System.out.println("The graph is not regular");
        else
            System.out.printf("The graph is regular of power %d", regularity);
    }

    public void printPaths(ArrayList<ArrayList<Integer>> paths, int ttl) {
        printTitle(String.format("List of %d-step paths:", ttl));
        for (ArrayList<Integer> path : paths) {
            String[] arr = path.stream().map(el -> String.valueOf(el + 1)).toArray(String[]::new);
            String output = String.join(" - ", arr);
            System.out.println(output);
        }
    }

    public void printComponents(ArrayList<ArrayList<Integer>> components) {
        printTitle("Components");
        for (int i = 0; i < components.size(); i++) {
            System.out.printf("Component K%d:\n", i+1);
            for (int j = 0; j < components.get(i).size(); j++) {
                System.out.printf("\t%d", components.get(i).get(j));
            }
            System.out.println();
        }

    }

    private void printTitle(String title) {
        System.out.println(title);
    }

}
