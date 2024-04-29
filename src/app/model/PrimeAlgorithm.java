package app.model;

import app.view.PrimeStagePrinter;

import java.util.ArrayList;
import java.util.LinkedList;

public class PrimeAlgorithm {
    private final Integer[][] edgesMatrix;
    private final Integer[][] weightMatrix;
    private final LinkedList<Integer> includedVertices;
    private final ArrayList<ArrayList<Integer>> edgesList;
    private final ArrayList<ArrayList<Integer>> includedEdges;
    private int totalWeight;

    public PrimeAlgorithm(Integer[][] edgesMatrix, Integer[][] weightMatrix) {
        this.edgesMatrix = edgesMatrix;
        this.edgesList = MatrixToListConverter.convertUndirectedMatrix(edgesMatrix);
        this.weightMatrix = weightMatrix;
        this.includedVertices = new LinkedList<>();
        this.includedEdges = new ArrayList<>();
        totalWeight = 0;
    }

    public boolean buildTree() {
        boolean output = false;
        if (!isSpanningTreeCompleted()) {
            makeStep();
            output = true;
        }
        return  output;
    }

    private boolean isSpanningTreeCompleted() {
        return edgesMatrix.length == includedVertices.size();
    }

    private void makeStep() {
        if (includedVertices.isEmpty()) {
            includedVertices.add(edgesList.get(0).get(0));
            PrimeStagePrinter.printStage(0, includedVertices, includedEdges, totalWeight);
        } else {
            Integer minWeight = 0;
            ArrayList<Integer> minEdge = null;
            for (Integer vertex: includedVertices) {
                ArrayList<ArrayList<Integer>> edgesWithActiveVertex = getEdgesWithActiveVertex(vertex);


                for (ArrayList<Integer> edge : edgesWithActiveVertex) {
                    if (!isVertexIncluded(edge.get(1))) {
                        if (minWeight == 0) {
                            minWeight = weightMatrix[edge.get(0)][edge.get(1)];
                            minEdge = edge;
                            continue;
                        }
                        if (weightMatrix[edge.get(0)][edge.get(1)] < minWeight) {
                            minWeight = weightMatrix[edge.get(0)][edge.get(1)];
                            minEdge = edge;
                        }
                    }
                }
            }

            if (minEdge != null) {
                includedVertices.add(minEdge.get(1));
                includedEdges.add(minEdge);
            }

            totalWeight += minWeight;
            PrimeStagePrinter.printStage(minWeight, includedVertices, includedEdges, totalWeight);
        }
    }

    private ArrayList<ArrayList<Integer>> getEdgesWithActiveVertex(Integer activeVertex) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (ArrayList<Integer> edge : edgesList) {
            if (edge.get(0).equals(activeVertex))
                list.add(edge);
        }
        return list;
    }

    private boolean isVertexIncluded(Integer vertex) {
        boolean output = false;
        for (Integer v : includedVertices) {
            if (v.equals(vertex)) {
               output = true;
               break;
            }
        }
        return output;
    }

    public ArrayList<ArrayList<Integer>> getIncludedEdges() {
        return includedEdges;
    }

    public int getTotalWeight() {
        return totalWeight;
    }
}
