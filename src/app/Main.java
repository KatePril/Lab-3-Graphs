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

}
