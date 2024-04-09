package app.controller;

import app.model.Model;
import app.model.graphVisitors.BFSVisitor;
import app.model.graphVisitors.DFSVisitor;
import app.model.table.Table;
import app.view.View;
import app.view.graph.GraphVisitFrame;

import java.util.Queue;

public class Controller {
    private final Model model = new Model();
    private final View<Integer> view = new View<>();

    public void displayDirectedGraph() {
        view.printMatrix("Directed graph matrix", model.getDirectedGraphMatrix());
        view.drawDirectedGraph(model.getDirectedGraphMatrix());
        BFSVisitor bfsVisitor = new BFSVisitor(model.getDirectedGraphMatrix());
//        bfsVisitor.visit();
        DFSVisitor dfsVisitor = new DFSVisitor(model.getDirectedGraphMatrix());
//        dfsVisitor.visit();
//        Table<Queue<Integer>> table = new Table<>("Queue");
        GraphVisitFrame graphVisitBFSFrame = new GraphVisitFrame("BFS", model.getDirectedGraphMatrix(), bfsVisitor);
//        GraphVisitFrame graphVisitDFSFrame = new GraphVisitFrame("DFS", model.getDirectedGraphMatrix(), dfsVisitor);

    }
}
