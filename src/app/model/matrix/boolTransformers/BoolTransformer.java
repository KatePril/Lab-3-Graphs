package app.model.matrix.boolTransformers;

import java.util.function.Predicate;

public class BoolTransformer<T extends Number> {

    private final Predicate<T> condition;

    public BoolTransformer(Predicate<T> condition) {
        this.condition = condition;
    }

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
        return condition.test(el) ? 0 : 1;
    }
}
