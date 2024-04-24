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
        Integer[][] resultMatrix = new Integer[a.length][a[0].length];

        for (int i = 0; i < resultMatrix.length; i++) {
            Integer[] rowA = a[i];
            Integer[] rowNewMatrix = resultMatrix[i];

            for (int j = 0; j < resultMatrix.length; j++) {
                int tmp = 0;

                for (int k = 0; k < resultMatrix.length; k++) {
                    tmp += rowA[k] * b[k][j];
                }
                rowNewMatrix[j] = tmp;
            }
        }

        return resultMatrix;
    }

    public static Integer[][] addMatrices(Integer[][] a, Integer[][] b) {
        Integer[][] resultMatrix = new Integer[a.length][a[0].length];

        for (int i = 0; i < resultMatrix.length; i++) {
            Integer[] aRow = a[i];
            Integer[] bRow = b[i];
            Integer[] rRow = resultMatrix[i];
            for (int j = 0; j < resultMatrix[0].length; j++) {
                rRow[j] = aRow[j] + bRow[j];
            }
        }

        return resultMatrix;
    }

    public static Integer[][] transposeMatrix(Integer[][] a) {
        Integer[][] resultMatrix = new Integer[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            Integer[] aRow = a[i];
            for (int j = 0; j < a[0].length; j++) {
                resultMatrix[j][i] = aRow[j];
            }
        }
        return resultMatrix;
    }

    public static Integer[][] multiplyElementWise (Integer[][] a, Integer[][] b) {
        Integer[][] resultMatrix = new Integer[a.length][a[0].length];
        for (int i = 0; i < resultMatrix.length; i++) {
            Integer[] aRow = a[i];
            Integer[] bRow = b[i];
            Integer[] rRow = resultMatrix[i];
            for (int j = 0; j < resultMatrix[0].length; j++) {
                rRow[j] = aRow[j] * bRow[j];
            }
        }
        return resultMatrix;
    }
}