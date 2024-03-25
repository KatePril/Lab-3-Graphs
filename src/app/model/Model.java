package app.model;

import app.model.graphAnalysis.VertexCalculator;
import app.model.matrix.DirectedGraphMatrixCreator;
import app.model.matrix.RandomMatrixCreator;
import app.model.matrix.UndirectedGraphMatrixCreator;
import app.utils.Constants;

import java.util.ArrayList;

public class Model {
    private final int n;
    private final GraphInformator undirectedGraph;
    private final GraphInformator directedGraph;

    public Model() {
        this.n = calculateVerticesNumber();
//        matrix = ;

        Integer[][] directedGraphMatrix = createDirectedGraphMatrix(new RandomMatrixCreator(this.n).getMatrix());
        Integer[][] undirectedGraphMatrix = createUndirectedGraphMatrix(directedGraphMatrix);

        undirectedGraph = new GraphInformator(undirectedGraphMatrix, false);
        directedGraph = new GraphInformator(directedGraphMatrix, true);
    }

    private Integer[][] createDirectedGraphMatrix(Double[][] matrix) {
        DirectedGraphMatrixCreator directedGraphMatrixCreator = new DirectedGraphMatrixCreator(matrix);
        return directedGraphMatrixCreator.getGraphMatrix();
    }

    private Integer[][] createUndirectedGraphMatrix(Integer[][] directedGraphMatrix) {
        UndirectedGraphMatrixCreator undirectedGraphMatrixCreator = new UndirectedGraphMatrixCreator(directedGraphMatrix);
        return undirectedGraphMatrixCreator.getGraphMatrix();
    }

    private int calculateVerticesNumber() {
        return 10 + Constants.n3;
    }

    public int getN() {
        return n;
    }


    public GraphInformator getUndirectedGraph() {
        return undirectedGraph;
    }

    public GraphInformator getDirectedGraph() {
        return directedGraph;
    }
}
