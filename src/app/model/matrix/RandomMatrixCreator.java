package app.model.matrix;

import app.utils.Constants;

import java.util.Random;

public class RandomMatrixCreator {
    private Double[][] matrix;
    public RandomMatrixCreator(int n) {
        matrix = generateRandomMatrix(n, calculateSeed(Constants.n1, Constants.n2, Constants.n3, Constants.n4));
        scalarMultiply(calculateK());
    }

    private int calculateVerticesNumber(int n) {
        return 10 + n;
    }

    private Double calculateK() {
        return 1.0 - Constants.n3 * 0.02 - Constants.n4 * 0.005 - 0.25;
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

    public Double[][] getMatrix() {
        return matrix;
    }
}
