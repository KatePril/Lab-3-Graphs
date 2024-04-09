package app.model.graphVisitors;

import app.enums.VertexStatus;
import app.model.table.Row;
import app.model.table.Table;

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
        if (isVisitedVerticesEmpty())
            visitedVertices.add(startVertex);
        boolean flag = true;

        while (flag) {
            Integer activeVertex = visitedVertices.element();
            vertices[activeVertex] = VertexStatus.ACTIVE;

            for (; index < graphMatrix[0].length; index++) {
                if (graphMatrix[activeVertex][index] == 1) {
                    if (vertices[index] == VertexStatus.NEW) {
                        vertices[index] = VertexStatus.VISITED;
                        ++vertexCounter;
                        newIndicesOfVertices.put(index, vertexCounter);
                        visitedVertices.add(index);
                        visitMatrix[activeVertex][index] = 1;
                        table.addRow(new Row<>(vertices, visitedVertices, index + 1, vertexCounter));
                        break;
                    } else {
                        table.addRow(new Row<>(vertices, visitedVertices));
                    }
                }
                if (index == graphMatrix[0].length - 1) {
                    vertices[visitedVertices.remove()] = VertexStatus.CLOSED;
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
