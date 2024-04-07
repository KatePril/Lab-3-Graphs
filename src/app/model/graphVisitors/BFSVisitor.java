package app.model.graphVisitors;

import app.enums.VertexStatus;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public final class BFSVisitor extends Visitor {

    public BFSVisitor(Integer[][] graphMatrix) {
        super(graphMatrix);
    }

    @Override
    public void makeStep(Integer startVertex) {
        vertices[startVertex] = 1;
        Queue<Integer> visitedVertices = new LinkedList<>();
        visitedVertices.add(startVertex);

        newIndicesOfVertices.put(startVertex, vertexCounter);

        while (!visitedVertices.isEmpty()) {
            Integer activeVertex = visitedVertices.element();
            vertices[activeVertex] = VertexStatus.ACTIVE.val;

            for (int i = 0; i < graphMatrix[0].length; i++) {
                System.out.printf("activeVertex = %d, i = %d\n", activeVertex, i);
                System.out.println("Vertices: " + Arrays.toString(vertices));
                if (graphMatrix[activeVertex][i] == 1 && vertices[i] == VertexStatus.NEW.val) {
                    vertices[i] = VertexStatus.VISITED.val;
                    ++vertexCounter;
                    newIndicesOfVertices.put(i, vertexCounter);
                    visitedVertices.add(i);
                    System.out.println("Vertices: " + Arrays.toString(vertices));
                    System.out.println("VisitedVertices: " + visitedVertices);
                    System.out.println("HashMap: " + newIndicesOfVertices);
                }
                if (i == graphMatrix[0].length - 1) {
                    vertices[visitedVertices.remove()] = VertexStatus.CLOSED.val;
                    System.out.println("Vertices: " + Arrays.toString(vertices));
                    System.out.println("VisitedVertices: " + visitedVertices);
                    System.out.println("HashMap: " + newIndicesOfVertices);
                }
            }
        }
    }

}
