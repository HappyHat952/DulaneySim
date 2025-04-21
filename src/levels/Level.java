package levels;

import assignment.AssignState;
import core.CutScene;
import core.CutState;
import core.Player;
import org.newdawn.slick.state.StateBasedGame;
import racer.Obstacle;
import teacherTalk.Conversation;

import java.util.ArrayList;

abstract public class Level {
    // not a game state, but keeps track of each level's Information
    protected int grade;
    protected String gradeName;

    protected ArrayList<Obstacle> obstacles;
    protected Conversation convo;
    protected String testID;
    protected ArrayList<CutScene> cutScenes = new ArrayList();
    protected int cutSceneID;

    //cutscene per grade level
    //sticker per grade level.

    public void setLevel() {
        // AssignState.setAssignment(testID);

    }

    public void nextCutScene(StateBasedGame sbg) {
        if (cutSceneID + 1 < cutScenes.size()) {
            cutSceneID++;
            System.out.println("Id is " + cutSceneID);
        } else {
            ((CutState) (sbg.getCurrentState())).goToLocker();
            Player.addGrade();
        }
    }

    public CutScene getCurrentScene() {
        return cutScenes.get(cutSceneID);
    }


}
