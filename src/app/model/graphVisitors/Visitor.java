package app.model.graphVisitors;

import app.enums.VertexStatus;

import java.util.Arrays;
import java.util.HashMap;

public abstract class Visitor {
    protected final Integer[][] graphMatrix;
    protected final Integer[] vertices;
    protected HashMap<Integer, Integer> newIndicesOfVertices;
    protected Integer vertexCounter;

    public Visitor(Integer[][] graphMatrix) {
        this.graphMatrix = graphMatrix;
        this.vertices = new Integer[graphMatrix.length];
        Arrays.fill(vertices, VertexStatus.NEW.val);
        this.newIndicesOfVertices = new HashMap<>(); // pair {vertex : new number}
        this.vertexCounter = 0;
    }

    public void visit() {
        while (!isVisitComplete()) {
            makeStep(getStartVertex());
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

    protected abstract void makeStep(Integer startVertex);


}
