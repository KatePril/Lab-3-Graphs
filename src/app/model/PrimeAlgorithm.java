package app.model;

import app.view.PrimeStagePrinter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PrimeAlgorithm {
    private final Integer[][] edgesMatrix;
    private final Integer[][] weightMatrix;
    private final LinkedList<Integer> includedVertices;
    private final ArrayList<ArrayList<Integer>> includedEdges;
    private int totalWeight;

    public PrimeAlgorithm(Integer[][] edgesMatrix, Integer[][] weightMatrix) {
        this.edgesMatrix = edgesMatrix;
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

    private void makeStep() {
        if (includedVertices.isEmpty()) {
            includedVertices.add(0);
            PrimeStagePrinter.printStage(0, includedVertices, includedEdges, totalWeight);
        } else {
            Integer activeVertex = includedVertices.getLast();
            Integer minWeight = 0;
            Integer vertexTwo = null;
            for (int i = 0; i < edgesMatrix[activeVertex].length; i++) {
                if (activeVertex != i && edgesMatrix[activeVertex][i] == 1 && !isVertexIncluded(i)) {
                    if (minWeight == 0) {
                        minWeight = weightMatrix[activeVertex][i];
                        vertexTwo = i;
                        continue;
                    }
                    if (weightMatrix[activeVertex][i] < minWeight) {
                        minWeight = weightMatrix[activeVertex][i];
                        vertexTwo = i;
                    }
                }
            }
            if (vertexTwo != null) {
                addEdge(activeVertex, vertexTwo);
            }
            totalWeight += minWeight;
            PrimeStagePrinter.printStage(minWeight, includedVertices, includedEdges, totalWeight);
        }
    }

    private boolean isSpanningTreeCompleted() {
        return edgesMatrix.length == includedVertices.size();
    }

    private void addEdge(Integer vertexOne, Integer vertexTwo) {
        includedVertices.add(vertexTwo);
        includedEdges.add(new ArrayList<>(List.of(vertexOne, vertexTwo)));
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
