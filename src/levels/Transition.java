package levels;

import core.CutScene;
import core.Main;
import core.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import ui.buttons.StateChangeButton;

import static core.Main.CUTSCENE_ID;

public class Transition extends CutScene {
//    public Transition(int id, Image image, String title, String op1, String op2, String cons1, String cons2, int totalFrames) {
//        super(id, image, title, op1, op2, cons1, cons2, totalFrames);
//    }

    public Transition(int id, Image image) {
        super();
        this.image = image;
        leaveButton = new StateChangeButton((int) (Main.getScreenWidth() * .85f), (int) (Main.getScreenHeight() * .1f), Color.yellow,
                "Leave", CUTSCENE_ID, sbg);
    }

    @Override
    public void render(Graphics g, int currentFrame, String choice) {
        g.drawImage(image, 0, 0);
        leaveButton.render(g);
    }

    public void mousePressed(int x, int y, StateBasedGame sbg, GameContainer gc) throws SlickException {
        this.sbg = sbg;
        if (leaveButton.isMouseOver(x, y)) {
            Player.getCurrentLevel().nextCutScene(gc, sbg);
            sbg.getCurrentState().init(gc, sbg);
//            leaveButton.action();

        }
    }
}
