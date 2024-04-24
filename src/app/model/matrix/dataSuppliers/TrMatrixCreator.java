package app.model.matrix.dataSuppliers;

public final class TrMatrixCreator {
    public static Integer[][] getTrMatrix(int n) {
        Integer[][] matrixTr = new Integer[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrixTr[i][j] = (i < j) ? 1 : 0;
            }
        }
        return matrixTr;
    }
}
