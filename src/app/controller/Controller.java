package app.controller;

import app.model.Model;
import app.model.graphVisitors.BFSVisitor;
import app.model.graphVisitors.DFSVisitor;
import app.view.View;

public class Controller {
    private final Model model = new Model();
    private final View<Integer> view = new View<>();

    public void displayDirectedGraph() {
        view.printMatrix("Directed graph matrix", model.getDirectedGraphMatrix());
        view.drawDirectedGraph(model.getDirectedGraphMatrix());

        BFSVisitor bfsVisitor = new BFSVisitor(model.getDirectedGraphMatrix());
        view.drawDirectedGraphOfVisit("BFS", model.getDirectedGraphMatrix(), bfsVisitor);

        DFSVisitor dfsVisitor = new DFSVisitor(model.getDirectedGraphMatrix());
        view.drawDirectedGraphOfVisit("DFS", model.getDirectedGraphMatrix(), dfsVisitor);

    }
}
