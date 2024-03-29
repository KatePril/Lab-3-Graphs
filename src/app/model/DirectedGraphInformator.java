package app.model;

import app.model.graphAnalysis.VertexCalculator;
import app.model.matrix.BoolTransformer;
import app.model.matrix.MatrixCalculator;
import app.model.matrix.PathSearcher;
import app.model.matrix.dataSupliers.IdentityMatrixSupplier;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Integer[][] getTwoStepPathMatrix() {
        return MatrixCalculator.exponentMatrix(getAdjacencyMatrix(), 2);
    }

    public Integer[][] getThreeStepPathMatrix() {
        return MatrixCalculator.exponentMatrix(getAdjacencyMatrix(), 3);
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

    public Integer[][] moveMatrixOfStrongConnections() {
        Integer[][] numeratedMatrixOfStrongConnections = new Integer[matrixOfStrongConnections.length + 1][matrixOfStrongConnections[0].length + 1];
        for (int i = 0; i <= matrixOfStrongConnections.length; i++) {
            numeratedMatrixOfStrongConnections[0][i] = i;
            numeratedMatrixOfStrongConnections[i][0] = i;
        }
        for (int i = 0; i < matrixOfStrongConnections.length; i++) {
            for (int j = 0; j < matrixOfStrongConnections[0].length; j++) {
                numeratedMatrixOfStrongConnections[i+1][j+1] = matrixOfStrongConnections[i][j];
            }
        }

        for (int i = 1; i < numeratedMatrixOfStrongConnections.length; i++) {
            for (int j = 2; j < numeratedMatrixOfStrongConnections.length; j++) {
                if (calculateRowValue(numeratedMatrixOfStrongConnections[j-1]) < calculateRowValue(numeratedMatrixOfStrongConnections[j])) {
                    Integer[] tmp = numeratedMatrixOfStrongConnections[j-1];
                    numeratedMatrixOfStrongConnections[j-1] = numeratedMatrixOfStrongConnections[j];
                    numeratedMatrixOfStrongConnections[j] = tmp;
                }
            }
        }
        for (int i = 1; i < numeratedMatrixOfStrongConnections.length; i++) {
            for (int j = 2; j < numeratedMatrixOfStrongConnections.length; j++) {
                if (calculateColumnValue(numeratedMatrixOfStrongConnections, j-1) < calculateColumnValue(numeratedMatrixOfStrongConnections, j)) {
                    for (int k = 0; k < numeratedMatrixOfStrongConnections.length; k++) {
                        Integer tmp = numeratedMatrixOfStrongConnections[k][j-1];
                        numeratedMatrixOfStrongConnections[k][j-1] = numeratedMatrixOfStrongConnections[k][j];
                        numeratedMatrixOfStrongConnections[k][j] = tmp;
                    }
                }
            }
        }


        return numeratedMatrixOfStrongConnections;
    }

    public ArrayList<ArrayList<Integer>> getComponents() {
        Integer[][] matrix = moveMatrixOfStrongConnections();
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

    private int calculateRowValue(Integer[] row) {
        int sum = 0;
        for (int i = 1; i < row.length; i++) {
            sum += row[i];
        }
        return sum;
    }
    private int calculateColumnValue(Integer[][] matrix, int columnIndex) {
        int sum = 0;
        for (int i = 1; i < matrix[0].length; i++) {
            sum += matrix[i][columnIndex];
        }
        return sum;
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
