package app.model.matrix;

public class BoolTransformer<T extends Number> {

    public Integer[][] getBoolMatrix(T[][] matrix) {
        Integer[][] graphMatrix = new Integer[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            T[] tmpRandomArr = matrix[i];
            Integer[] tmpGraphMatrix = graphMatrix[i];
            for (int j = 0; j < matrix[0].length; j++) {
                tmpGraphMatrix[j] = replaceElementWithBool(tmpRandomArr[j]);
            }
        }

        return graphMatrix;
    }

    private Integer replaceElementWithBool(T el) {
        return el.doubleValue() < 1.0 ? 0 : 1;
    }
}