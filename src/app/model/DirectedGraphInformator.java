package app.model;

import app.model.graphAnalysis.VertexCalculator;
import app.model.matrix.MatrixCalculator;

import java.util.ArrayList;

public class DirectedGraphInformator extends UndirectedGraphInformator {
    private Integer[] positivePowerVertex;
    private Integer[] negativePowerVertex;
    public DirectedGraphInformator(Integer[][] adjacencyMatrix, boolean ifDirected) {
        super(adjacencyMatrix, ifDirected);
        positivePowerVertex = VertexCalculator.calculateVerticesPositivePower(getAdjacencyMatrix());
        negativePowerVertex = VertexCalculator.calculateVerticesNegativePower(getAdjacencyMatrix());
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


    public Integer[][] getThreeStepPathMatrix() {
        return MatrixCalculator.exponentMatrix(getAdjacencyMatrix(), 3);
    }

    public Integer[] getPositivePowerVertex() {
        return positivePowerVertex;
    }

    public Integer[] getNegativePowerVertex() {
        return negativePowerVertex;
    }
}
