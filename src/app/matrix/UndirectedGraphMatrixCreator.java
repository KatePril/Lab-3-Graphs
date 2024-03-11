package app.matrix;

public class UndirectedGraphMatrixCreator {
    private Integer[][] graphMatrix;
    public UndirectedGraphMatrixCreator(Integer[][] undirectedMatrix) {
        graphMatrix = undirectedMatrix.clone();
        fillGraphMatrix();
    }

    private void fillGraphMatrix() {
        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < graphMatrix[0].length; j++) {
                if (i != j && graphMatrix[i][j] == 1)
                    graphMatrix[j][i] = 1;
            }
        }
    }

    public Integer[][] getGraphMatrix() {
        return graphMatrix;
    }
}
