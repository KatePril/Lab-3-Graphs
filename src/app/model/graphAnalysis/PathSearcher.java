package app.model.graphAnalysis;

import java.util.Arrays;
import java.util.LinkedList;

public class PathSearcher {
    private final Integer[][] nStepPathMatrix;
    private final Integer[][] adjacencyMatrix;

    public PathSearcher(Integer[][] nStepPathMatrix, Integer[][] adjacencyMatrix) {
        this.nStepPathMatrix = nStepPathMatrix;
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public LinkedList<LinkedList<Integer>> findNStepPaths(int ttl) {
        LinkedList<LinkedList<Integer>> nStepPaths = new LinkedList<>();

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (nStepPathMatrix[i][j] > 0) {
                    LinkedList<LinkedList<Integer>> foundPaths = findPath(i, j, ttl);
                    nStepPaths.addAll(foundPaths);
                }
            }
        }

        return nStepPaths;
    }

    private LinkedList<LinkedList<Integer>> findPath(Integer firstVertex, Integer lastVertex, int ttl) {
        LinkedList<LinkedList<Integer>> paths = new LinkedList<>();
        boolean flag = true;
        int currentVertex = firstVertex;
        while (ttl > 0) {
            LinkedList<LinkedList<Integer>> pathsFromVertex = new LinkedList<>();
            if (flag) {
                for (int i = 0; i < adjacencyMatrix.length; i++) {
                    if (adjacencyMatrix[currentVertex][i] == 1) {
                        pathsFromVertex.add(new LinkedList<>(Arrays.asList(firstVertex, i)));
                    }
                }
                flag = false;
            } else {
                for (LinkedList<Integer> path : paths) {
                    currentVertex = path.getLast();
                    for (int j = 0; j < adjacencyMatrix.length; j++) {
                        if (currentVertex != j && adjacencyMatrix[currentVertex][j] == 1) {
                            if (!checkVertexPresence(path, j)) {
                                LinkedList<Integer> tmp = (LinkedList<Integer>) path.clone();
                                tmp.add(j);
                                pathsFromVertex.add(tmp);
                            }
                        }
                    }
                }
            }
            paths = pathsFromVertex;
            --ttl;
        }
        LinkedList<LinkedList<Integer>> output = new LinkedList<>();
        for (LinkedList<Integer> path : paths) {
            if (path.get(0).equals(firstVertex) && path.getLast().equals(lastVertex)) {
                output.add(path);
            }
        }
        return output;
    }

    private boolean checkVertexPresence(LinkedList<Integer> arr, Integer vertex) {
        Integer lastElement = arr.get(arr.size() - 1);
        boolean output = false;
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).equals(vertex) && arr.get(i-1).equals(lastElement)) {
                output = true;
                break;
            }
        }
        return output;
    }
}
