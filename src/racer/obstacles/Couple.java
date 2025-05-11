package racer.obstacles;

import setup.Images;

public class Couple extends Obstacle{

    public Couple() {
        super();
        image = Images.couple;
        name = "Couple";
        w = image.getWidth();
        h = image.getHeight();
    }
}
