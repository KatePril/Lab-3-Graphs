package app;

import app.controller.Controller;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();

        controller.printUndirectedGraphMatrix();
        controller.calculateUndirectedGraphVerticesPower();
        controller.findEndVertexUndirectedGraph();
        controller.drawUndirectedGraphMatrix();


        System.out.println();


        controller.printDirectedGraphMatrix();
        controller.calculateDirectedGraphVerticesPower();
        controller.findEndVertexDirectedGraph();
        controller.drawDirectedGraphMatrix();

    }
}
