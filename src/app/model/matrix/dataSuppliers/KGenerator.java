package app.model.matrix.dataSuppliers;

import app.utils.Constants;

public final class KGenerator {

    public static Double getK() {
        return 1.0 - Constants.n3 * 0.01 - Constants.n4 * 0.005 - 0.15;
    }
    public static Double getK1() {
        return 1.0 - Constants.n3 * 0.02 - Constants.n4 * 0.005 - 0.25;
    }
}