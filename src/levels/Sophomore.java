package levels;

import core.CutScene;
import racer.obstacles.Mouse;
import racer.obstacles.Spill;
import setup.Images;

import java.util.ArrayList;

public class Sophomore extends Level {
    public Sophomore() {
        grade = 10;
        gradeName = "Sophomore";
        cutSceneID = 0;

        obstacles = new ArrayList<>();
        obstacles.add(Spill.class);
        obstacles.add(Mouse.class);

        testID = "Test_2";
        convoID = "Velten";
        convoSprite = Images.veltImage;
        convoBg = Images.veltBG;
        this.cutScenes.add(new CutScene(1, Images.lunch2, "LUNCH TIME", "EAT", "DON'T EAT", "You're feeling a little sick from that taco salad... Pero fue muy divertido comer.", "What, you didn't want to try the mexican radish? You're a loser.", 2, Images.coolLunch, Images.wimpLunch));
        this.cutScenes.add(new CutScene(2, Images.snowDay, "SNOW DAY!", "SKIP VIRTUAL CLASS", "LOG ON TO CLASS", "You enjoy some sledding with friends, but you've been marked absent and now have 10 missings. Better hop on the grind or your grades are gonna be cooked.", "You watch the snow fall outside, but stay inside working... Your teachers respect your grind, but you missed out on sledding withfriends :(", 2, Images.snow1, Images.snow2));
        cutScenes.add(new Transition(3, Images.transition1));
    }
}
