package app.model.graphVisitors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public final class BFSVisitor {
    private final Integer[][] graphMatrix;
    private final Integer[] vertices;
    private HashMap<Integer, Integer> newIndicesOfVertices;
    private Integer vertexCounter;

    public BFSVisitor(Integer[][] graphMatrix) {
        this.graphMatrix = graphMatrix;
        this.vertices = new Integer[graphMatrix.length];
        Arrays.fill(vertices, 0);
        this.vertexCounter = 0;
    }

    public void visitBFS(Integer startVertex) {
        vertices[startVertex] = 1;
        Queue<Integer> visitedVertices = new LinkedList<>();
        visitedVertices.add(startVertex);

        newIndicesOfVertices = new HashMap<>();// pair {vertex : new number}
        newIndicesOfVertices.put(startVertex, vertexCounter);

        while (!visitedVertices.isEmpty()) {
            Integer activeVertex = visitedVertices.element();
            for (int i = 0; i < graphMatrix[0].length; i++) {
                if (graphMatrix[activeVertex][i] == 1 && vertices[i] == 0) {
                    vertices[i] = 1;
                    ++vertexCounter;
                    newIndicesOfVertices.put(i, vertexCounter);
                    visitedVertices.add(i);
                }
                if (i == graphMatrix[0].length - 1)
                    visitedVertices.poll();
            }
        }
    }

    public Integer[] getVertices() {
        return vertices;
    }

    public HashMap<Integer, Integer> getNewIndicesOfVertices() {
        return newIndicesOfVertices;
    }
}
