package app.model;

import app.model.graphAnalysis.VertexCalculator;
import app.model.matrix.BoolTransformer;
import app.model.matrix.MatrixCalculator;
import app.model.matrix.dataSupliers.IdentityMatrixSupplier;

import java.util.ArrayList;

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

    public ArrayList<ArrayList<Integer>> findNStepPaths(int ttl) {
        Integer[][] twoStepPathMatrix = getTwoStepPathMatrix();
        ArrayList<ArrayList<Integer>> twoStepPaths = new ArrayList<>();

        for (int i = 0; i < getAdjacencyMatrix().length; i++) {
            for (int j = 0; j < getAdjacencyMatrix().length; j++) {
                if (twoStepPathMatrix[i][j] > 0) {
                    System.out.println("Entrance");
                    ArrayList<Integer> foundPath = findPath(i, j, ttl, new ArrayList<>());
                    if (foundPath != null) {
                        twoStepPaths.add(foundPath);
                    }
                }
            }
        }

        return twoStepPaths;
    }

    private ArrayList<Integer> findPath(Integer firstVertex, Integer lastVertex, int TTL, ArrayList<Integer> path) { // refactor numerous returns
        path.add(firstVertex);
        System.out.println(path);
        System.out.printf("firstVertex = %d, lastVertex = %d, ttl = %d\n", firstVertex, lastVertex, TTL);
        if (TTL == 0) {
            if (firstVertex.equals(lastVertex)) {
                return path;
            } else {
                return null;
            }
        } else {
            for (int j = 0; j < getAdjacencyMatrix().length; j++) {
                if (getAdjacencyMatrix()[firstVertex][j] == 1) {
                    return findPath(getAdjacencyMatrix()[firstVertex][j], lastVertex, TTL-1, path);
                }
            }
        }
        return null;
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

    public Integer[][] getThreeStepPathMatrix() {
        return MatrixCalculator.exponentMatrix(getAdjacencyMatrix(), 3);
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
