package levels;

import core.CutScene;
import setup.Images;

public class Senior extends Level {
    public Senior() {
        grade = 10;
        gradeName = "Sophomore";

//        protected ArrayList<Obstacle> obstacles;
//        protected Conversation convo;
        testID = "test1";
        cutScenes.add(new CutScene(1, Images.lunch4, "LUNCH TIME", "EAT", "DON'T EAT", "You're feeling a little " +
                "sick, but you've earned some street cred", "Taking the safe option... so you think you're smart?", 2));
        cutScenes.add(new CutScene(2, Images.fight, "SNOW DAY!", "SKIP VIRTUAL CLASS", "LOG ON TO CLASS", "You've got courage, that's " +
                "for sure... The teachers aren't going to like this.", "You've avoided violence... for now", 2));
    }
}
