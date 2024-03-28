package app.view.matrix;

public class MatrixPrinter<T> {
    public void printMatrix(T[][] matrix) {
        System.out.print(" \t\t");
        for (int i = 1; i <= matrix.length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        System.out.println();

        int k = 1;
        for (T[] ints : matrix) {
            System.out.print(k + "\t\t");
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(ints[j] + "\t");
            }
            System.out.println();
            ++k;
        }
    }
    public void printMovedMatrix(T[][] matrix) {
        System.out.print(" \t\t");
        for (int i = 1; i < matrix.length; i++) {
            System.out.print(matrix[0][i] + "\t");
        }
        System.out.println();
        System.out.println();
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == 0) {
                    System.out.print(matrix[i][j] + "\t\t");
                } else {
                    System.out.print(matrix[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }
}
