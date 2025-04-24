package racer;

import core.Main;
import core.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import racer.obstacles.Obstacle;
import racer.obstacles.Spill;
import setup.Fonts;
import setup.Images;
import ui.buttons.StateChangeButton;
import ui.messages.MessageManager;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static locker.Locker.renderVolume;
import static locker.Locker.updateVolume;

public class RacerState extends BasicGameState {

    private int id;
    private StateBasedGame sbg;
    private static Racer r;
    private static SpriteSheet background;
    private static int maxCount;
    private static int count;
    private static int frame;
    private static int yAdd;
    private int FPS;
    private int counter;
    private int screenHeight;
    private int screenWidth;
    private static ArrayList<Obstacle> obstacles;
    private static ArrayList<Class<? extends Obstacle>> obstaclesClass;

    private static int distanceToClass;
    private static int travelled;

    private boolean complete;

    private StateChangeButton classButton;
    private static int hitTimer;

    public RacerState(int id) {
        this.id = id;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        resetObstacles(Player.getCurrentLevel().getObstacles());
        init(container, game);
        hitTimer = 0;
    }

    public static void resetObstacles(ArrayList<Class<? extends Obstacle>> o) {
        background = Images.racerBackground;
        maxCount = background.getVerticalCount();
        r = new Racer();
        obstacles = new ArrayList<>();
        yAdd = 20;

        hitTimer = 0;
        count = 0;
        frame = 0;

        distanceToClass = 10;
        travelled = 0;

        obstaclesClass = Player.getCurrentLevel().getObstacles();

//        obstaclesClass = o;
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
            sbg.enterState(Main.TEACHER_ID);
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
        obstacles = new ArrayList<>();
        count = 0;
        frame = 0;
        sbg = stateBasedGame;
        yAdd = 20;
        FPS = 10;

        distanceToClass = 400;
        travelled = 0;

        obstaclesClass = Player.getCurrentLevel().getObstacles();

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


        if (hitTimer > 0) {
            g.drawImage(Images.hit, r.getX(), (r.getY() + r.getH() * .6f));
        }
        Player.render(g);
        renderVolume(g);

        MessageManager.render(g);
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        super.mousePressed(button, x, y);
        if (complete) {
            classButton.click(x, y);
        }
        updateVolume(x, y);
    }

    public Obstacle createNewObstacle() {
        int i = (int) (obstaclesClass.size() * Math.random());

        Class<? extends Obstacle> clazz = obstaclesClass.get(i);
        Obstacle obstacle = null;

        try {
            obstacle = clazz.getDeclaredConstructor().newInstance();

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return obstacle;

    }

    @Override
    public void update(GameContainer gc, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (count < 60 & count % 15 == 0) {
            obstacles.add(createNewObstacle());
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
                    hitTimer = 20;
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

        MessageManager.update();

        if (hitTimer > 0) {
            hitTimer--;
        }

//        if (travelled <= distanceToClass)
//        {

    }
}


