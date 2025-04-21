package levels;

import core.CutScene;
import racer.Sign;
import setup.Images;

import java.util.ArrayList;

public class Senior extends Level {
    public Senior() {
        grade = 10;
        gradeName = "Sophomore";

        obstacles = new ArrayList<>();
        obstacles.add(Sign.class);
        cutSceneID = 0;

//        protected ArrayList<Obstacle> obstacles;
//        protected Conversation convo;
        testID = "Test_1";
        convoID = "mcVeigh";
        cutScenes.add(new CutScene(1, Images.lunch4, "LUNCH TIME", "EAT", "DON'T EAT", "You're feeling a little " +
                "sick, but you've earned some street cred", "Taking the safe option... so you think you're smart?", 2));
//        cutScenes.add(new CutScene(2, Images.fight, "SNOW DAY!", "SKIP VIRTUAL CLASS", "LOG ON TO CLASS", "You've got courage, that's " +
//                "for sure... The teachers aren't going to like this.", "You've avoided violence... for now", 2));
        cutScenes.add(new Transition(2, Images.transition1));
    }
}
