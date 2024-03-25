package app.model.graphAnalysis;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;

public final class VertexCalculator {
    public static Integer[] calculateVerticesPower(Integer[][] graphMatrix, boolean ifDirected) {
        Integer[] verticesPower = new Integer[graphMatrix.length];
        Arrays.fill(verticesPower, 0);

        for (int i = 0; i < verticesPower.length; i++) {
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

    //вихідні дуги, які починаються з заданої вершини
    public static Integer[] calculateVerticesPositivePower(Integer[][] graphMatrix) {
        Integer[] positivePower = new Integer[graphMatrix.length];
        Arrays.fill(positivePower, 0);
        // спитати як рахувати петлі
        for (int i = 0; i < positivePower.length; i++) {
            for (int j = 0; j < positivePower.length; j++) {
                if (graphMatrix[i][j] == 1)
                    ++positivePower[i];
            }
        }

        return positivePower;
    }

    // вхідні дуги, які закінчуються з заданої вершини
    public static Integer[] calculateVerticesNegativePower(Integer[][] graphMatrix) {
        Integer[] negativePower = new Integer[graphMatrix.length];
        Arrays.fill(negativePower, 0);

        for (int i = 0; i < negativePower.length; i++) {
            for (int j = 0; j < negativePower.length; j++) {
                if (graphMatrix[j][i] == 1)
                    ++negativePower[i];
            }
        }

        return negativePower;
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
