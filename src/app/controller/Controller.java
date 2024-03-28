package app.controller;

import app.model.Model;
import app.model.matrix.MatrixCalculator;
import app.view.View;
import app.view.matrix.MatrixPrinter;

public class Controller {
    private final Model model = new Model();
    private final View<Integer> view = new View<>();

    public void displayUndirectedGraphInfo() {
        //1.1-4
        view.printMatrix("Undirected graph matrix", model.getUndirectedGraph().getAdjacencyMatrix());
        view.drawUndirectedGraph(model.getUndirectedGraph().getAdjacencyMatrix());
        //2.1
        view.printVertexPower(model.getUndirectedGraph().getVerticesPower(), "Power of vertices (undirected graph):");
        //2.3
        view.printGraphRegularity(model.getUndirectedGraph().getGraphRegularity());
        //2.4
        view.printEndVertices(model.getUndirectedGraph().getEndVertices(), "End vertices of undirected graph:");
    }

    public void displayDirectedGraphInfo() {
        //1.1-4
        view.printMatrix("Directed graph matrix", model.getDirectedGraph().getAdjacencyMatrix());
        view.drawDirectedGraph(model.getDirectedGraph().getAdjacencyMatrix());
        //2.1
        view.printVertexPower(model.getDirectedGraph().getVerticesPower(), "Power of vertices (directed graph):");
        //2.2
        view.printVertexPower(model.getDirectedGraph().getPositivePowerVertex(), "Positive power of vertices (directed graph):");
        view.printVertexPower(model.getDirectedGraph().getNegativePowerVertex(), "Negative power of vertices (directed graph):");
        //2.3
        view.printGraphRegularity(model.getDirectedGraph().getGraphRegularity());
        //2.4
        view.printEndVertices(model.getDirectedGraph().getEndVertices(), "End vertices of directed graph:");
    }

    public void displayDirectedGraphTwoInfo() {
        //1.1-4
        view.printMatrix("Directed graph matrix №2", model.getDirectedGraphTwo().getAdjacencyMatrix());
        view.drawDirectedGraph(model.getDirectedGraphTwo().getAdjacencyMatrix());
        //4.1
        view.printVertexPower(model.getDirectedGraphTwo().getPositivePowerVertex(), "Positive power of vertices (directed graph 2):");
        view.printVertexPower(model.getDirectedGraphTwo().getNegativePowerVertex(), "Negative power of vertices (directed graph 2):");
        //4.2 FIX
        view.printMatrix("matrix of two-step paths", model.getDirectedGraphTwo().getTwoStepPathMatrix());
        view.printMatrix("matrix of three-step paths", model.getDirectedGraphTwo().getThreeStepPathMatrix());
        view.printPaths(model.getDirectedGraphTwo().findNStepPaths(2), 2);
        //4.3
        view.printMatrix("Matrix of reachability", model.getDirectedGraphTwo().getMatrixOfReachability());
        view.printMatrix("Transposed of reachability", MatrixCalculator.transposeMatrix(model.getDirectedGraphTwo().getMatrixOfReachability()));
        //4.4
        view.printMatrix("Matrix of strong connections", model.getDirectedGraphTwo().getMatrixOfStrongConnections());
        System.out.println("Moved matrix of strong connections");
        MatrixPrinter<Integer> matrixPrinter = new MatrixPrinter<>();
        matrixPrinter.printMovedMatrix(model.getDirectedGraphTwo().moveMatrixOfStrongConnections());
        view.printComponents(model.getDirectedGraphTwo().getComponents());

    }
}
