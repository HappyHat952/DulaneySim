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
        testID = "Test_3";
        convoID = "Malafarina";
        convoSprite = Images.malImage;
        convoBg = Images.malBG;
        cutScenes.add(new CutScene(1, Images.lunch3, "LUNCH TIME", "EAT", "DON'T EAT", "Those jalapeno poppers are making your stomach upset,"
                + " but you're lowkey cool now :P", "The worm in your apple is disappointed you didn't eat him", 2 , Images.coolLunch, Images.wimpLunch));
        cutScenes.add(new CutScene(2, Images.bathroom, "BATHROOM", "ENTER", "RUN", "You enter the stall... The toilet is full of a " +
                "mystery liquid and when you flush the bathroom starts flooding. The teachers blame you for it.",
                "You avoided losing HP to the smells of the bathroom, but you have to hold it all day. Maybe you should have risked it...", 2, Images.bathroom1, Images.bathroom2));
        cutScenes.add(new Transition(2, Images.transition1));
    }
}
