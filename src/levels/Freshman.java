package levels;

import core.CutScene;
import racer.Obstacle;
import racer.Sign;
import setup.Images;
import teacherTalk.Conversation;

import java.util.ArrayList;

public class Freshman extends Level {

    public Freshman() {
        grade = 9;
        gradeName = "Freshman";
        cutSceneID = 0;

         obstacles = new ArrayList<>();
         obstacles.add(Sign.class);

        testID = "Test_1";
        convoID = "mcVeigh";
        cutScenes.add(new CutScene(1, Images.lunch1, "LUNCH TIME", "EAT", "DON'T EAT", "That heavenly hot dog has" +
                " granted you some street cred, but you'll be spending some extra time in the bathroom later...",
                "What?! You're missing out on some tasty fud", 2));
        cutScenes.add(new CutScene(2, Images.fight, "FIGHT!", "FIGHT", "DON'T FIGHT", "You've got courage, that's " +
                "for sure... The teachers aren't going to like this.", "You've avoided violence... for now", 2));
        cutScenes.add(new Transition(3, Images.transition1));
    }
}
