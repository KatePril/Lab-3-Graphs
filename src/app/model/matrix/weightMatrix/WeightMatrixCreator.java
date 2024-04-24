package app.model.matrix.weightMatrix;

import app.model.matrix.dataSuppliers.RandomMatrixCreator;

public class WeightMatrixCreator {
    private final Integer[][] matrixA;

    public WeightMatrixCreator(Integer[][] matrixA) {
        this.matrixA = matrixA;
    }

    private Double[][] getWeightMatrix() {
        RandomMatrixCreator randomMatrixCreator = new RandomMatrixCreator(matrixA.length);
        Double[][] matrixB = randomMatrixCreator.getMatrix();
        return null;
    }
}
