package app.model.matrix;

public final class MatrixCalculator {

    public static Integer[][] exponentMatrix(Integer[][] graphMatrix, int power) {
        Integer[][] exponentMatrix = graphMatrix;
        while (power > 1) {
            --power;
            exponentMatrix = multiplyMatrices(graphMatrix, exponentMatrix);
        }
        return exponentMatrix;
    }

    public static Integer[][] multiplyMatrices(Integer[][] a, Integer[][] b) {
        Integer[][] newMatrix = new Integer[a.length][a[0].length];

        for (int i = 0; i < newMatrix.length; i++) {
            Integer[] rowA = a[i];
            Integer[] rowNewMatrix = newMatrix[i];

            for (int j = 0; j < newMatrix.length; j++) {
                int tmp = 0;

                for (int k = 0; k < newMatrix.length; k++) {
                    tmp += rowA[k] * b[k][j];
                }
                rowNewMatrix[j] = tmp;
            }
        }

        return newMatrix;
    }
}
