package app;

import app.matrix.DirectedGraphMatrixCreator;
import app.matrix.MatrixPrinter;
import app.matrix.RandomMatrixCreator;
import app.matrix.UndirectedGraphMatrixCreator;
import app.model.Node;
import app.painter.GraphFrame;

public class Main {

    public static void main(String[] args) {
        testMatrices();
//        testPainter();
//        System.out.println(11/2);
    }

    private static void testPainter(Integer[][] graphMatrix) {
        GraphFrame graphFrame = new GraphFrame("Graph", graphMatrix);
    }

    private static void testMatrices() {
        int n1 = 3;
        int n2 = 1;
        int n3 = 1;
        int n4 = 9;

        Double[][] matrix = new RandomMatrixCreator(n1, n2, n3, n4).getMatrix();

        MatrixPrinter<Double> matrixPrinter = new MatrixPrinter<>();
        matrixPrinter.printMatrix(matrix);
        System.out.println();

        Integer[][] intMatrix = new DirectedGraphMatrixCreator(matrix).getGraphMatrix();
        MatrixPrinter<Integer> integerMatrixPrinter = new MatrixPrinter<>();
        integerMatrixPrinter.printMatrix(intMatrix);



        System.out.println();

        Integer[][] undirectedMatrix = new UndirectedGraphMatrixCreator(intMatrix).getGraphMatrix();
        integerMatrixPrinter.printMatrix(undirectedMatrix);

        testPainter(undirectedMatrix);
    }
}
