package core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import racer.RacerState;

public class Main extends StateBasedGame {
    public final static int FRAMES_PER_SECOND = 60;
    private static AppGameContainer appgc;

    public static final int GAME_ID = 0;
    public static final int RACER_ID = 1;

    private BasicGameState game;
    private BasicGameState racer;

    public Main(String name) {
        super(name);

        game = new Game(GAME_ID);
        racer = new RacerState(RACER_ID);
    }

    public static int getScreenWidth() {
        return appgc.getScreenWidth();
    }

    public static int getScreenHeight() {
        return appgc.getScreenHeight();
    }


    public void initStatesList(GameContainer gc) throws SlickException {
        addState(game);
        addState(racer);
    }

    public static void main(String[] args) {
        try {
            appgc = new AppGameContainer(new Main("My First Project"));
            System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

            appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), false);
            appgc.setTargetFrameRate(FRAMES_PER_SECOND);
            appgc.setVSync(true);
            appgc.start();

        } catch (SlickException e) {
            e.printStackTrace();
        }

    }
}