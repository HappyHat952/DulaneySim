package racer;

import org.newdawn.slick.Image;
import setup.Images;

public class Sign extends Obstacle{

    public Sign() {
        super();
        image = Images.obstacle;
        name = "Warning Sign";
    }
}
