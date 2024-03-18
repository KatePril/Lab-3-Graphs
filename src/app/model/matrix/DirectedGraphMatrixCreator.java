package app.model.matrix;

public class DirectedGraphMatrixCreator {
    private Integer[][] graphMatrix;
    public DirectedGraphMatrixCreator(Double[][] randomMatrix) {
        fillGraphMatrix(randomMatrix);
    }

    private void fillGraphMatrix(Double[][] randomMatrix) {
        graphMatrix = new Integer[randomMatrix.length][randomMatrix[0].length];

        for (int i = 0; i < randomMatrix.length; i++) {
            for (int j = 0; j < randomMatrix[0].length; j++) {
                graphMatrix[i][j] = replaceElementWithBool(randomMatrix[i][j]);
            }
        }
    }

    private Integer replaceElementWithBool(Double el) {
        return el < 1.0 ? 0 : 1;
    }

    public Integer[][] getGraphMatrix() {
        return graphMatrix;
    }
}
