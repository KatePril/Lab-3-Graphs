package app.model;

import app.model.matrix.*;
import app.utils.Constants;

public class Model {
    private final int n;
    private final UndirectedGraphInformator undirectedGraph;
    private final DirectedGraphInformator directedGraph;
    private final DirectedGraphInformator directedGraphTwo;

    public Model() {
        this.n = calculateVerticesNumber();

        Integer[][] directedGraphMatrix = createDirectedGraphMatrix(new RandomMatrixCreator(this.n, KGenerator.getK1()).getMatrix());
        Integer[][] undirectedGraphMatrix = createUndirectedGraphMatrix(directedGraphMatrix);

        undirectedGraph = new UndirectedGraphInformator(undirectedGraphMatrix, false);
        directedGraph = new DirectedGraphInformator(directedGraphMatrix, true);

        Integer[][] directedGraphMatrixTwo = createDirectedGraphMatrix(new RandomMatrixCreator(this.n, KGenerator.getK2()).getMatrix());
        directedGraphTwo = new DirectedGraphInformator(directedGraphMatrixTwo, true);
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


    public UndirectedGraphInformator getUndirectedGraph() {
        return undirectedGraph;
    }

    public DirectedGraphInformator getDirectedGraph() {
        return directedGraph;
    }

    public DirectedGraphInformator getDirectedGraphTwo() {
        return directedGraphTwo;
    }
}
