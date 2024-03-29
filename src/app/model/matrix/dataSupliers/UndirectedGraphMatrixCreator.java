package app.model.matrix.dataSupliers;

public class UndirectedGraphMatrixCreator {
    private final Integer[][] directedMatrix;
    public UndirectedGraphMatrixCreator(Integer[][] directedMatrix) {
        this.directedMatrix = directedMatrix;
    }

    public Integer[][] getGraphMatrix() {
        Integer[][] graphMatrix = new Integer[directedMatrix.length][directedMatrix[0].length];
        for (int i = 0; i < graphMatrix.length; i++) {
            Integer[] tmpGraphArr = graphMatrix[i];
            Integer[] tmpDirectedArr = directedMatrix[i];
            for (int j = 0; j < graphMatrix[0].length; j++) {
                if (tmpDirectedArr[j] == 1) {
                    tmpGraphArr[j] = 1;
                    graphMatrix[j][i] = 1;
                } else if (tmpGraphArr[j] == null)
                    tmpGraphArr[j] = 0;
            }
        }

        return graphMatrix;
    }
}
