package app;

import app.controller.Controller;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();

        controller.printUndirectedGraphMatrix();
        controller.printDirectedGraphMatrix();

        controller.drawUndirectedGraphMatrix();
        controller.drawDirectedGraphMatrix();

    }
}
