package locker;

import core.Main;
import core.Player;
import org.newdawn.slick.*;
import ui.buttons.Button;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;
import setup.Images;
import ui.buttons.StateChangeButton;

import java.util.ArrayList;

public class Locker extends BasicGameState {
    private int id;
    private Button run;
    private ArrayList<Button> lockerButtons;

    private static Image volumeOn;
    private static Image volumeOff;
    public static boolean volume;

    public Locker(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // This code happens when you enter a game state for the *first time.*
        gc.setShowFPS(true);
        lockerButtons = new ArrayList<>();
        run = new StateChangeButton((int) (Main.getScreenWidth() * .728f), (int) (Main.getScreenHeight() * .42f),
                Images.runButton, sbg, "Run To Class", Main.RACER_ID);
        lockerButtons.add(run);
        volume = true;
        volumeOn = Images.volumeSheet.getSprite(0, 0);
        volumeOff = Images.volumeSheet.getSprite(0, 1);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        // This updates your game's logic every frame.  NO DRAWING.
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        // This code renders shapes and images every frame.
        g.setBackground(Color.black);
        g.drawImage(Images.locker, 0, 0);
        for (Button b: lockerButtons) {
            b.render(g);
        }
        g.setFont(Fonts.big);
        g.setColor(Color.black);
        if (Player.getGPA()<0)
        {
            g.drawString("GPA:----", Main.getScreenWidth() * .120f, Main.getScreenHeight() * .27f);
        }
        else {
            g.drawString("GPA: " + Player.getGPA(), Main.getScreenWidth() * .120f, Main.getScreenHeight() * .27f);
        }

        g.drawString("Grade: " + Player.getGrade(), Main.getScreenWidth() * .440f, Main.getScreenHeight() * .21f);
        renderVolume(g);
        renderStickers(g);
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // This code happens when you enter a gameState.
    }

    public void leave(GameContainer gc, StateBasedGame sbg) {
        // This code happens when you leave a gameState.
    }

    public void keyPressed(int key, char c) {
        // This code happens every time the user presses a key
    }

    public void mousePressed(int button, int x, int y) {
        // This code happens every time the user presses the mouse
        for (Button b: lockerButtons) {
            b.click(x, y);
        }
        updateVolume(x, y);
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
    }

    public static void renderVolume(Graphics g){
        if (volume) {
            g.drawImage(volumeOn, 0, 0);
        } else {
            g.drawImage(volumeOff, 0, 0);
        }
    }

    public static void updateVolume(int x, int y){
        if (x >= 0 && x <= volumeOn.getWidth() && y >= 0 && y < volumeOn.getHeight()) {
            if (volume) {
                volume = false;
            } else {
                volume = true;
            }
        }
    }
    public void renderStickers(Graphics g) {
        g.setColor(Color.black);
        g.setFont(Fonts.medium);
        g.drawString("MY STICKERS", Main.getScreenWidth() * .11f, Main.getScreenHeight() * .5f);

        if (Player.getGrade() == 10) {
            g.drawImage(Images.sticker9, Main.getScreenWidth() * .05f, Main.getScreenHeight() * .58f);
        } else if (Player.getGrade() == 11) {
            g.drawImage(Images.sticker9, Main.getScreenWidth() * .05f, Main.getScreenHeight() * .58f);
            g.drawImage(Images.sticker10, Main.getScreenWidth() * .2f, Main.getScreenHeight() * .58f);
        } else if (Player.getGrade() == 12) {
            g.drawImage(Images.sticker9, Main.getScreenWidth() * .05f, Main.getScreenHeight() * .58f);
            g.drawImage(Images.sticker10, Main.getScreenWidth() * .2f, Main.getScreenHeight() * .58f);
            g.drawImage(Images.sticker11, Main.getScreenWidth() * .05f, Main.getScreenHeight() * .78f);
        } else if (Player.getGrade() != 9) {
            g.drawImage(Images.sticker9, Main.getScreenWidth() * .05f, Main.getScreenHeight() * .60f);
            g.drawImage(Images.sticker10, Main.getScreenWidth() * .2f, Main.getScreenHeight() * .60f);
            g.drawImage(Images.sticker11, Main.getScreenWidth() * .05f, Main.getScreenHeight() * .80f);
            g.drawImage(Images.sticker12, Main.getScreenWidth() * .2f, Main.getScreenHeight() * .80f);
        }
        // ADD ONCE GRADUATED TO SHOW LAST ONE
    }
}
