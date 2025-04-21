package core;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;
import setup.Images;
import ui.buttons.Button;
import ui.buttons.CutsceneButton;
import ui.buttons.StateChangeButton;

import static core.Main.LOCKER_ID;
import static locker.Locker.renderVolume;
import static locker.Locker.updateVolume;

public class CutState extends BasicGameState {
    private int id;
    private StateBasedGame sbg;
    private GameContainer gc;
    private StateChangeButton stateButton;
    private CutScene currentScene;
    private String choice;
    private int currentFrame;

    public CutState(int id) {
        this.id = id;
    }

    @Override
    public void keyPressed(int key, char c) {

    }


    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.gc = gc;
        sbg = stateBasedGame;
        setCurrentScene();
        currentFrame = 1;
        choice = "";
        stateButton = new StateChangeButton((int) (Main.getScreenWidth() * .8f), (int) (Main.getScreenHeight() * .1f), Color.red,
                "Finish CutState", LOCKER_ID, sbg);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        currentScene.render(g, currentFrame, choice);
        Player.render(g);
        renderVolume(g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {


    }

    @Override
    public void mousePressed(int button, int x, int y) {
        if (stateButton.isMouseOver(x, y)) {
            stateButton.action();
            Player.getCurrentLevel().nextCutScene(sbg);
        }
        try {
            currentScene.mousePressed(x, y, sbg, gc);
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
        updateVolume(x, y);
    }

    public void setChoice(String choice) {
        this.choice = choice;
        System.out.println("Changed choice to " + choice);
    }

    public void nextFrame() {
        currentFrame++;
    }

    public void setCurrentScene(){
        currentScene = Player.getCurrentLevel().getCurrentScene();
        System.out.println("Changed scene to this thing");
    }

    public void goToLocker() {
        sbg.enterState(LOCKER_ID);
    }
}
