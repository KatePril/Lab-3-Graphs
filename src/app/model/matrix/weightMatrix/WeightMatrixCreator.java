package app.model.matrix.weightMatrix;

import app.model.matrix.boolTransformers.BiBoolTransformer;
import app.model.matrix.boolTransformers.BoolTransformer;
import app.model.matrix.MatrixCalculator;
import app.model.matrix.ScalarMultiplier;
import app.model.matrix.dataSuppliers.RandomMatrixCreator;
import app.model.matrix.dataSuppliers.TrMatrixCreator;
import app.view.matrix.MatrixPrinter;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class WeightMatrixCreator {
    private final Integer[][] matrixA;

    public WeightMatrixCreator(Integer[][] matrixA) {
        this.matrixA = matrixA;
    }

    public Integer[][] getWeightMatrix() {
        Double[][] matrixB = getMatrixB();
        Integer[][] matrixC = getMatrixC(matrixB);
        Integer[][] matrixD = getMatrixD(matrixC);
        Integer[][] matrixH = getMatrixH(matrixD);
        Integer[][] matrixTr = TrMatrixCreator.getTrMatrix(matrixA.length);

        return getMatrixW(matrixC, matrixD, matrixH, matrixTr);
    }

    private Double[][] getMatrixB() {
        RandomMatrixCreator randomMatrixCreator = new RandomMatrixCreator(matrixA.length);
        return randomMatrixCreator.getMatrix();
    }

    private Integer[][] getMatrixC(Double[][] matrixB) {
        ScalarMultiplier<Integer> scalarMultiplier = new ScalarMultiplier<>();
        Double[][] c = scalarMultiplier.scalarMultiply(MatrixCalculator.multiplyElementWise(matrixA, matrixB), 100);

        Integer[][] matrixC = new Integer[c.length][c[0].length];
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                matrixC[i][j] = c[i][j].intValue();
                if (matrixC[i][j] != 0) {
                    matrixC[i][j] += 1;
                }
            }
        }
        return matrixC;
    }

    private Integer[][] getMatrixD(Integer[][] matrixC) {
        Predicate<Integer> condition = (el) -> el == 0.0;
        BoolTransformer<Integer> boolTransformer = new BoolTransformer<>(condition);
        return boolTransformer.getBoolMatrix(matrixC);
    }

    private Integer[][] getMatrixH(Integer[][] matrixD) {
        BiPredicate<Integer, Integer> biCondition = Integer::equals;
        BiBoolTransformer<Integer> biBoolTransformer = new BiBoolTransformer<>(biCondition);
        return biBoolTransformer.getBoolMatrix(matrixD);
    }

    private Integer[][] getMatrixW(Integer[][] c, Integer[][] d, Integer[][] h, Integer[][] tr) {
        Integer[][] w = new Integer[c.length][c[0].length];
        int k = 0;
        for (int i = 0; i < w.length; i++) {
            for (int j = k; j < w.length; j++) {
                int value = (d[i][j] + h[i][j] * tr[i][j]) * c[i][j];
                w[i][j] = value;
                w[j][i] = value;
            }
            ++k;
        }

        return w;
    }
}