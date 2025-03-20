package setup;

import java.awt.*;
import java.io.File;

import org.newdawn.slick.TrueTypeFont;

public class Fonts {
    public static TrueTypeFont big;
    public static TrueTypeFont medium;
    public static TrueTypeFont small;


    public static void loadFonts() {
        big = new TrueTypeFont(new Font("Segoe UI Black", Font.BOLD, 46), false);
        medium = new TrueTypeFont(new Font("Segoe UI Black", Font.PLAIN, 32), false);
        small = new TrueTypeFont(new Font("Segoe UI Black", Font.PLAIN, 15), false);
    }

}
