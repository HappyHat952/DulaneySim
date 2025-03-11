package racer;

import core.Main;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import setup.Images;


import java.util.ArrayList;

public class RacerState extends BasicGameState {

    private int id;
    Runner r;
    private Image background1;
    private Image background2;
    private int y1;
    private int y2;
    private static int speed;
    private int counter;
    private int screenHeight;
    private int screenWidth;
    private ArrayList<Obstacle> obstacles;

    public RacerState(int id) {
        this.id = id;

//        gameOverScreen = Images.timesUp;
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
        background1 = Images.racerBackground;
        background2 = Images.racerBackground;
        screenHeight = Main.getScreenHeight();
        screenWidth = Main.getScreenWidth();
        r = new Runner();
        y1 = 0;
        y2 = background1.getHeight() * -1;
        obstacles = new ArrayList<Obstacle>();
        speed = 22;
        counter = 0;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.drawImage(background1, 0, y1);
        g.drawImage(background2, 0, y2);
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
        int j = 0;
        while (y1 < screenHeight && y2 < screenHeight && j <= speed) {
            y1++;
            y2++;
            j++;
        }
        if (y1 >= screenHeight) {
            y1 = background1.getHeight() * -1;
        }
        if (y2 >= screenHeight) {
            y2 = background1.getHeight() * -1;
        }
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
