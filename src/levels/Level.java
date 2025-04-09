package levels;

import assignment.AssignState;
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
    //cutscene per grade level
    //sticker per grade level.

    public void setLevel ()
    {
       // AssignState.setAssignment(testID);

    }


    //up

}
