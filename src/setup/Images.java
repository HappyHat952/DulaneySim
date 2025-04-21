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

    //locker
    static public Image locker;
    static public Image home;
    static public Image help;
    static public Image sticker;

    //conversation
    static public Image mcVBG;
    static public SpriteSheet mcVImage;


    // cutscene
    static public Image lunch1;
    static public Image lunch2;
    static public Image lunch3;
    static public Image lunch4;
    static public Image fight;
    static public Image transition1;

    // ui
    static public SpriteSheet volumeSheet;

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
            sticker = new Image("res/locker/sticker.png");

            //conversation
            mcVBG = new Image("res/conversation/backdrop/mcVeighBackdrop.png");
            mcVImage = new SpriteSheet("res/conversation/teachers/mcVeighSprite.png", 1920, 1080);

            //cutscene
            lunch1 = new Image("res/cutscene/lunch/lunch1.png");
            lunch2 = new Image("res/cutscene/lunch/lunch2.png");
            lunch3 = new Image("res/cutscene/lunch/lunch3.png");
            lunch4 = new Image("res/cutscene/lunch/lunch4.png");
            fight = new Image("res/cutscene/fight.png");
            transition1 = new Image("res/cutscene/transition1.png");

            // ui
            volumeSheet = new SpriteSheet("res/ui/volumeSheet.png", 100, 100);


        } catch (SlickException e) {
            System.out.println("don't load");
            e.printStackTrace();
        }
    }
}
