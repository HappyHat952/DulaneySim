package setup;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Images {

    static public Image title;
    static public SpriteSheet racerBackground;
    static public Image racer;
    static public Image obstacle;
    static public Image locker;
    static public Image home;
    static public Image help;


    // REMINDER TO SCALE TO SCREEN WIDTH AND HEIGHT
    public static void loadImages() {

        try {
            title = new Image("res/title/title.png").getScaledCopy(1920, 1080);
            racerBackground = new SpriteSheet("res/racer/racerBackground.png", 1920, 1080);
            racer = new Image("res/racer/racer.png");
            obstacle = new Image("res/racer/obstacle.png");
            locker = new Image("res/locker/locker.png");
            home = new Image("res/locker/home.png");
            help = new Image("res/locker/help.png");

        } catch (SlickException e) {
            System.out.println("don't load");
            e.printStackTrace();
        }
    }
}
