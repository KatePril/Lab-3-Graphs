package app;

import app.controller.Controller;

public class Main {
    public static double angleWithXAxis(double slope) {
        // Use the arctan function to calculate the angle
        return Math.toDegrees(Math.atan(slope));
    }

//    public static void main(String[] args) {
//        // Example usage
//        double slope =0.5; // Change this to the slope of your line
//        double angle = angleWithXAxis(slope);
//        System.out.println("Angle with x-axis: " + angle + " degrees");
//    }

    public static void main(String[] args) {
        Controller controller = new Controller();

        controller.printUndirectedGraphMatrix();
        controller.printDirectedGraphMatrix();

        controller.drawUndirectedGraphMatrix();
        controller.drawDirectedGraphMatrix();

    }
}
