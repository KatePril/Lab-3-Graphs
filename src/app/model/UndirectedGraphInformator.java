package app.model;

import app.model.graphAnalysis.VertexCalculator;

import java.util.ArrayList;

public class UndirectedGraphInformator {
    private final Integer[][] adjacencyMatrix;
    private final boolean ifDirected;
    private Integer[] verticesPower;
    private ArrayList<Integer> endVertices;

    public UndirectedGraphInformator(Integer[][] adjacencyMatrix, boolean ifDirected) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.ifDirected = ifDirected;

        this.verticesPower = VertexCalculator.calculateVerticesPower(adjacencyMatrix, ifDirected);
        this.endVertices = VertexCalculator.calculateEndVertices(this.verticesPower);
    }

    public Integer[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public boolean isIfDirected() {
        return ifDirected;
    }

    public Integer[] getVerticesPower() {
        return verticesPower;
    }

    public ArrayList<Integer> getEndVertices() {
        return endVertices;
    }
}
