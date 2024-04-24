package app.model.matrix.weightMatrix;

import app.model.matrix.BoolTransformer;
import app.model.matrix.MatrixCalculator;
import app.model.matrix.ScalarMultiplier;
import app.model.matrix.dataSuppliers.RandomMatrixCreator;
import app.view.matrix.MatrixPrinter;

import java.util.function.Predicate;

public class WeightMatrixCreator {
    private final Integer[][] matrixA;

    public WeightMatrixCreator(Integer[][] matrixA) {
        this.matrixA = matrixA;
    }

    public Double[][] getWeightMatrix() {
        MatrixPrinter<Double> matrixPrinter = new MatrixPrinter<>();
        RandomMatrixCreator randomMatrixCreator = new RandomMatrixCreator(matrixA.length);
        Double[][] matrixB = randomMatrixCreator.getMatrix();
        System.out.println("B: ");
        matrixPrinter.printMatrix(matrixB);

        ScalarMultiplier<Integer> scalarMultiplier = new ScalarMultiplier<>();
        Double[][] matrixC = scalarMultiplier.scalarMultiply(MatrixCalculator.multiplyElementWise(matrixA, matrixB), 100);
        System.out.println("C: ");
        matrixPrinter.printMatrix(matrixC);

        Predicate<Double> condition = (el) -> el == 0.0;
        BoolTransformer<Double> boolTransformer = new BoolTransformer<>(condition);
        Integer[][] matrixD = boolTransformer.getBoolMatrix(matrixC);

        MatrixPrinter<Integer> integerMatrixPrinter = new MatrixPrinter<>();
        System.out.println("D:");
        integerMatrixPrinter.printMatrix(matrixD);

        return null;
    }
}
