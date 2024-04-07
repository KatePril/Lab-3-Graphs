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
        this.newIndicesOfVertices = new HashMap<>(); // pair {vertex : new number}
        Arrays.fill(vertices, 0);
        this.vertexCounter = 0;
    }

    public void visitBFS() {
        while (!isVisitComplete()) {
            visit(getStartVertex());
        }
    }

    private Integer getStartVertex() {
        int startVertex = 0;
        boolean flag = false;
        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < graphMatrix[0].length; j++) {
                if (vertices[i] == 0 && graphMatrix[i][j] == 1) {
                    startVertex = i;
                    flag = true;
                    break;
                }
            }
            if (flag)
                break;
        }

        return startVertex;
    }

    public void visit(Integer startVertex) {
        vertices[startVertex] = 1;
        Queue<Integer> visitedVertices = new LinkedList<>();
        visitedVertices.add(startVertex);

        newIndicesOfVertices.put(startVertex, vertexCounter);

        while (!visitedVertices.isEmpty()) {
            Integer activeVertex = visitedVertices.element();
            vertices[activeVertex] = 2;
            for (int i = 0; i < graphMatrix[0].length; i++) {
                System.out.printf("activeVertex = %d, i = %d\n", activeVertex, i);
                System.out.println("Vertices: " + Arrays.toString(vertices));
                if (graphMatrix[activeVertex][i] == 1 && vertices[i] == 0) {
                    vertices[i] = 1;
                    ++vertexCounter;
                    newIndicesOfVertices.put(i, vertexCounter);
                    visitedVertices.add(i);
                    System.out.println("Vertices: " + Arrays.toString(vertices));
                    System.out.println("VisitedVertices: " + visitedVertices);
                    System.out.println("HashMap: " + newIndicesOfVertices);
                }
                if (i == graphMatrix[0].length - 1) {
                    vertices[visitedVertices.remove()] = 3;
                    System.out.println("Vertices: " + Arrays.toString(vertices));
                    System.out.println("VisitedVertices: " + visitedVertices);
                    System.out.println("HashMap: " + newIndicesOfVertices);
                }
            }
        }
    }

    private boolean isVisitComplete() {
        boolean output = true;
        for (Integer vertex : vertices) {
            if (vertex == 0) {
                output = false;
                break;
            }
        }
        return output;
    }

    public Integer[] getVertices() {
        return vertices;
    }

    public HashMap<Integer, Integer> getNewIndicesOfVertices() {
        return newIndicesOfVertices;
    }
}
