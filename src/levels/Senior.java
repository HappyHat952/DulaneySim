package levels;

import core.CutScene;
import racer.obstacles.Couple;
import racer.obstacles.LunchTray;
import racer.obstacles.Mouse;
import racer.obstacles.Spill;
import setup.Images;

import java.util.ArrayList;

public class Senior extends Level {
    public Senior() {
        grade = 12;
        gradeName = "Senior";

        obstacles = new ArrayList<>();
        obstacles.add(Spill.class);
        obstacles.add(Mouse.class);
        obstacles.add(Couple.class);
        obstacles.add(LunchTray.class);
        cutSceneID = 0;

//        protected ArrayList<Obstacle> obstacles;
//        protected Conversation convo;
        testID = "Test_4";
        convoID = "Shaw";
        convoSprite = Images.shawImage;
        convoBg = Images.shawBG;
        cutScenes.add(new CutScene(1, Images.lunch4, "LUNCH TIME", "EAT", "DON'T EAT", "That hamburger was dryer than the sahara desert, "
                + "and there seemed to be a new ecosystem growing in your strawberries. Yum", "Last lunch of the year and you're too scared " +
                "to try? Bruh", 2, Images.coolLunch, Images.wimpLunch));
        cutScenes.add(new CutScene(2, Images.parking, "PARKING", "PARK ILLEGALLY", "FIND ANOTHER SPOT", "You come back to your car later and "
                + "see it's been rear ended by a teacher. Looks like someone wanted to teach you a lesson...", "You are forced to park at the top of Padonia, and must walk" +
                " miles to get to school. But your car is safe.", 2, Images.parking1, Images.parking2));
        //cutScenes.add(new Transition(2, Images.transition1));
    }
}
