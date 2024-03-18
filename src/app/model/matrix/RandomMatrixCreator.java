package app.model.matrix;

import java.util.Random;

public class RandomMatrixCreator {
    private final int n;
    private Double[][] matrix;
    public RandomMatrixCreator(int n1, int n2, int n3, int n4) {
        n = calculateVerticesNumber(n3);
        matrix = generateRandomMatrix(n, calculateSeed(n1, n2, n3, n4));
        double k = calculateK(n3, n4);
        System.out.println(k);
        scalarMultiply(k);
    }

    private int calculateVerticesNumber(int n) {
        return 10 + n;
    }

    private Double calculateK(int n3, int n4) {
        return 1.0 - n3 * 0.02 - n4 * 0.005 - 0.25;
    }
    
    private int calculateSeed(int ...ns) {
        StringBuilder output = new StringBuilder();
        for (int n: ns) {
            output.append(n);
        }
        return Integer.parseInt(output.toString());
    }

    private Double[][] generateRandomMatrix(int n, int seed) {
        Random generator = new Random(seed);
        Double[][] matrix = new Double[n][n];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = generator.nextDouble(0, 2);
            }
        }

        return matrix;
    }

    private void scalarMultiply(Double k) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] *= k;
            }
        }
    }

    public int getN() {
        return n;
    }

    public Double[][] getMatrix() {
        return matrix;
    }
}
