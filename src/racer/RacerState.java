package racer;

import core.Main;
import core.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;
import setup.Images;
import ui.buttons.StateChangeButton;


import java.util.ArrayList;

import static locker.Locker.renderVolume;
import static locker.Locker.updateVolume;

public class RacerState extends BasicGameState {

    private int id;
    private StateBasedGame sbg;
    Racer r;
    private SpriteSheet background;
    private int maxCount;
    private int count;
    private int frame;
    private static int yAdd;
    private int FPS;
    private int counter;
    private int screenHeight;
    private int screenWidth;
    private static ArrayList<Obstacle> obstacles;

    private int distanceToClass;
    private int travelled;

    private boolean complete;

    private StateChangeButton classButton;

    public RacerState(int id) {
        this.id = id;
    }

    public static void setObstacles(ArrayList<Obstacle> obstacs) {
        obstacles = obstacs;
    }


    public void cleanUp() {
        for (Obstacle o : obstacles) {
            if (o.getY() > screenHeight) {
                o.setX(screenWidth * .5f - (float) o.getImage().getWidth() / 2);
                o.setY(screenHeight * .14f);

                double random = Math.random();
                float xAdd;

                if (random < .25) {
                    xAdd = 4.5f;
                } else if (random < .5) {
                    xAdd = -4.5f;
                } else if (random < .75) {
                    xAdd = 10;
                } else {
                    xAdd = -10;
                }
                o.setxAdd(xAdd);
            }
        }
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

    public static int getyAdd() {
        return yAdd;
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
        yAdd = 20;
        FPS = 10;

        distanceToClass = 400;
        travelled = 0;

        classButton = new StateChangeButton((int) (Main.getScreenWidth() * .8f), (int) (Main.getScreenHeight() * .03f),
                Color.orange, "Enter Class", Main.TEACHER_ID, sbg);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.setBackground(Color.black);
        g.drawImage(background.getSprite(0, frame), 0, 0);
        for (Obstacle o : obstacles) {
            o.render(g);
        }
        r.render(g);
        if (complete) {
            classButton.render(g);
        }
        g.setFont(Fonts.big);
        g.setColor(Color.black);
        g.drawString("Use WASD TO MOVE", Main.getScreenWidth() * .71f, 20);

        g.setLineWidth(2);
        g.setColor(Color.black);
        g.drawRect(10, 190, 15, 500);

        g.setLineWidth(1);
        g.setColor(Color.red);
        g.fillRect(10, 190 + 500 * (1 - travelled * 1f / distanceToClass), 15, 500 * (travelled * 1f / distanceToClass));

        Player.render(g);
        renderVolume(g);
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        super.mousePressed(button, x, y);
        if (complete) {
            classButton.click(x, y);
        }
        updateVolume(x, y);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (count < 60 & count % 15 == 0) {
            obstacles.add(new Obstacle(Images.obstacle));
        }

        if (travelled <= distanceToClass) {
            r.update();
            for (Obstacle o : obstacles) {
                o.update();
            }

            cleanUp();

            // UPDATE SCREEN
            if (yAdd == 20) {
                FPS = 10;
            } else {
                FPS = 20;
            }


            count++;
            if (count % FPS == 0 && frame < maxCount - 1) {
                frame++;

            } else if (count % FPS == 0 & frame == maxCount - 1) {
                frame = 0;
                travelled += yAdd;
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
                if (r.isOver(o) && !r.isJumping()) {
                    yAdd = 10;
                    counter = 60;
                    i--;
                }
            }

            if (counter > 0) {
                counter--;
                if (counter == 0) {
                    yAdd = 20;
                }
            }
        } else {
            complete = true;
        }

//        if (travelled <= distanceToClass)
//        {

    }
}


