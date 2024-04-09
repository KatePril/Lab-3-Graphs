package app.model.graphVisitors;

import app.enums.VertexStatus;
import app.model.table.Row;
import app.model.table.Table;

import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

public class DFSVisitor extends Visitor{

    private Table<Stack<Integer>> table;
    private Stack<Integer> visitedVertices;

    public DFSVisitor(Integer[][] graphMatrix) {
        super(graphMatrix);
        this.table = new Table<>("Stack");
        this.visitedVertices = new Stack<>();
    }

    @Override
    public void makeStep(Integer startVertex) {
        table.addRow(new Row<>(vertices, vertexCounter, visitedVertices));

        vertices[startVertex] = VertexStatus.VISITED;
        newIndicesOfVertices.put(startVertex, vertexCounter);
        visitedVertices.push(startVertex);

        while (!visitedVertices.empty()) {
            Integer activeVertex = visitedVertices.peek();
            vertices[activeVertex] = VertexStatus.ACTIVE;
            for (int i = 0; i < graphMatrix[0].length; i++) {
//                System.out.printf("activeVertex = %d, i = %d\n", activeVertex, i);
//                System.out.println("Vertices: " + Arrays.toString(vertices));
                if (graphMatrix[activeVertex][i] == 1) {
                    if (vertices[i] == VertexStatus.NEW) {
                        ++vertexCounter;
                        table.addRow(new Row<>(vertices, visitedVertices, i + 1, vertexCounter));
                        vertices[i] = VertexStatus.ACTIVE;
                        vertices[activeVertex] = VertexStatus.VISITED;

                        newIndicesOfVertices.put(i, vertexCounter);
                        visitedVertices.push(i);
                        visitMatrix[activeVertex][i] = 1;
//                    System.out.println("Vertices: " + Arrays.toString(vertices));
//                    System.out.println("VisitedVertices: " + visitedVertices);
//                    System.out.println("HashMap: " + newIndicesOfVertices);
                        break;
                    } else {
                        table.addRow(new Row<>(vertices, visitedVertices));
                    }
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
