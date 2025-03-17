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
    Runner r;
    private SpriteSheet background;
    private int y1;
    private int y2;
    private static int speed;
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
//        super.keyPressed(key, c);
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

    public static int getSpeed() {
        return speed;
    }

    public void resetGame() {
//        super.resetGame();
//        gameOver = false;
        speed = 22;
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
        r = new Runner();
        obstacles = new ArrayList<Obstacle>();

        sbg = stateBasedGame;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.drawImage(background, 0, y1);
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

        if (Math.random() <.03f) {
            obstacles.add(new Obstacle());
        }

        cleanUp();

        // UPDATE SCREEN

        // MOVE PENGUIN
        if (gc.getInput().isKeyDown(Input.KEY_A)) {
            r.moveLeft();
        }
        if (gc.getInput().isKeyDown(Input.KEY_D)) {
            r.moveRight();
        }

        // IF OBSTACLES ARE HIT
        for (int a = 0; a < obstacles.size(); a++) {
            Obstacle o = obstacles.get(a);
            if (r.isOver(o)) {
//                    r.setImage(Images.penguinHit);
                speed = 10;
                counter = 60;
                i--;
            }
        }

        if (counter > 0) {
            counter--;
            if (counter == 0) {
                speed = 22;
//                    r.setImage(Images.penguin);
            }
        }
    }
}
