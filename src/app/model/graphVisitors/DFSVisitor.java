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
        table.addRow(new Row<>(vertices, vertexCounter, visitedVertices));
        this.visitedVertices = new Stack<>();
    }

    @Override
    public void makeStep(Integer startVertex) {
        if (isVisitedVerticesEmpty())
            visitedVertices.push(startVertex);
        boolean flag = true;

        while (flag) {
            activeVertex = visitedVertices.peek();
            vertices[activeVertex] = VertexStatus.ACTIVE;
            for (; index < graphMatrix[0].length; index++) {
                System.out.printf("activeVertex = %d, index = %d\n", activeVertex, index);
                System.out.println("Vertices: " + Arrays.toString(vertices));
                if (graphMatrix[activeVertex][index] == 1) {
                    if (vertices[index] == VertexStatus.NEW) {
                        ++vertexCounter;
                        table.addRow(new Row<>(vertices, visitedVertices, index + 1, vertexCounter));
                        vertices[index] = VertexStatus.ACTIVE;
                        vertices[activeVertex] = VertexStatus.VISITED;

                        newIndicesOfVertices.put(index, vertexCounter);
                        visitedVertices.push(index);
                        visitMatrix[activeVertex][index] = 1;
                    System.out.println("Vertices: " + Arrays.toString(vertices));
                    System.out.println("VisitedVertices: " + visitedVertices);
                    System.out.println("HashMap: " + newIndicesOfVertices);
                        break;
                    } else {
                        table.addRow(new Row<>(vertices, visitedVertices));
                    }
                }
                if (this.index == graphMatrix[0].length - 1) {
                    vertices[visitedVertices.pop()] = VertexStatus.CLOSED;
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
        return visitedVertices.empty();
    }
}
