package app.model.graphAnalysis;

import java.util.ArrayList;
import java.util.Arrays;

public final class VertexCalculator {
    public static Integer[] calculateVerticesPower(Integer[][] graphMatrix, boolean ifDirected) {
        Integer[] verticesPower = new Integer[graphMatrix.length];
        Arrays.fill(verticesPower, 0);

        for (int i = 0; i < verticesPower.length; i++) {
            if (verticesPower[i] == null) {
                verticesPower[i] = 0;
            }
            for (int j = 0; j < graphMatrix.length; j++) {
                if (graphMatrix[i][j] == 1) {
                    if (i == j) {
                        verticesPower[i] += 2;
                        continue;
                    }
                    ++verticesPower[i];
                }
                if (ifDirected && graphMatrix[j][i] == 1) {
                    ++verticesPower[i];
                }

            }
        }
        return verticesPower;
    }

    public static ArrayList<Integer> calculateEndVertices(Integer[] vertices) {
        ArrayList<Integer> endVertices = new ArrayList<>();
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == 1) {
                endVertices.add(i + 1);
            }
        }
        return endVertices;
    }
}
