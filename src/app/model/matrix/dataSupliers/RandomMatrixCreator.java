package app.model.matrix.dataSupliers;

import app.utils.Constants;

import java.util.Random;

public class RandomMatrixCreator {
    private final Double[][] matrix;
    public RandomMatrixCreator(int n, Double k) {
        matrix = generateRandomMatrix(n, calculateSeed(Constants.n1, Constants.n2, Constants.n3, Constants.n4));
        scalarMultiply(k);
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

        for (Double[] tmpArr : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                tmpArr[j] = generator.nextDouble(0, 2);
            }
        }

        return matrix;
    }

    private void scalarMultiply(Double k) {
        for (Double[] tmpArr : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                tmpArr[j] *= k;
            }
        }
    }

    public Double[][] getMatrix() {
        return matrix;
    }
}
