package racer.obstacles;

import core.Main;
import setup.Images;

public class Mouse extends Obstacle {
    public Mouse() {
        super();
        image = Images.mouse;
        name = "Mouse";
        x = Main.getScreenWidth() * .5f - (float) image.getWidth() / 2;
        w = image.getWidth();
        h = image.getHeight();
    }
}
