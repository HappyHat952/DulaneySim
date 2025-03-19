package setup;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Images {

    static public SpriteSheet racerBackground;
    static public Image racer;
    static public Image obstacle;


    public static void loadImages() {

        try {
            racerBackground = new SpriteSheet("res/racer/racerBackground.png", 1920, 1080);
            racer = new Image("res/racer/racer.png");
            obstacle = new Image("res/racer/obstacle.png");
        } catch (SlickException e) {
            System.out.println("don't load");
            e.printStackTrace();
        }
    }
}
