package levels;

import assignment.AssignState;
import assignment.Assignment;
import core.CutScene;
import core.CutState;
import core.Player;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import racer.obstacles.Obstacle;
import racer.RacerState;
import teacherTalk.TeacherTalk;

import java.util.ArrayList;

abstract public class Level {
    // not a game state, but keeps track of each level's Information
    protected int grade;
    protected String gradeName;

    protected ArrayList<Class<? extends Obstacle>> obstacles;

    protected String convoID;
    protected Image convoBg;
    protected SpriteSheet convoSprite;
    protected Assignment assignment;
    protected String testID;

    protected ArrayList<CutScene> cutScenes = new ArrayList();
    protected int cutSceneID;

    //cutscene per grade level
    //sticker per grade level.

    public void setLevel() {
        AssignState.setAssignment(testID);
        TeacherTalk.setConversation(convoID, convoBg, convoSprite);
        RacerState.resetObstacles(obstacles);
    }

    public void nextCutScene(GameContainer gc, StateBasedGame sbg) throws SlickException {
        if (cutSceneID + 1 < cutScenes.size()) {
            cutSceneID++;
            sbg.getCurrentState().init(gc, sbg);
            System.out.println("Id is " + cutSceneID);

        } else {
            System.out.println("RECHED!");
            if (Player.getGrade() == 9)
            {
                ((CutState) (sbg.getCurrentState())).goToGraduation();
            }
            else{
                ((CutState) (sbg.getCurrentState())).goToLocker();
            }

            setCutSceneID(0);
            Player.addGrade();
            System.out.println("Ending");
        }
    }

    public CutScene getCurrentScene() {
        return cutScenes.get(cutSceneID);
    }

    public void setCutSceneID(int a) {
        cutSceneID = a;
    }

    public ArrayList<Class<? extends Obstacle>> getObstacles() {
        return obstacles;
    }
}
