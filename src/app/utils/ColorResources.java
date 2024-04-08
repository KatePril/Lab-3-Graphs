package app.utils;

import java.awt.*;
import java.util.Random;

public final class ColorResources {

    public static final Color BROWN = new Color(80, 63, 0);
    public static final Color BLUE = new Color(32, 106, 255);
    public static final Color GREEN = new Color(53, 199, 0);
    public static final Color RED = new  Color(236, 0, 0);
    public static final Color PURPLE = new  Color(201, 37, 255);

    public static Color getRandomColor() {
        Random colorGenerator = new Random();
        return new Color(colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256), colorGenerator.nextInt(0, 256));
    }
}
