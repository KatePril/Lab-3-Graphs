package app.controller;

import app.model.Model;
import app.model.graphVisitors.BFSVisitor;
import app.model.graphVisitors.DFSVisitor;
import app.view.View;
import app.view.graph.GraphVisitFrame;

public class Controller {
    private final Model model = new Model();
    private final View<Integer> view = new View<>();

    public void displayDirectedGraph() {
        view.printMatrix("Directed graph matrix", model.getDirectedGraphMatrix());
        view.drawDirectedGraph(model.getDirectedGraphMatrix());
        BFSVisitor bfsVisitor = new BFSVisitor(model.getDirectedGraphMatrix());
        GraphVisitFrame graphVisitBFSFrame = new GraphVisitFrame("BFS", model.getDirectedGraphMatrix(), bfsVisitor);
        DFSVisitor dfsVisitor = new DFSVisitor(model.getDirectedGraphMatrix());
//        GraphVisitFrame graphVisitDFSFrame = new GraphVisitFrame("DFS", model.getDirectedGraphMatrix(), dfsVisitor);

    }
}
