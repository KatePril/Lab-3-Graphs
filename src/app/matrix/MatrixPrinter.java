package app.matrix;

public class MatrixPrinter<T> {
    public void printMatrix(T[][] matrix) {
        for (T[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }
}
