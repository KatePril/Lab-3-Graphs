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
        vertices[startVertex] = VertexStatus.VISITED;

        newIndicesOfVertices.put(startVertex, vertexCounter);

        Stack<Integer> visitedVertices = new Stack<>();
        visitedVertices.push(startVertex);

        while (!visitedVertices.empty()) {
            Integer activeVertex = visitedVertices.peek();
            vertices[activeVertex] = VertexStatus.ACTIVE;
            for (int i = 0; i < graphMatrix[0].length; i++) {
//                System.out.printf("activeVertex = %d, i = %d\n", activeVertex, i);
//                System.out.println("Vertices: " + Arrays.toString(vertices));
                if (graphMatrix[activeVertex][i] == 1 && vertices[i] == VertexStatus.NEW) {
                    vertices[i] = VertexStatus.ACTIVE;
                    vertices[activeVertex] = VertexStatus.VISITED;
                    ++vertexCounter;
                    newIndicesOfVertices.put(i, vertexCounter);
                    visitedVertices.push(i);
                    visitMatrix[activeVertex][i] = 1;
//                    System.out.println("Vertices: " + Arrays.toString(vertices));
//                    System.out.println("VisitedVertices: " + visitedVertices);
//                    System.out.println("HashMap: " + newIndicesOfVertices);
                    break;
                }
                if (i == graphMatrix[0].length - 1) {
                    vertices[visitedVertices.pop()] = VertexStatus.CLOSED;
//                    System.out.println("Vertices: " + Arrays.toString(vertices));
//                    System.out.println("VisitedVertices: " + visitedVertices);
//                    System.out.println("HashMap: " + newIndicesOfVertices);
                }
            }
        }
    }

}
