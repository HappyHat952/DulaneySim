package racer.obstacles;

import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import racer.RacerState;
import setup.Images;

public class Obstacle {

    protected float x;
    protected float y;
    protected int w;
    protected int h;

    protected Image image;
    protected String name;


    public Obstacle() {
//        this.image = Images.obstacle;
        int random = (int) (Math.random() * Main.getScreenWidth() *.32f);
        int multiplier;
        if (Math.random() < 0.5) {
            multiplier = -1;
        } else {
            multiplier = 1;
        }
        x = Main.getScreenWidth() * .5f + (random * multiplier);
        y = 0;


    }

    public void update() {
       if (RacerState.getSpeed() == 1) {
           y += 5;
       } else {
           y += 3;
       }
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void render(Graphics g) {
        g.drawImage(image, x, y);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }
}
