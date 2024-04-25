package app.view;

import java.util.ArrayList;
import java.util.LinkedList;

public final class PrimeStagePrinter {
    public static void printStage(Integer minWeight, LinkedList<Integer> vertices,
                                  ArrayList<ArrayList<Integer>> edges, int totalWeight) {
        System.out.println("Weight: " + minWeight);
        System.out.println("Vertices: " + getDisplayVertices(vertices));
        System.out.println("Edges: " + getDisplayEdges(edges));
        System.out.println("Total weight: " + totalWeight);
        System.out.println();
    }

    private static LinkedList<Integer> getDisplayVertices(LinkedList<Integer> vertices) {
        LinkedList<Integer> displayVertices = (LinkedList<Integer>) vertices.clone();
        displayVertices.replaceAll(el -> el + 1);
        return displayVertices;
    }

    private static ArrayList<ArrayList<Integer>> getDisplayEdges(ArrayList<ArrayList<Integer>> edges) {
        ArrayList<ArrayList<Integer>> displayEdges = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            displayEdges.add(new ArrayList<>());
            for (int j = 0; j < edges.get(i).size(); j++) {
                displayEdges.get(i).add(edges.get(i).get(j) + 1);
            }
        }
        return displayEdges;
    }
}
