package app.model;

import java.util.ArrayList;
import java.util.List;

public final class MatrixToListConverter {
    public static ArrayList<ArrayList<Integer>> convertUndirectedMatrix (Integer[][] matrix) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i != j && matrix[i][j] == 1)
                    list.add(new ArrayList<>(List.of(i, j)));
            }
        }
        return list;
    }
}
