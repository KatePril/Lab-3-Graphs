package app.model.graphVisitors;

import app.enums.VertexStatus;

import java.util.Arrays;
import java.util.HashMap;

public abstract class Visitor {
    protected final Integer[][] graphMatrix;
    protected final VertexStatus[] vertices;
    protected HashMap<Integer, Integer> newIndicesOfVertices;
    protected Integer vertexCounter;
    protected Integer[][] visitMatrix;
    protected Integer activeVertex;
    protected Integer index;

    public Visitor(Integer[][] graphMatrix) {
        activeVertex = null;
        this.graphMatrix = graphMatrix;
        this.vertices = new VertexStatus[graphMatrix.length];
        Arrays.fill(vertices, VertexStatus.NEW);
        this.newIndicesOfVertices = new HashMap<>(); // pair {vertex : new number}
        this.vertexCounter = 0;
        this.visitMatrix = new Integer[graphMatrix.length][graphMatrix[0].length];
        for (int i = 0; i < visitMatrix.length; i++) {
            for (int j = 0; j < visitMatrix[0].length; j++) {
                visitMatrix[i][j] = 0;
            }
        }
        index = 0;
    }

    public void visit() {
        if (isVisitedVerticesEmpty()) {
            Integer startVertex = getStartVertex();
            vertices[startVertex] = VertexStatus.VISITED;
            newIndicesOfVertices.put(startVertex, vertexCounter);
            makeStep(startVertex);
        } else {
            makeStep(activeVertex);
        }
    }

    public boolean isVisitComplete() {
        boolean output = true;
        for (VertexStatus vertex : vertices) {
            if (vertex == VertexStatus.NEW) {
                output = false;
                break;
            }
        }
        return output;
    }

    private Integer getStartVertex() {
        int startVertex = 0;
        boolean flag = false;
        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < graphMatrix[0].length; j++) {
                if (vertices[i] == VertexStatus.NEW && graphMatrix[i][j] == 1) {
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

    protected abstract void makeStep(Integer startVertex);

    public VertexStatus[] getVertices() {
        return vertices;
    }

    public Integer[][] getVisitMatrix() {
        return visitMatrix;
    }

    public HashMap<Integer, Integer> getNewIndicesOfVertices() {
        return newIndicesOfVertices;
    }

    protected abstract boolean isVisitedVerticesEmpty();
}
