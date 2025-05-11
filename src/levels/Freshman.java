package levels;

import core.CutScene;
import racer.obstacles.Spill;
import setup.Images;

import java.util.ArrayList;

public class Freshman extends Level {

    public Freshman() {
        grade = 9;
        gradeName = "Freshman";
        cutSceneID = 0;

        obstacles = new ArrayList<>();
        obstacles.add(Spill.class);

        testID = "Test_1";
        convoID = "Shaw";
        convoSprite = Images.shawImage;
        convoBg = Images.shawBG;
        this.cutScenes.add(new CutScene(1, Images.lunch1, "LUNCH TIME", "EAT", "DON'T EAT", "That heavenly hot dog has granted you some street cred, but you'll be spending some extra time in the bathroom later... Send a prayer to the janitors", "What?! You missed out on some tasty food... But you also escaped the listeria outbreak, so maybe this was the better choice", 2, Images.coolLunch, Images.wimpLunch));
        this.cutScenes.add(new CutScene(2, Images.fight, "FIGHT!", "FIGHT", "DON'T FIGHT", "You've got courage, that's for sure... But you've been called to the principal's office, and now you have two weeks of detention. RIP your GPA", "You've avoided violence... for now. Unfortunately the fight was recorded and you look lame for not joining in. Might be best to avoid social media for the next few days.", 2, Images.fight1, Images.fight2));
        cutScenes.add(new Transition(3, Images.transition1));
    }
}
