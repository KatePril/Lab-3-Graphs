package app.model.matrix.dataSuppliers;

import app.utils.Constants;

public class KGenerator {
    public static Double getK() {
        return 1.0 - Constants.n3 * 0.02 - Constants.n4 * 0.005 - 0.25;
    }
}
