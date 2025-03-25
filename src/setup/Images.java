package setup;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Images {

    //title/general
    static public Image title;

    //racer
    static public SpriteSheet racerBackground;
    static public Image racer;
    static public Image obstacle;
    static public Image locker;

    //locker
    static public Image home;
    static public Image help;

    //conversation
    static public Image mcVBG;
    static public SpriteSheet mcVImage;


    // cutscene
    static public Image lunch1;
    static public Image fight;

    // REMINDER TO SCALE TO SCREEN WIDTH AND HEIGHT
    public static void loadImages() {

        try {
            //Title/general
            title = new Image("res/title/title.png").getScaledCopy(1920, 1080);

            //racer
            racerBackground = new SpriteSheet("res/racer/racerBackground.png", 1920, 1080);
            racer = new Image("res/racer/racer.png");
            obstacle = new Image("res/racer/obstacle.png");

            //locker
            locker = new Image("res/locker/locker.png");
            home = new Image("res/locker/home.png");
            help = new Image("res/locker/help.png");

            //conversation
            mcVBG = new Image("res/conversation/backdrop/mcVeighBackdrop.png");
            mcVImage = new SpriteSheet ("res/conversation/teachers/mcVeighSprite.png", 1920,1080);

            //cutscene
            lunch1 = new Image("res/cutscene/lunch/lunch1.png");
            fight = new Image("res/cutscene/fight.png");


        } catch (SlickException e) {
            System.out.println("don't load");
            e.printStackTrace();
        }
    }
}
