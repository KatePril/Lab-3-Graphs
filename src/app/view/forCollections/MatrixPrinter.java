package app.view.forCollections;

public class MatrixPrinter<T> {
    public void printMatrix(T[][] matrix) {
        System.out.print(" \t\t");
        for (int i = 1; i <= matrix.length; i++) {
//        for (int i = 0; i < matrix.length; i++) {
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

    public void printMatrixTitle(String title) {
        System.out.println(title);
    }
}
