package app.model;

import app.model.graphAnalysis.VertexCalculator;
import app.model.matrix.BoolTransformer;
import app.model.matrix.ConnectionsMatrixMover;
import app.model.matrix.MatrixCalculator;
import app.model.matrix.PathSearcher;
import app.model.matrix.dataSupliers.IdentityMatrixSupplier;

import java.util.ArrayList;
import java.util.LinkedList;

public class DirectedGraphInformator extends UndirectedGraphInformator {
    private Integer[] positivePowerVertex;
    private Integer[] negativePowerVertex;
    private Integer[][] matrixOfReachability;
    private Integer[][] matrixOfStrongConnections;
    public DirectedGraphInformator(Integer[][] adjacencyMatrix, boolean ifDirected) {
        super(adjacencyMatrix, ifDirected);
        positivePowerVertex = VertexCalculator.calculateVerticesPositivePower(getAdjacencyMatrix());
        negativePowerVertex = VertexCalculator.calculateVerticesNegativePower(getAdjacencyMatrix());
        matrixOfReachability = calculateMatrixOfReachability();
        matrixOfStrongConnections = calculateMatrixOfStrongConnections();
    }

    public Integer[][] getBoolTwoStepPathMatrix() {
        BoolTransformer<Integer> boolTransformer = new BoolTransformer<>();
        return boolTransformer.getBoolMatrix(MatrixCalculator.exponentMatrix(getAdjacencyMatrix(), 2));
    }

    public Integer[][] getBoolThreeStepPathMatrix() {
        BoolTransformer<Integer> boolTransformer = new BoolTransformer<>();
        return boolTransformer.getBoolMatrix(MatrixCalculator.exponentMatrix(getAdjacencyMatrix(), 3));
    }


    public LinkedList<LinkedList<Integer>> findNStepPaths(int ttl) {
        PathSearcher pathSearcher = new PathSearcher(MatrixCalculator.exponentMatrix(getAdjacencyMatrix(), ttl), getAdjacencyMatrix());
        return pathSearcher.findNStepPaths(ttl);
    }

    private Integer[][] calculateMatrixOfReachability() {
        ArrayList<Integer[][]> matrixExponents = new ArrayList<>();
        for (int i = 1; i < getAdjacencyMatrix().length; i++) {
            matrixExponents.add(MatrixCalculator.exponentMatrix(getAdjacencyMatrix(), i));
        }
        Integer[][] resultMatrix = IdentityMatrixSupplier.getIdentityMatrix(getAdjacencyMatrix().length);
        for (Integer[][] matrix : matrixExponents) {
            resultMatrix = MatrixCalculator.addMatrices(resultMatrix, matrix);
        }
        BoolTransformer<Integer> boolTransformer = new BoolTransformer<>();
        return boolTransformer.getBoolMatrix(resultMatrix);
    }

    private Integer[][] calculateMatrixOfStrongConnections() {
        Integer[][] transposedMatrixOfReachability = MatrixCalculator.transposeMatrix(matrixOfReachability);
        return MatrixCalculator.multiplyElementWise(matrixOfReachability, transposedMatrixOfReachability);
    }

    public Integer[][] getMovedMatrixOfStrongConnections() {
        return ConnectionsMatrixMover.moveMatrixOfStrongConnections(matrixOfStrongConnections);
    }

    public ArrayList<ArrayList<Integer>> getComponents() {
        Integer[][] matrix = getMovedMatrixOfStrongConnections();
        ArrayList<ArrayList<Integer>> components = new ArrayList<>();
        int k = 0;
        int j = 1;
        int i = 1;
        while (j < matrix.length) {
                while (matrix[i][j] == 1 && matrix[j][i] == 1) {
                    try {
                        components.get(k).add(matrix[0][j]);
                    } catch (IndexOutOfBoundsException exception) {
                        components.add(new ArrayList<>());
                        components.get(k).add(matrix[0][j]);
                    }

                    j++;
                    if (j == matrix.length)
                        break;
                }
            i = j;
            k++;
        }
        return components;
    }

    public Integer[] getPositivePowerVertex() {
        return positivePowerVertex;
    }

    public Integer[] getNegativePowerVertex() {
        return negativePowerVertex;
    }

    public Integer[][] getMatrixOfReachability() {
        return matrixOfReachability;
    }

    public Integer[][] getMatrixOfStrongConnections() {
        return matrixOfStrongConnections;
    }
}
