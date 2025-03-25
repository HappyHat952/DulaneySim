package levels;

import core.Main;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;
import setup.Images;
import ui.buttons.StateChangeButton;

public class Title extends BasicGameState {

    private int id;
    private StateBasedGame sbg;

    private StateChangeButton button;

    public Title(int id) {
        this.id = id;

    }

    public int getID() {
        return id;
    }


    public void init(GameContainer gameContainer, StateBasedGame sbg) throws SlickException {
        this.sbg = sbg;
        Fonts.loadFonts();
        button = new StateChangeButton((int)(Main.getScreenWidth() * .8f), (int)(Main.getScreenHeight()*.6f), Color.red,
                "Start Game",  Main.LOCKER_ID, sbg);
    }


    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

        g.drawImage(Images.title, 0, 0);

        g.setColor(Color.white);
        g.setFont(Fonts.big);
        g.drawString("Dulaney Simulator", Main.getScreenWidth() * .3f, Main.getScreenHeight() * .31f);
        g.drawString("Press X to Start", Main.getScreenWidth() * .3f, Main.getScreenHeight() * .51f);

        button.render(g);
    }


    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
    }


    public void keyPressed(int key, char c) {
        if (key == Input.KEY_G)
        {
            sbg.enterState(Main.GAME_ID);
        }
        if (key == Input.KEY_T)
        {
            sbg.enterState(Main.TEACHER_ID);
        }
        if (key == Input.KEY_A)
        {
            sbg.enterState(Main.ASSIGN_ID);
        }
        if (key == Input.KEY_L)
        {
            sbg.enterState(Main.LOCKER_ID);
        }

    }

    @Override
    public void mousePressed(int button, int x, int y) {
        super.mousePressed(button, x, y);
        this.button.click(x,y);
    }
}
