package app.model.matrix.weightMatrix;

import app.model.matrix.boolTransformers.BiBoolTransformer;
import app.model.matrix.boolTransformers.BoolTransformer;
import app.model.matrix.MatrixCalculator;
import app.model.matrix.ScalarMultiplier;
import app.model.matrix.dataSuppliers.RandomMatrixCreator;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class WeightMatrixCreator {
    private final Integer[][] matrixA;

    public WeightMatrixCreator(Integer[][] matrixA) {
        this.matrixA = matrixA;
    }

    public Double[][] getWeightMatrix() {
        Double[][] matrixB = getMatrixB();
        Double[][] matrixC = getMatrixC(matrixB);
        Integer[][] matrixD = getMatrixD(matrixC);
        Integer [][] matrixH = getMatrixH(matrixD);

        return null;
    }

    private Double[][] getMatrixB() {
        RandomMatrixCreator randomMatrixCreator = new RandomMatrixCreator(matrixA.length);
        return randomMatrixCreator.getMatrix();
    }

    private Double[][] getMatrixC(Double[][] matrixB) {
        ScalarMultiplier<Integer> scalarMultiplier = new ScalarMultiplier<>();
        return scalarMultiplier.scalarMultiply(MatrixCalculator.multiplyElementWise(matrixA, matrixB), 100);
    }

    private Integer[][] getMatrixD(Double[][] matrixC) {
        Predicate<Double> condition = (el) -> el == 0.0;
        BoolTransformer<Double> boolTransformer = new BoolTransformer<>(condition);
        return boolTransformer.getBoolMatrix(matrixC);
    }

    private Integer[][] getMatrixH(Integer[][] matrixD) {
        BiPredicate<Integer, Integer> biCondition = Integer::equals;
        BiBoolTransformer<Integer> biBoolTransformer = new BiBoolTransformer<>(biCondition);
        return biBoolTransformer.getBoolMatrix(matrixD);
    }
}
