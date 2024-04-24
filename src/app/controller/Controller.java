package app.controller;

import app.model.Model;
import app.view.View;

public class Controller {
    private final Model model = new Model();
    private final View<Integer> view = new View<>();

    public void displayDirectedGraph() {
        view.printMatrix("Undirected graph matrix", model.getUndirectedGraphMatrix());
        view.printMatrix("Weighted graph matrix", model.getWeightMatrix());
        view.drawWeightedGraph(model.getUndirectedGraphMatrix(), model.getWeightMatrix());
    }
}
