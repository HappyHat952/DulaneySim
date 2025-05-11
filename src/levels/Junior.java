package levels;

import core.CutScene;
import racer.obstacles.Couple;
import racer.obstacles.Mouse;
import racer.obstacles.Spill;
import setup.Images;

import java.util.ArrayList;

public class Junior extends Level {

    public Junior() {
        grade = 11;
        gradeName = "Junior";
        obstacles = new ArrayList<>();
        obstacles.add(Spill.class);
        obstacles.add(Mouse.class);
        obstacles.add(Couple.class);
        cutSceneID = 0;

//        protected ArrayList<Obstacle> obstacles;
//        protected Conversation convo;
        testID = "Test_1";
        convoID = "mcVeigh";
        convoSprite = Images.veltImage;
        convoBg = Images.veltBG;
        cutScenes.add(new CutScene(1, Images.lunch3, "LUNCH TIME", "EAT", "DON'T EAT", "You're feeling a little " +
                "sick, but you've earned some street cred", "Taking the safe option... so you think you're smart?", 2 , Images.coolLunch, Images.wimpLunch));
        cutScenes.add(new CutScene(2, Images.bathroom, "SNOW DAY!", "SKIP VIRTUAL CLASS", "LOG ON TO CLASS", "You've got courage, that's " +
                "for sure... The teachers aren't going to like this.", "You've avoided violence... for now", 2, Images.bathroom1, Images.bathroom2));
        cutScenes.add(new Transition(2, Images.transition1));
    }
}
