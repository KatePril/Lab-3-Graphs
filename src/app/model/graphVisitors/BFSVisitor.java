package app.model.graphVisitors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public final class BFSVisitor {
    private Integer[][] graphMatrix;
    private Integer[] vertices;
    private HashMap<Integer, Integer> newIndicesOfVertices;
    private Integer vertexCounter;

    public BFSVisitor(Integer[][] graphMatrix) {
        this.graphMatrix = graphMatrix;
        this.vertices = new Integer[graphMatrix.length];
        Arrays.fill(vertices, 0);
    }

    public void visitBFS(Integer startVertex) {
        vertices[startVertex] = 1;
//        System.out.println("Vertices: " + Arrays.toString(vertices));
        Queue<Integer> visitedVertices = new LinkedList<>();
        visitedVertices.add(startVertex);
//        System.out.println("VisitedVertices: " + visitedVertices);
        vertexCounter = 0;
        newIndicesOfVertices = new HashMap<>();// pair {vertex : new number}
        newIndicesOfVertices.put(startVertex, vertexCounter);
//        System.out.println("Hash map: " + newIndicesOfVertices);

        while (!visitedVertices.isEmpty()) {
            Integer activeVertex = visitedVertices.element();
            for (int i = 0; i < graphMatrix[0].length; i++) {
//                System.out.printf("active = %d, i = %d\n", activeVertex, i);
                if (graphMatrix[activeVertex][i] == 1) {
//                    System.out.println("Edge exists");
                    if (vertices[i] == 0) {
                        vertices[i] = 1;
                        ++vertexCounter;
                        newIndicesOfVertices.put(i, vertexCounter);
                        visitedVertices.add(i);
//                        System.out.println("Vertices: " + Arrays.toString(vertices));
//                        System.out.println("VisitedVertices: " + visitedVertices);
//                        System.out.println("Hash map: " + newIndicesOfVertices);
                    }
                }
                if (i == graphMatrix[0].length - 1) {
                    visitedVertices.poll();
//                    System.out.println("After element was polled");
//                    System.out.println("Vertices: " + Arrays.toString(vertices));
//                    System.out.println("VisitedVertices: " + visitedVertices);
//                    System.out.println("Hash map: " + newIndicesOfVertices);
                }

            }
        }
    }
}
