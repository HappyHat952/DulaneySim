package locker;

import core.Main;
import core.Player;
import ui.buttons.Button;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;
import setup.Images;
import ui.buttons.HomeButton;

import java.util.ArrayList;

public class Locker extends BasicGameState {
    private int id;
    private Button home;
    private Button help;
    private ArrayList<Button> lockerButtons;

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
        home = new HomeButton((int) (Main.getScreenWidth() * .735f), (int) (Main.getScreenHeight() * .35f),
                Images.home.getScaledCopy((int) (Main.getScreenWidth() * .08f), (int) (Main.getScreenHeight() * .14f)), sbg);
//        help = new Button((int) (Main.getScreenWidth() * .735f), (int) (Main.getScreenHeight() * .55f),
//                Images.help.getScaledCopy((int) (Main.getScreenWidth() * .08f), (int) (Main.getScreenHeight() * .14f)),
//        "help");
        lockerButtons.add(home);
//        lockerButtons.add(help);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        // This updates your game's logic every frame.  NO DRAWING.
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        // This code renders shapes and images every frame.
       g.drawImage(Images.locker, 0, 0);
        for (Button b: lockerButtons) {
            b.render(g);
        }
       g.setFont(Fonts.big);
       g.setColor(Color.black);
       g.drawString("GPA: " + Player.getGPA(), Main.getScreenWidth() * .120f, Main.getScreenHeight() * .27f);
       g.drawString("Grade: " + Player.getGrade(), Main.getScreenWidth() * .440f, Main.getScreenHeight() * .21f);
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
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
    }
}
