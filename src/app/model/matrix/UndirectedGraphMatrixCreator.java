package app.model.matrix;

public class UndirectedGraphMatrixCreator {
    private Integer[][] undirectedMatrix;
    public UndirectedGraphMatrixCreator(Integer[][] undirectedMatrix) {
        this.undirectedMatrix = undirectedMatrix;
    }

    public Integer[][] getGraphMatrix() {
        Integer[][] graphMatrix = undirectedMatrix.clone();
        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < graphMatrix[0].length; j++) {
                if (i != j && graphMatrix[i][j] == 1)
                    graphMatrix[j][i] = 1;
            }
        }

        return graphMatrix;
    }
}
