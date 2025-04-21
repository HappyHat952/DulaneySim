package racer.obstacles;

import core.Main;
import setup.Images;

public class Spill extends Obstacle {

    public Spill() {
        super();
        image = Images.spill;
        name = "Spill";
        x = Main.getScreenWidth() * .5f - (float) image.getWidth() / 2;
        w = image.getWidth();
        h = image.getHeight();
    }
}
