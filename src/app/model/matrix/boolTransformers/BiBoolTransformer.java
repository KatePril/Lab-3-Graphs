package app.model.matrix.boolTransformers;

import java.util.function.BiPredicate;

public class BiBoolTransformer<T extends Number> {

    private final BiPredicate<T, T> condition;

    public BiBoolTransformer(BiPredicate<T, T> condition) {
        this.condition = condition;
    }

    public Integer[][] getBoolMatrix(T[][] matrix) {
        Integer[][] graphMatrix = new Integer[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            T[] tmpRandomArr = matrix[i];
            Integer[] tmpGraphMatrix = graphMatrix[i];
            for (int j = 0; j < matrix[0].length; j++) {
                tmpGraphMatrix[j] = replaceElementWithBool(tmpRandomArr[j], matrix[j][i]);
            }
        }

        return graphMatrix;
    }

    private Integer replaceElementWithBool(T el1, T el2) {
        return condition.test(el1, el2) ? 0 : 1;
    }

}
