package app.model;

import app.model.graphAnalysis.VertexCalculator;
import app.model.matrix.BoolTransformer;
import app.model.matrix.MatrixCalculator;
import app.model.matrix.dataSupliers.IdentityMatrixSupplier;

import java.awt.*;
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

    public ArrayList<ArrayList<Integer>> findNStepPaths(int ttl) {
        Integer[][] twoStepPathMatrix = getTwoStepPathMatrix();
        ArrayList<ArrayList<Integer>> twoStepPaths = new ArrayList<>();

        for (int i = 0; i < getAdjacencyMatrix().length; i++) {
            for (int j = 0; j < getAdjacencyMatrix().length; j++) {
                if (i != j) {
                    if (twoStepPathMatrix[i][j] > 0) {
//                        System.out.printf("\nEntrance: i = %d; j = %d; ttl = %d\n", i, j, ttl);
                        ArrayList<ArrayList<Integer>> foundPaths = findPath(i, j, ttl);
                        twoStepPaths.addAll(foundPaths);
                    }
                }
            }
        }

        return twoStepPaths;
    }

   private ArrayList<ArrayList<Integer>> findPath(Integer firstVertex, Integer lastVertex, int ttl) {
       ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
       boolean flag = true;
       int currentVertex = firstVertex;
       while (ttl > 0) {
           ArrayList<ArrayList<Integer>> pathsFromVertex = new ArrayList<>();
           if (flag) {
               for (int i = 0; i < getAdjacencyMatrix().length; i++) {
                   if (currentVertex != i && getAdjacencyMatrix()[currentVertex][i] == 1) {
                       pathsFromVertex.add(new ArrayList<>(Arrays.asList(firstVertex, i)));
                   }
               }
//               System.out.println(paths);
               flag = false;
           } else {
               for (ArrayList<Integer> path : paths) {
                   currentVertex = path.get(path.size() - 1);
                   for (int j = 0; j < getAdjacencyMatrix().length; j++) {
                       if (currentVertex != j && getAdjacencyMatrix()[currentVertex][j] == 1) {
                           if (!path.contains(j)) {
                               ArrayList<Integer> tmp = (ArrayList<Integer>) path.clone();
                               tmp.add(j);
//                           System.out.println("tmp: " + tmp);
                               pathsFromVertex.add(tmp);
                           }
                       }
                   }
               }
           }
           paths = pathsFromVertex;
//           System.out.println(pathsFromVertex);
           --ttl;
       }
       ArrayList<ArrayList<Integer>> output = new ArrayList<>();
       for (ArrayList<Integer> path : paths) {
           if (path.get(0).equals(firstVertex) && path.get(path.size()-1).equals(lastVertex)) {
               output.add(path);
           }
       }
//       System.out.println("output: " + output);
       return output;
   }

//    private ArrayList<Integer> findPath(Integer firstVertex, Integer lastVertex, int TTL, ArrayList<Integer> path) { // refactor numerous returns
//
//
//        System.out.println(path);
//        System.out.printf("firstVertex = %d, lastVertex = %d, ttl = %d\n", firstVertex, lastVertex, TTL);
//        if (TTL == 0) {
//            path.add(firstVertex);
//            if (firstVertex.equals(lastVertex)) {
//                return path;
//            } else {
//                return path;
//            }
//        } else {
//            if (!path.isEmpty()) {
//                if (path.contains(firstVertex)) {
//                    return findPath(path.get(path.size() - 1), lastVertex, TTL, path);
//                }
//            }
//            path.add(firstVertex);
//            for (int j = 0; j < getAdjacencyMatrix().length; j++) {
//                if (getAdjacencyMatrix()[lastVertex][j] == 1) {
//                    return findPath(j, lastVertex, TTL-1, path);
//                }
//            }
//        }
//        return null;
//    }

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
