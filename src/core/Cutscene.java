package core;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import racer.Obstacle;
import racer.Racer;
import setup.Images;
import ui.buttons.Button;
import ui.buttons.CutsceneButton;
import ui.buttons.StateChangeButton;

import java.util.ArrayList;

public class Cutscene extends BasicGameState {
    private int id;
    private StateBasedGame sbg;
    private Image image;
    private int screenHeight;
    private int screenWidth;
    private int level;
    private Button left;
    private Button right;
    private StateChangeButton stateButton;
    private float x;
    private float y;
    private String text1;
    private String text2;


    public Cutscene(int id) {
        this.id = id;
        level = 0;
    }

    @Override
    public void keyPressed(int key, char c) {
        if (c == 'x') {
            sbg.enterState(Main.ASSIGN_ID);
        }
    }



    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        screenHeight = Main.getScreenHeight();
        screenWidth = Main.getScreenWidth();
        x = screenWidth * .05f;
        y = screenHeight * .2f;
        sbg = stateBasedGame;
        stateButton = new StateChangeButton((int)(Main.getScreenWidth() * .8f), (int)(Main.getScreenHeight()*.1f), Color.red,
                "Finish Cutscene",  Main.LOCKER_ID, sbg);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
       g.drawImage(image, Main.getScreenWidth() *.5f - (float) image.getWidth() /2, Main.getScreenHeight() * .1f);
       left.render(g);
       right.render(g);
       stateButton.render(g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        level++;

        if (level == 1) {
            image = Images.lunch1;
            text1 = "EAT";
            text2 = "DON'T EAT";
        } else if (level == 2) {
            image = Images.fight;
            text1 = "FIGHT";
            text2 = "DON'T FIGHT";
        }

        left = new CutsceneButton((int) x, (int) y, 200, 100, Color.green, text1, "Interesting choice...");
        right = new CutsceneButton((int) (screenWidth - 3 * x), (int) y, 200, 100, Color.red, text2, "This action has consequences...");
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        left.click(x, y);
        right.click(x, y);
    }
}
