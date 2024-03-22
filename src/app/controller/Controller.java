package app.controller;

import app.model.Model;
import app.view.View;

public class Controller {
    private final Model model = new Model();
    private final View<Integer> view = new View<>();

    public void printUndirectedGraphMatrix() {
        view.printMatrix("Undirected graph matrix", model.getUndirectedGraphMatrix());
    }

    public void printDirectedGraphMatrix() {
        view.printMatrix("Directed graph matrix", model.getDirectedGraphMatrix());
    }

    public void drawUndirectedGraphMatrix() {
        view.drawUndirectedGraph(model.getUndirectedGraphMatrix());
    }

    public void drawDirectedGraphMatrix() {
        view.drawDirectedGraph(model.getDirectedGraphMatrix());
    }
}
