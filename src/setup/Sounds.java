package setup;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Sounds {

    public static Sound click;

    public static void loadSounds() throws SlickException {
        click = new Sound("res/sounds/click.wav");
    }

}