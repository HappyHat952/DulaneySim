package core;

import assignment.AssignState;
import levels.Title;
import locker.Locker;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import racer.RacerState;
import teacherTalk.TeacherTalk;

public class Main extends StateBasedGame {
    public final static int FRAMES_PER_SECOND = 60;
    private static AppGameContainer appgc;

    public static final int TITLE_ID = 0;
    public static final int GAME_ID = 1;
    public static final int RACER_ID = 2;
    public static final int TEACHER_ID = 3;
    public static final int ASSIGN_ID = 4;
    public static final int LOCKER_ID = 5;
    public static final int CUTSCENE_ID = 6;

    private BasicGameState title;
    private BasicGameState game;
    private BasicGameState racer;
    private BasicGameState teacher;
    private BasicGameState assign;
    private BasicGameState locker;
    private BasicGameState cutscene;

    public Main(String name) {
        super(name);

        title = new Title(TITLE_ID);
        game = new Game(GAME_ID);
        racer = new RacerState(RACER_ID);
        teacher = new TeacherTalk(TEACHER_ID);
        assign = new AssignState(ASSIGN_ID);
        locker = new Locker(LOCKER_ID);
        cutscene = new CutState(CUTSCENE_ID);
    }

    public static int getScreenWidth() {
        return appgc.getScreenWidth();
    }

    public static int getScreenHeight() {
        return appgc.getScreenHeight();
    }


    public void initStatesList(GameContainer gc) throws SlickException {
        addState(title);
        addState(game);
        addState(racer);
        addState(teacher);
        addState(assign);
        addState(locker);
        addState(cutscene);
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
            e.printStackTrace()  ;
        }

    }
}