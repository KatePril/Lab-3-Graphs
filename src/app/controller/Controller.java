package app.controller;

import app.model.Model;
import app.model.graphVisitors.DFSVisitor;
import app.view.View;

public class Controller {
    private final Model model = new Model();
    private final View<Integer> view = new View<>();

    public void displayDirectedGraph() {
        view.printMatrix("Directed graph matrix", model.getDirectedGraphMatrix());
        view.drawDirectedGraph(model.getDirectedGraphMatrix());
//        BFSVisitor bfsVisitor = new BFSVisitor(model.getDirectedGraphMatrix());
//        bfsVisitor.visitBFS();
        DFSVisitor dfsVisitor = new DFSVisitor(model.getDirectedGraphMatrix());
        dfsVisitor.visitDFS();
    }
}
