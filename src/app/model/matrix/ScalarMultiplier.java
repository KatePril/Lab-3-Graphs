package app.model.matrix;

public class ScalarMultiplier<E extends Number>  {
    public Double[][] scalarMultiply(Double[][] matrix, E k) {
        Double[][] resultMatrix = new Double[matrix.length][matrix[0].length];
        double scalar = k.doubleValue();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                resultMatrix[i][j] = matrix[i][j] * scalar;
            }
        }
        return resultMatrix;
    }
}
