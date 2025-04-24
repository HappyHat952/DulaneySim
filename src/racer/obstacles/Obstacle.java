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
    protected float xAdd;
    protected Image image;
    protected String name;
    protected int finalX;

    public Obstacle() {
//        this.image = Images.obstacle;
        y = Main.getScreenHeight() * .14f;
        finalX = (int) (Math.random() * Main.getScreenWidth());

        double random = Math.random();

        Point start = new Point(Main.getScreenWidth()*.5f, y);
        Point end = new Point(finalX, Main.getScreenHeight());

        if (random < .25) {
            xAdd = 4.5f;
        } else if (random < .5) {
            xAdd = -4.5f;
        } else if (random < .75) {
            xAdd = 10;
        } else {
            xAdd = -10;
        }

    }

    public float getScale() {
        int startY = (int) (Main.getScreenHeight() * 0.14f);
        int endY = Main.getScreenHeight();

        // Can adjust this later to make it better
        float add = 2 * ((y-startY)/(endY-startY));
        return (0.2f + add);
    }

    public void update() {
        y += (float) RacerState.getyAdd() /2;
        if (RacerState.getyAdd() == 10) {
            x += xAdd/2;
        } else {
            x += xAdd;
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
//        g.drawImage(image, x, y);
        g.drawImage(image.getScaledCopy(getScale()), x, y);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setxAdd(float xAdd) {
        this.xAdd = xAdd;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }
}
