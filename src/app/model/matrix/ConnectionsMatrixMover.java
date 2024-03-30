package app.model.matrix;

public final class ConnectionsMatrixMover {
    public static Integer[][] moveMatrixOfStrongConnections(Integer[][] matrixOfStrongConnections) {
        Integer[][] numeratedMatrixOfStrongConnections = numerateMatrix(matrixOfStrongConnections);

        for (int i = 1; i < numeratedMatrixOfStrongConnections.length; i++) {
            for (int j = 2; j < numeratedMatrixOfStrongConnections.length; j++) {
                if (calculateRowValue(numeratedMatrixOfStrongConnections[j-1])
                        < calculateRowValue(numeratedMatrixOfStrongConnections[j])) {
                    Integer[] tmp = numeratedMatrixOfStrongConnections[j-1];
                    numeratedMatrixOfStrongConnections[j-1] = numeratedMatrixOfStrongConnections[j];
                    numeratedMatrixOfStrongConnections[j] = tmp;
                }
            }
        }
        for (int i = 1; i < numeratedMatrixOfStrongConnections.length; i++) {
            for (int j = 2; j < numeratedMatrixOfStrongConnections.length; j++) {
                if (calculateColumnValue(numeratedMatrixOfStrongConnections, j-1)
                        < calculateColumnValue(numeratedMatrixOfStrongConnections, j)) {
                    for (int k = 0; k < numeratedMatrixOfStrongConnections.length; k++) {
                        Integer tmp = numeratedMatrixOfStrongConnections[k][j-1];
                        numeratedMatrixOfStrongConnections[k][j-1] = numeratedMatrixOfStrongConnections[k][j];
                        numeratedMatrixOfStrongConnections[k][j] = tmp;
                    }
                }
            }
        }


        return numeratedMatrixOfStrongConnections;
    }

    private static Integer[][] numerateMatrix(Integer[][] matrix) {
        Integer[][] numeratedMatrixOfStrongConnections = new Integer[matrix.length + 1][matrix[0].length + 1];
        for (int i = 0; i <= matrix.length; i++) {
            numeratedMatrixOfStrongConnections[0][i] = i;
            numeratedMatrixOfStrongConnections[i][0] = i;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                numeratedMatrixOfStrongConnections[i+1][j+1] = matrix[i][j];
            }
        }
        return numeratedMatrixOfStrongConnections;
    }

    private static int calculateRowValue(Integer[] row) {
        int sum = 0;
        for (int i = 1; i < row.length; i++) {
            sum += row[i];
        }
        return sum;
    }
    private static int calculateColumnValue(Integer[][] matrix, int columnIndex) {
        int sum = 0;
        for (int i = 1; i < matrix[0].length; i++) {
            sum += matrix[i][columnIndex];
        }
        return sum;
    }
}
