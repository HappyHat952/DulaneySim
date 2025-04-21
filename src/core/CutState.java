package core;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;
import setup.Images;
import ui.buttons.Button;
import ui.buttons.CutsceneButton;
import ui.buttons.StateChangeButton;

public class CutState extends BasicGameState {
    private int id;
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
    private String testString;
    private String title;
    private boolean showButtons;

    private StateBasedGame sbg;
    private GameContainer gc;


    public CutState(int id) {
        this.id = id;
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
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.gc = gc;
        this.sbg = sbg;

        screenHeight = Main.getScreenHeight();
        screenWidth = Main.getScreenWidth();
        x = screenWidth * .05f;
        y = screenHeight * .2f;
        stateButton = new StateChangeButton((int)(Main.getScreenWidth() * .8f), (int)(Main.getScreenHeight()*.1f), Color.red,
                "Finish Cutscene",  Main.LOCKER_ID, sbg);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
       g.drawImage(image, Main.getScreenWidth() *.5f - (float) image.getWidth() /2, Main.getScreenHeight() * .1f);
       if (showButtons) {
           left.render(g);
           right.render(g);
           g.setColor(Color.white);
           g.setFont(Fonts.big);
           g.drawString(title, Main.getScreenWidth() * .4f, Main.getScreenHeight() * .05f);
       } else {
           g.setColor(Color.darkGray);
           g.fillRect(0, 0, Main.getScreenWidth(), Main.getScreenHeight());
           g.setColor(Color.white);
           g.setFont(Fonts.big);
           g.drawString(testString,Main.getScreenWidth() * .35f, Main.getScreenHeight() * .45f);
           stateButton.render(g);
       }
        Player.render(g);
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
            title = "LUNCH TIME!";
            stateButton = new StateChangeButton((int)(Main.getScreenWidth() * .35f), (int)(Main.getScreenHeight()*.8f), Color.red,
                    "Continue",  Main.CUTSCENE_ID, sbg);
        } else if (level == 2) {
            image = Images.fight;
            text1 = "FIGHT";
            text2 = "DON'T FIGHT";
            title = "FIGHT!";
            stateButton = new StateChangeButton((int)(Main.getScreenWidth() * .35f), (int)(Main.getScreenHeight()*.8f), Color.red,
                    "Continue",  Main.LOCKER_ID, sbg);
        }

        showButtons = true;
        left = new CutsceneButton((int) x, (int) y, 200, 100, Color.green, text1, "Interesting choice...");
        right = new CutsceneButton((int) (screenWidth - 3 * x), (int) y, 200, 100, Color.red, text2, "This action has consequences...");



    }

    @Override
    public void mousePressed(int button, int x, int y) {
        left.click(x, y);
        right.click(x, y);
        if (left.isMouseOver(x, y)) {
            showButtons = false;
            testString = "Interesting choice...";
        } else if (right.isMouseOver(x, y)) {
            showButtons = false;
            testString = "This action will have consequences...";
        } else if (stateButton.isMouseOver(x, y)) {
            stateButton.action();
        }

    }
}
