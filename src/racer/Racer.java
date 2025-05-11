package racer;


import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import racer.obstacles.Obstacle;
import setup.Images;

public class Racer {

    private int x;
    private int y;
    private int w;
    private int h;

    private Image image;

    private boolean complete;

    public Racer() {
        image = Images.racer.getScaledCopy(150, 250);

        x = Main.getScreenWidth()/2 - image.getWidth();
        y = Main.getScreenHeight() - (image.getHeight() + 10);

        w = image.getWidth();
        h = image.getHeight();

        complete = false;


    }

    public boolean isComplete(){ return complete;}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getH() {
        return h;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setImage(Image i) {
        image = i;
    }

    public void render(Graphics g) {
        g.drawImage(image, x, y);
    }

    public void update() {

    }

    public void moveRight() {
        float maxX = Main.getScreenWidth() * 0.87f;
        int maxMove = 15;

        float spaceRemaining = maxX - (x + image.getWidth());

        if (spaceRemaining >= 15) {
            x += maxMove;
        } else if (spaceRemaining > 0) {
            x += spaceRemaining;
        }
//        if (x + 15 + image.getWidth() < Main.getScreenWidth() +) {
//            x += 15;
//        }
    }

    public boolean isOver(Obstacle o) {
        if (o.getY() >= y && o.getY() <= y + h && x+w > o.getX() && o.getX() + o.getW() > x) {
            return true;
        }
        return false;
    }

    public void moveLeft() {
        int maxMove = 15;
        float minX = Main.getScreenWidth() * 0.13f;

        float spaceRemaining = x - minX;

        if (spaceRemaining >= maxMove) {
            x -= maxMove;
        } else if (spaceRemaining > 0) {
            x -= spaceRemaining;
        }
    }

}
