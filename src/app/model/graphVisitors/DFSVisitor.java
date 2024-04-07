package app.model.graphVisitors;

import app.enums.VertexStatus;

import java.util.Arrays;
import java.util.Stack;

public class DFSVisitor extends Visitor{

    public DFSVisitor(Integer[][] graphMatrix) {
        super(graphMatrix);
    }

    @Override
    public void makeStep(Integer startVertex) {
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

}
