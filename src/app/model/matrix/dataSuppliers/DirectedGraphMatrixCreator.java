package app.model.matrix.dataSuppliers;

import app.model.matrix.boolTransformers.BoolTransformer;

import java.util.function.Predicate;

public class DirectedGraphMatrixCreator {
    private final Double[][] randomMatrix;
    public DirectedGraphMatrixCreator(Double[][] randomMatrix) {
        this.randomMatrix = randomMatrix;
    }

    public Integer[][] getGraphMatrix() {
        Predicate<Double> condition = (el) -> el < 1.0;
        BoolTransformer<Double> boolTransformer = new BoolTransformer<>(condition);
        return boolTransformer.getBoolMatrix(randomMatrix);
    }
}
