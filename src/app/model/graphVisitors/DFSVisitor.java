package app.model.graphVisitors;

import app.enums.VertexStatus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class DFSVisitor {
    private final Integer[][] graphMatrix;
    private final Integer[] vertices;
    private HashMap<Integer, Integer> newIndicesOfVertices;
    private Integer vertexCounter;

    public DFSVisitor(Integer[][] graphMatrix) {
        this.graphMatrix = graphMatrix;
        this.vertices = new Integer[graphMatrix.length];
        Arrays.fill(vertices, VertexStatus.NEW.val);
        this.vertexCounter = 0;
        this.newIndicesOfVertices = new HashMap<>();
    }

    public void visitDFS() {
        while (!isVisitComplete()) {
            visit(getStartVertex());
        }
    }

    private Integer getStartVertex() {
        int startVertex = 0;
        boolean flag = false;
        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < graphMatrix[0].length; j++) {
                if (vertices[i] == VertexStatus.NEW.val && graphMatrix[i][j] == 1) {
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
        vertices[startVertex] = VertexStatus.ACTIVE.val;

        newIndicesOfVertices.put(startVertex, vertexCounter);

        Stack<Integer> visitedVertices = new Stack<>();
        visitedVertices.push(startVertex);

        while (!visitedVertices.empty()) {
            Integer activeVertex = visitedVertices.peek();
            vertices[activeVertex] = VertexStatus.ACTIVE.val;
            for (int i = 0; i < graphMatrix[0].length; i++) {
                System.out.printf("activeVertex = %d, i = %d\n", activeVertex, i);
                System.out.println("Vertices: " + Arrays.toString(vertices));
                if (graphMatrix[activeVertex][i] == 1 && vertices[i] == VertexStatus.NEW.val) {
                    vertices[i] = VertexStatus.ACTIVE.val;
                    vertices[activeVertex] = VertexStatus.VISITED.val;
                    ++vertexCounter;
                    newIndicesOfVertices.put(i, vertexCounter);
                    visitedVertices.push(i);
                    System.out.println("Vertices: " + Arrays.toString(vertices));
                    System.out.println("VisitedVertices: " + visitedVertices);
                    System.out.println("HashMap: " + newIndicesOfVertices);
                    break;
                }
                if (i == graphMatrix[0].length - 1) {
                    vertices[visitedVertices.pop()] = VertexStatus.CLOSED.val;
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
            if (vertex == VertexStatus.NEW.val) {
                output = false;
                break;
            }
        }
        return output;
    }
}
