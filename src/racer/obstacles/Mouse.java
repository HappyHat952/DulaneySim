package racer.obstacles;

import core.Main;
import setup.Images;

public class Mouse extends Obstacle {
    public Mouse() {
        super();
        image = Images.mouse;
        name = "Mouse";
        w = image.getWidth();
        h = image.getHeight();
    }
}
