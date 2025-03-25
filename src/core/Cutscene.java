package core;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import racer.Obstacle;
import racer.Racer;
import setup.Images;

import java.util.ArrayList;

public class Cutscene extends BasicGameState {
    private int id;
    private StateBasedGame sbg;
    private SpriteSheet background;
    private int screenHeight;
    private int screenWidth;

    public Cutscene(int id) {
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
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = Images.racerBackground;
        screenHeight = Main.getScreenHeight();
        screenWidth = Main.getScreenWidth();
        sbg = stateBasedGame;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
       g.drawImage(Images.fight, Main.getScreenWidth() *.5f - (float) Images.fight.getWidth() /2, Main.getScreenHeight() * .1f);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
