package racer.obstacles;

import core.Main;
import setup.Images;

public class Spill extends Obstacle {

    public Spill() {
        super();
        image = Images.spill;
        name = "Spill";
        w = image.getWidth();
        h = image.getHeight();
    }
}
