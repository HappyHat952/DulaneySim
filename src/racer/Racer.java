package racer;


import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import setup.Images;
import ui.buttons.StateChangeButton;

public class Racer {

    private int x;
    private int y;
    private int w;
    private int h;
    private int baseY;
    private boolean isJumping;
    private final int jumpHeight = 500;
    private Image image;

    private boolean complete;

    public Racer() {
        image = Images.racer;
        isJumping = false;

        x = Main.getScreenWidth()/2 - image.getWidth();
        y = Main.getScreenHeight() - (image.getHeight() + 10);
        baseY = Main.getScreenHeight() - (image.getHeight() + 10);
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
        if (isJumping) {
            if (y == baseY - jumpHeight) {
                isJumping = false;
            } else {
                y -= 25;
            }
        }

        if (!isJumping && y < baseY) {
            y += 25;
        }
    }

    public void moveRight() {
        if (x + 15 + image.getWidth() < Main.getScreenWidth()) {
            x += 15;
        }
    }

    public boolean isOver(Obstacle o) {
        if (y >= o.getY() && y <= o.getY() + o.getH() && x+w > o.getX() && o.getX() + o.getW() > x) {
            return true;
        }
        return false;
    }

    public void moveLeft() {
        if (x - 15 > 0) {
            x -= 15;
        }
    }

    public void moveUp() {
        if (y == Main.getScreenHeight() - (image.getHeight() + 10)) {
            isJumping = true;
        }
    }

    public boolean isJumping()
    {
        return isJumping;
    }
}
