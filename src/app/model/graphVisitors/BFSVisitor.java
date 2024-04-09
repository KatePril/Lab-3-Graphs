package app.model.graphVisitors;

import app.enums.VertexStatus;
import app.model.table.Row;
import app.model.table.Table;
import app.view.matrix.MatrixPrinter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public final class BFSVisitor extends Visitor {

    private Table<Queue<Integer>> table;
    private Queue<Integer> visitedVertices;


    public BFSVisitor(Integer[][] graphMatrix) {
        super(graphMatrix);
        table = new Table<>("Queue");
        table.addRow(new Row<>(vertices, vertexCounter, visitedVertices));
        this.visitedVertices = new LinkedList<>();
    }

    @Override
    public void makeStep(Integer startVertex) {
        if (isVisitedVerticesEmpty())
            visitedVertices.add(startVertex);
        boolean flag = true;

        while (flag) {
            Integer activeVertex = visitedVertices.element();
            vertices[activeVertex] = VertexStatus.ACTIVE;

            for (; index < graphMatrix[0].length; index++) {
                System.out.printf("activeVertex = %d, index = %d\n", activeVertex, index);
                System.out.println("Vertices: " + Arrays.toString(vertices));
                if (graphMatrix[activeVertex][index] == 1) {
                    if (vertices[index] == VertexStatus.NEW) {
                        System.out.println("---------------------New vertex was visited");
                        vertices[index] = VertexStatus.VISITED;
                        ++vertexCounter;
                        newIndicesOfVertices.put(index, vertexCounter);
                        visitedVertices.add(index);
                        visitMatrix[activeVertex][index] = 1;
                        MatrixPrinter<Integer> matrixPrinter = new MatrixPrinter<>();
                        matrixPrinter.printMatrix(visitMatrix);

                        System.out.println("Vertices: " + Arrays.toString(vertices));
                        System.out.println("VisitedVertices: " + visitedVertices);
                        System.out.println("HashMap: " + newIndicesOfVertices);
                        table.addRow(new Row<>(vertices, visitedVertices, index + 1, vertexCounter));
                        flag = false;
                        break;
                    } else {
                        table.addRow(new Row<>(vertices, visitedVertices));
                    }
                }
                if (index == graphMatrix[0].length - 1) {
                    if (!isVisitedVerticesEmpty()) {
                        vertices[visitedVertices.remove()] = VertexStatus.CLOSED;
                    }
                    System.out.println("Vertices: " + Arrays.toString(vertices));
                    System.out.println("VisitedVertices: " + visitedVertices);
                    System.out.println("HashMap: " + newIndicesOfVertices);
                }
            }
            flag = false;
            index = 0;
        }
    }

    @Override
    protected boolean isVisitedVerticesEmpty() {
        return visitedVertices.isEmpty();
    }
}
