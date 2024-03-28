package app.model.matrix.dataSupliers;

public final class IdentityMatrixSupplier {
    public static Integer[][] getIdentityMatrix(int n) {
        Integer[][] matrix = new Integer[n][n];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = i == j ? 1 : 0;
            }
        }
        return matrix;
    }
}
