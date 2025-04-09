package levels;

import core.CutScene;
import racer.Obstacle;
import setup.Images;
import teacherTalk.Conversation;

import java.util.ArrayList;

public class Freshman extends Level {

    public Freshman() {
        grade = 9;
        gradeName = "Freshman";

//        protected ArrayList<Obstacle> obstacles;
//        protected Conversation convo;
        testID = "test1";
        cutScenes.add(new CutScene(1, Images.lunch1, "LUNCH TIME", "EAT", "DON'T EAT", "You're feeling a little " +
                "sick, but you've earned some street cred", "Taking the safe option... so you think you're smart?"));
        cutScenes.add(new CutScene(2, Images.fight, "FIGHT!", "FIGHT", "DON'T FIGHT", "You've got courage, that's " +
                "for sure... The teachers aren't going to like this.", "You've avoided violence... for now"));
    }
}
