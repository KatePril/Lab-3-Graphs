package app;

import app.controller.Controller;
import app.model.matrix.MatrixCalculator;
import app.view.View;
import app.view.matrix.MatrixPrinter;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.displayUndirectedGraphInfo();
        System.out.println();
        controller.displayDirectedGraphInfo();
        System.out.println();
        controller.displayDirectedGraphTwoInfo();
    }

//    public static void main(String[] args) {
//        Integer[][] a = {{3, 7},
//                         {2, 0}};
//        Integer[][] b = {{6, -5},
//                         {2, 8}};
//        Integer[][] matrix = MatrixCalculator.addMatrices(a, b);
//        MatrixPrinter<Integer> matrixPrinter = new MatrixPrinter<>();
//        matrixPrinter.printMatrix(matrix);
//    }
}
