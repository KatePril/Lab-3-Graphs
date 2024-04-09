package app.model.graphVisitors;

import app.enums.VertexStatus;
import app.model.table.Row;
import app.model.table.Table;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public final class BFSVisitor extends Visitor {

    private Table<Queue<Integer>> table;
    private Queue<Integer> visitedVertices;


    public BFSVisitor(Integer[][] graphMatrix) {
        super(graphMatrix);
        table = new Table<>("Queue");
        this.visitedVertices = new LinkedList<>();
    }

    @Override
    public void makeStep(Integer startVertex) {
        table.addRow(new Row<>(vertices, vertexCounter, visitedVertices));
        vertices[startVertex] = VertexStatus.VISITED;
        visitedVertices.add(startVertex);

        newIndicesOfVertices.put(startVertex, vertexCounter);

        while (!visitedVertices.isEmpty()) {
            Integer activeVertex = visitedVertices.element();
            vertices[activeVertex] = VertexStatus.ACTIVE;

            for (int i = 0; i < graphMatrix[0].length; i++) {
//                System.out.printf("activeVertex = %d, i = %d\n", activeVertex, i);
//                System.out.println("Vertices: " + Arrays.toString(vertices));
                if (graphMatrix[activeVertex][i] == 1) {
                    if (vertices[i] == VertexStatus.NEW) {
                        vertices[i] = VertexStatus.VISITED;
                        ++vertexCounter;
                        newIndicesOfVertices.put(i, vertexCounter);
                        visitedVertices.add(i);
                        visitMatrix[activeVertex][i] = 1;
//                    System.out.println("Vertices: " + Arrays.toString(vertices));
//                    System.out.println("VisitedVertices: " + visitedVertices);
//                    System.out.println("HashMap: " + newIndicesOfVertices);
                        table.addRow(new Row<>(vertices, visitedVertices, i + 1, vertexCounter));
                    } else {
                        table.addRow(new Row<>(vertices, visitedVertices));
                    }
                }
                if (i == graphMatrix[0].length - 1) {
                    vertices[visitedVertices.remove()] = VertexStatus.CLOSED;
//                    System.out.println("Vertices: " + Arrays.toString(vertices));
//                    System.out.println("VisitedVertices: " + visitedVertices);
//                    System.out.println("HashMap: " + newIndicesOfVertices);
                }
            }
        }
    }

}
