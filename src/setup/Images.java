package setup;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Images {

    //title/general
    static public Image title;
    static public Image logo;

    //racer
    static public SpriteSheet racerBackground;
    static public Image racer;
    static public Image hit;
    static public Image spill;
    static public Image mouse;
    static public Image couple;
    static public Image lunchTray;

    //locker
    static public Image locker;
    static public Image runButton;
    static public Image help;
    static public Image sticker9;
    static public Image sticker10;
    static public Image sticker11;
    static public Image sticker12;

    //conversation
    static public Image mcVBG;
    static public SpriteSheet mcVImage;

    static public Image veltBG;
    static public SpriteSheet veltImage;

    static public Image malBG;
    static public SpriteSheet malImage;

    static public Image shawBG;
    static public SpriteSheet shawImage;

    // cutscene
    static public Image lunch1;
    static public Image lunch2;
    static public Image lunch3;
    static public Image lunch4;
    static public Image fight;
    static public Image fight1;
    static public Image fight2;

    static public Image transition1;
    static public Image snowDay;
    static public Image snow1;
    static public Image snow2;
    static public Image bathroom;
    static public Image bathroom1;
    static public Image bathroom2;
    static public Image parking;
    static public Image parking1;
    static public Image parking2;

    static public Image coolLunch;
    static public Image wimpLunch;

    static public Image graduation;
    static public Image expulsion;


    // ui
    static public SpriteSheet volumeSheet;

    // REMINDER TO SCALE TO SCREEN WIDTH AND HEIGHT
    public static void loadImages() {

        try {
            //Title/general
            title = new Image("res/title/title.png").getScaledCopy(1920, 1080);
            logo = new Image("res/title/logo.png");

            //racer
            racerBackground = new SpriteSheet("res/racer/racerBackgroundLoop.png", 1920, 1080);

            racer = new Image("res/racer/racer.png");
            hit = new Image("res/racer/hit.png");
            spill = new Image("res/racer/spill.png");
            mouse = new Image("res/racer/mouse.png");
            couple = new Image("res/racer/couple.png");
            lunchTray = new Image("res/racer/lunchTray.png");

            //locker
            locker = new Image("res/locker/locker.png");
            runButton = new Image("res/locker/runButton.png");
            help = new Image("res/locker/help.png");
            sticker9 = new Image("res/locker/sticker9.png");
            sticker10 = new Image("res/locker/sticker10.png");
            sticker11 = new Image("res/locker/sticker11.png").getScaledCopy(sticker10.getWidth(), sticker10.getHeight());
            sticker12 = new Image("res/locker/sticker12.png");

            //conversation
            mcVBG = new Image("res/conversation/backdrop/mcVeighBackdrop.png");
            mcVImage = new SpriteSheet("res/conversation/teachers/mcVeighSprite.png", 1920, 1080);

            veltBG = new Image("res/conversation/backdrop/veltenBackdrop.png");
            veltImage = new SpriteSheet("res/conversation/teachers/veltenSprite.png",1906,1080);

            malBG = new Image("res/conversation/backdrop/MalafarinaBg.png");
            malImage = new SpriteSheet("res/conversation/teachers/malafarinaSprite.png", 1024, 1024);

            shawBG = new Image("res/conversation/backdrop/shawBG.png");
            shawImage = new SpriteSheet("res/conversation/teachers/shawSprite.png", 1280, 1280);

            //cutscene
            lunch1 = new Image("res/cutscene/lunch/lunch1.png");
            lunch2 = new Image("res/cutscene/lunch/lunch2.png");
            lunch3 = new Image("res/cutscene/lunch/lunch3.png");
            lunch4 = new Image("res/cutscene/lunch/lunch4.png");
            fight = new Image("res/cutscene/fight.png");
            fight1 = new Image("res/cutscene/fight1.png");
            fight2 = new Image("res/cutscene/fight2.png");
            transition1 = new Image("res/cutscene/transition1.png");
            snowDay = new Image("res/cutscene/snowDay.png");
            snow1 = new Image("res/cutscene/snow1.png");
            snow2 = new Image("res/cutscene/snow2.png");
            bathroom = new Image("res/cutscene/bathroom.png");
            bathroom1 = new Image("res/cutscene/bathroom1.png");
            bathroom2 = new Image("res/cutscene/bathroom2.png");

            parking = new Image("res/cutscene/parking.png");
            parking1 = new Image("res/cutscene/parking1.png");
            parking2 = new Image("res/cutscene/parking2.png");

            coolLunch = new Image("res/cutscene/lunch/coolLunch.png").getScaledCopy(400, 400);
            wimpLunch = new Image("res/cutscene/lunch/wimpLunch.png");

            //
            graduation = new Image ("res/title/gradImage.png");
            expulsion = new Image ("res/title/expelled.png");



            // ui
            volumeSheet = new SpriteSheet("res/ui/volumeSheet.png", 100, 100);


        } catch (SlickException e) {
            System.out.println("don't load");
            e.printStackTrace();
        }
    }
}
