package app.model;

import app.model.matrix.DirectedGraphMatrixCreator;
import app.model.matrix.RandomMatrixCreator;
import app.model.matrix.UndirectedGraphMatrixCreator;
import app.utils.Constants;

public class Model {
    private final int n;
    private final UndirectedGraphInformator undirectedGraph;
    private final DirectedGraphInformator directedGraph;

    public Model() {
        this.n = calculateVerticesNumber();

        Integer[][] directedGraphMatrix = createDirectedGraphMatrix(new RandomMatrixCreator(this.n).getMatrix());
        Integer[][] undirectedGraphMatrix = createUndirectedGraphMatrix(directedGraphMatrix);

        undirectedGraph = new UndirectedGraphInformator(undirectedGraphMatrix, false);
        directedGraph = new DirectedGraphInformator(directedGraphMatrix, true);
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
}
