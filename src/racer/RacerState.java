package racer;

import core.Main;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import setup.Images;


import java.util.ArrayList;

public class RacerState extends BasicGameState {

    private int id;
    private StateBasedGame sbg;
    Racer r;
    private SpriteSheet background;
    private int maxCount;
    private int count;
    private int frame;
    private static int FPS;
    private int counter;
    private int screenHeight;
    private int screenWidth;
    private ArrayList<Obstacle> obstacles;

    public RacerState(int id) {
        this.id = id;
    }


    public void cleanUp() {
        obstacles.removeIf(o -> o.getY() > screenHeight);
    }

    @Override
    public void keyPressed(int key, char c) {
        if (c == 'a') {
            r.moveLeft();
        }
        if (c == 'd') {
            r.moveRight();
        }
        if (c == 'x') {
            sbg.enterState(Main.ASSIGN_ID);
        }
    }

    public static int getFPS() {
        return FPS;
    }

    public void resetGame() {
        FPS = 22;
        r.setX(Main.getScreenWidth()/2 - Images.racer.getWidth());
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
        maxCount = background.getVerticalCount();
        r = new Racer();
        obstacles = new ArrayList<Obstacle>();
        count = 0;
        frame = 0;
        sbg = stateBasedGame;
        FPS = 10;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.drawImage(background.getSprite(0, frame), 0, 0);
        for (Obstacle o: obstacles) {
            o.render(g);
        }
        r.render(g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame stateBasedGame, int i) throws SlickException {
        //UPDATE PENGUIN AND THE OBSTACLES
//        if (!pause && !gameOver){
        r.update();
        for (Obstacle o: obstacles) {
            o.update();
        }

        if (count % 10 == 0 && Math.random() <.01f) {
            obstacles.add(new Obstacle());
        }

        cleanUp();

        // UPDATE SCREEN
        count++;
        if (count % FPS == 0 && frame < maxCount - 1) {
            frame++;
        } else if (count % FPS == 0 & frame == maxCount - 1) {
            frame = 0;
        }

        // MOVE PENGUIN
        if (gc.getInput().isKeyDown(Input.KEY_A)) {
            r.moveLeft();
        }
        if (gc.getInput().isKeyDown(Input.KEY_D)) {
            r.moveRight();
        }
        if (gc.getInput().isKeyDown(Input.KEY_W)) {
            r.moveUp();
        }

        // IF OBSTACLES ARE HIT
        for (int a = 0; a < obstacles.size(); a++) {
            Obstacle o = obstacles.get(a);
            if (r.isOver(o)) {
                FPS = 20;
                counter = 60;
                i--;
            }
        }

        if (counter > 0) {
            counter--;
            if (counter == 0) {
                FPS = 10;
            }
        }
    }
}
