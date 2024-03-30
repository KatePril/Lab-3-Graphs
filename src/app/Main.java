package app;

import app.controller.Controller;

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
