package app.model.matrix;

public class DirectedGraphMatrixCreator {
    private final Double[][] randomMatrix;
    public DirectedGraphMatrixCreator(Double[][] randomMatrix) {
        this.randomMatrix = randomMatrix;
    }

    public Integer[][] getGraphMatrix() {
        BoolTransformer<Double> boolTransformer = new BoolTransformer<>();
        return boolTransformer.getBoolMatrix(randomMatrix);
    }
}
