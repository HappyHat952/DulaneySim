package racer.obstacles;

import setup.Images;

public class LunchTray extends Obstacle {
    public LunchTray() {
        super();
        image = Images.lunchTray;
        name = "Lunch Tray";
        w = image.getWidth();
        h = image.getHeight();
    }
}
