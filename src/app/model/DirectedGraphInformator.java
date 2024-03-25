package app.model;

import app.model.graphAnalysis.VertexCalculator;

import java.util.ArrayList;

public class DirectedGraphInformator extends UndirectedGraphInformator {
    private Integer[] positivePowerVertex;
    private Integer[] negativePowerVertex;
    public DirectedGraphInformator(Integer[][] adjacencyMatrix, boolean ifDirected) {
        super(adjacencyMatrix, ifDirected);
        positivePowerVertex = VertexCalculator.calculateVerticesPositivePower(getAdjacencyMatrix());
        negativePowerVertex = VertexCalculator.calculateVerticesNegativePower(getAdjacencyMatrix());
    }

    public Integer[] getPositivePowerVertex() {
        return positivePowerVertex;
    }

    public Integer[] getNegativePowerVertex() {
        return negativePowerVertex;
    }
}
