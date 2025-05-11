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
    private static int frame;
    private static int count;
    private static int speed;
    private int hitCount;
    private int screenHeight;
    private int screenWidth;
    private boolean isHit;
    private static ArrayList<Obstacle> obstacles;
    private static ArrayList<Class<? extends Obstacle>> obstaclesClass;

    private static int distanceToClass;
    private static int travelled;

    private boolean complete;

    private StateChangeButton classButton;

    public RacerState(int id) {
        this.id = id;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        resetObstacles(Player.getCurrentLevel().getObstacles());
        init(container, game);
        resetObstacles(Player.getCurrentLevel().getObstacles());

        complete = false;
        isHit = false;
        hitCount = 0;

        screenHeight = Main.getScreenHeight();
        screenWidth = Main.getScreenWidth();

        classButton = new StateChangeButton((int) (Main.getScreenWidth() * .45f), (int) (Main.getScreenHeight() * .45f),
                Color.orange, "Enter Class", Main.TEACHER_ID, game);
    }

    public static void resetObstacles(ArrayList<Class<? extends Obstacle>> o) {
        background = Images.racerBackground;
        r = new Racer();
        obstacles = new ArrayList<>();

        count = 0;
        frame = 0;
        speed = 1;

        distanceToClass = 2400;
        travelled = 0;

        obstaclesClass = Player.getCurrentLevel().getObstacles();
    }


    public void cleanUp() {
        for (Obstacle o : obstacles) {
            if (o.getY() > screenHeight) {
                int random = (int) (Math.random() * Main.getScreenWidth() *.32f);
                int multiplier;
                if (Math.random() < 0.5) {
                    multiplier = -1;
                } else {
                    multiplier = 1;
                }
                o.setX(Main.getScreenWidth() * .5f + (random * multiplier));
                o.setY(0);

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
        r = new Racer();
        obstacles = new ArrayList<>();
        count = 0;
        frame = 0;
        speed = 1;
        sbg = stateBasedGame;

        distanceToClass = 2400;
        travelled = 0;

        obstaclesClass = Player.getCurrentLevel().getObstacles();

        classButton = new StateChangeButton((int) (Main.getScreenWidth() * .45f), (int) (Main.getScreenHeight() * .45f),
                Color.orange, "Enter Class", Main.TEACHER_ID, sbg);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.setBackground(Color.black);
        int columns = 6;
        int xIndex = frame % columns;
        int yIndex = frame / columns;

        g.drawImage(background.getSprite(xIndex, yIndex), 0, 0);
        for (Obstacle o : obstacles) {
            o.render(g);
        }
        r.render(g);
        if (complete) {
            classButton.render(g);
        }

        if (isHit) {
            g.drawImage(Images.hit, r.getX() + 5, r.getY() + 100);
        }
        g.setFont(Fonts.big);
        g.setColor(Color.white);
        g.drawString("Use WASD TO MOVE", Main.getScreenWidth() * .70f, 10);

        g.setLineWidth(2);
        g.setColor(Color.black);
        g.drawRect(10, 190, 15, 500);

        g.setLineWidth(1);
        g.setColor(Color.red);
        if (1 - travelled * 1f / distanceToClass < 1) {
            g.fillRect(10, 190 + 500 * (1 - travelled * 1f / distanceToClass), 15, 500 * (travelled * 1f / distanceToClass));
        } else {
            g.fillRect(10, 190, 15, 500);
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

        if (count < 120 & count % 20 == 0) {
            obstacles.add(createNewObstacle());
        }


        if (travelled <= distanceToClass && !complete) {

            count++;
            if (count % speed == 0) {
                if (frame < 35) {
                    frame++;
                } else {
                    frame = 0;
                }
            }

            r.update();

            for (Obstacle o : obstacles) {
                o.update();
            }


            int baseSpeed = 2;
            travelled += baseSpeed * speed;

            if (travelled >= distanceToClass) {
                travelled = distanceToClass;
                complete = true;
            }

            r.update();
            for (Obstacle o : obstacles) {
                o.update();
            }
//
            cleanUp();
//
//            // UPDATE SCREEN
//
            // MOVE RACER
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
                    hitCount = 60;
                    speed = 2;
                    isHit = true;
                }
            }

            if (hitCount > 0) {
                hitCount--;
                if (hitCount == 0) {
                    speed = 1;
                    isHit = false;
                }
            }
        } else {
            complete = true;
        }

        MessageManager.update();

    }


    public static int getSpeed() {
        return speed;
    }
}


