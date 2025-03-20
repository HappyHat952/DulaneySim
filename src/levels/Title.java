package levels;

import core.Main;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;
import setup.Images;

public class Title extends BasicGameState {

    private int id;
    private StateBasedGame sbg;
    public Title(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }


    public void init(GameContainer gameContainer, StateBasedGame sbg) throws SlickException {
        this.sbg = sbg;
    }


    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.drawImage(Images.title, 0, 0);
        g.setFont(Fonts.big);
        g.drawString("Dulaney Simulator", Main.getScreenWidth() * .3f, Main.getScreenHeight() * .31f);
    }


    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }


    public void keyPressed(int key, char c) {
        if (key == Input.KEY_G)
        {
            sbg.enterState(Main.GAME_ID);
        }
    }
}
