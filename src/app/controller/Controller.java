package app.controller;

import app.model.Model;
import app.view.View;

public class Controller {
    private final Model model = new Model();
    private final View<Integer> view = new View<>();

    public void printUndirectedGraphMatrix() {
        view.printMatrix("Undirected graph matrix", model.getUndirectedGraph().getAdjacencyMatrix());
    }

    public void printDirectedGraphMatrix() {
        view.printMatrix("Directed graph matrix", model.getDirectedGraph().getAdjacencyMatrix());
    }

    public void drawUndirectedGraphMatrix() {
        view.drawUndirectedGraph(model.getUndirectedGraph().getAdjacencyMatrix());
    }

    public void drawDirectedGraphMatrix() {
        view.drawDirectedGraph(model.getDirectedGraph().getAdjacencyMatrix());
    }

    public void calculateUndirectedGraphVerticesPower() {
        view.printVertexPower(model.getUndirectedGraph().getVerticesPower(), "Power of vertices (undirected graph):");
    }

    public void calculateDirectedGraphVerticesPower() {
        view.printVertexPower(model.getDirectedGraph().getVerticesPower(), "Power of vertices (directed graph):");
    }

    public void findEndVertexUndirectedGraph() {
        view.printEndVertices(model.getUndirectedGraph().getEndVertices(), "End vertices of undirected graph:");
    }

    public void findEndVertexDirectedGraph() {
        view.printEndVertices(model.getDirectedGraph().getEndVertices(), "End vertices of directed graph:");
    }
}
