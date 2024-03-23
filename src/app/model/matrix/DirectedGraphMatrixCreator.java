package app.model.matrix;

public class DirectedGraphMatrixCreator {
    private final Double[][] randomMatrix;
    public DirectedGraphMatrixCreator(Double[][] randomMatrix) {
        this.randomMatrix = randomMatrix;
    }

    public Integer[][] getGraphMatrix() {
        Integer[][] graphMatrix = new Integer[randomMatrix.length][randomMatrix[0].length];

        for (int i = 0; i < randomMatrix.length; i++) {
            Double[] tmpRandomArr = randomMatrix[i];
            Integer[] tmpGraphMatrix = graphMatrix[i];
            for (int j = 0; j < randomMatrix[0].length; j++) {
                tmpGraphMatrix[j] = replaceElementWithBool(tmpRandomArr[j]);
            }
        }

        return graphMatrix;
    }

    private Integer replaceElementWithBool(Double el) {
        return el < 1.0 ? 0 : 1;
    }
}
