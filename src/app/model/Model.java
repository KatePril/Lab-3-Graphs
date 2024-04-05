package app.model;

import app.model.matrix.dataSuppliers.DirectedGraphMatrixCreator;
import app.model.matrix.dataSuppliers.KGenerator;
import app.model.matrix.dataSuppliers.RandomMatrixCreator;
import app.model.matrix.dataSuppliers.UndirectedGraphMatrixCreator;
import app.utils.Constants;

public class Model {
    private final int n;
    private final Double[][] matrix;
    private final Integer[][] directedGraphMatrix;
    private final Integer[][] undirectedGraphMatrix;

    public Model() {
        this.n = calculateVerticesNumber();
        matrix = new RandomMatrixCreator(this.n, KGenerator.getK()).getMatrix();

        directedGraphMatrix = createDirectedGraphMatrix();
        undirectedGraphMatrix = createUndirectedGraphMatrix();
    }

    private Integer[][] createDirectedGraphMatrix() {
        DirectedGraphMatrixCreator directedGraphMatrixCreator = new DirectedGraphMatrixCreator(matrix);
        return directedGraphMatrixCreator.getGraphMatrix();
    }

    private Integer[][] createUndirectedGraphMatrix() {
        UndirectedGraphMatrixCreator undirectedGraphMatrixCreator = new UndirectedGraphMatrixCreator(directedGraphMatrix);
        return undirectedGraphMatrixCreator.getGraphMatrix();
    }

    private int calculateVerticesNumber() {
        return 10 + Constants.n3;
    }

    public int getN() {
        return n;
    }

    public Integer[][] getDirectedGraphMatrix() {
        return directedGraphMatrix;
    }

    public Integer[][] getUndirectedGraphMatrix() {
        return undirectedGraphMatrix;
    }
}
