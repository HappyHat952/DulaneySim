package levels;

import core.CutScene;
import setup.Images;

public class Sophomore extends Level {
    public Sophomore() {
        grade = 10;
        gradeName = "Sophomore";

//        protected ArrayList<Obstacle> obstacles;
//        protected Conversation convo;
        testID = "test1";
        cutScenes.add(new CutScene(1, Images.lunch2, "LUNCH TIME", "EAT", "DON'T EAT", "You're feeling a little " +
                "sick from that taco salad...", "What, you didn't want to try the mexican radish? Coward", 2));
        cutScenes.add(new CutScene(2, Images.fight, "SNOW DAY!", "SKIP VIRTUAL CLASS", "LOG ON TO CLASS", "You enjoy some sledding" +
                " with friends, but you've been marked absent and now have 10 missings.", "You watch the snow fall outside, but stay inside working..." +
                " your grades are safe.", 2));
    }
}
