package app.controller;

import app.model.Model;
import app.view.View;

public class Controller {
    private final Model model = new Model();
    private final View<Integer> view = new View<>();

    public void displayDirectedGraph() {
        view.printMatrix("Undirected graph matrix", model.getUndirectedGraphMatrix());
        view.drawUndirectedGraph(model.getUndirectedGraphMatrix());
    }
}
