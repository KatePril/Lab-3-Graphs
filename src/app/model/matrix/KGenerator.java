package app.model.matrix;

import app.utils.Constants;

public final class KGenerator {
    public static Double getK1() {
        return 1.0 - Constants.n3 * 0.01 - Constants.n4 * 0.01 - 0.3;
    }

    public static Double getK2() {
        return 1.0 - Constants.n3 * 0.005 - Constants.n4 * 0.005 - 0.27;
    }
}
