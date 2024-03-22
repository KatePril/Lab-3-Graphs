package app.model.matrix;

public class UndirectedGraphMatrixCreator {
    private final Integer[][] directedMatrix;
    public UndirectedGraphMatrixCreator(Integer[][] directedMatrix) {
        this.directedMatrix = directedMatrix;
    }

    public Integer[][] getGraphMatrix() {
        Integer[][] graphMatrix = new Integer[directedMatrix.length][directedMatrix[0].length];
        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < graphMatrix[0].length; j++) {
                if (directedMatrix[i][j] == 1) {
                    graphMatrix[i][j] = 1;
                    graphMatrix[j][i] = 1;
                } else if (graphMatrix[i][j] == null)
                    graphMatrix[i][j] = 0;
            }
        }

        return graphMatrix;
    }
}
