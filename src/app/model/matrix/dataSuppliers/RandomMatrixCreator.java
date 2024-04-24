package app.model.matrix.dataSuppliers;

import app.model.matrix.ScalarMultiplier;
import app.model.matrix.dataSuppliers.KGenerator;
import app.utils.Constants;

import java.util.Random;

public class RandomMatrixCreator {
    private Double[][] matrix;
    public RandomMatrixCreator(int n) {
        matrix = generateRandomMatrix(n, calculateSeed(Constants.n1, Constants.n2, Constants.n3, Constants.n4));
        scalarMultiply(KGenerator.getK());
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
        ScalarMultiplier<Double> scalarMultiplier = new ScalarMultiplier<>();
        matrix = scalarMultiplier.scalarMultiply(matrix, k);
    }

    public Double[][] getMatrix() {
        return matrix;
    }
}
