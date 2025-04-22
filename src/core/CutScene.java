package core;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;
import ui.buttons.Button;
import ui.buttons.CutsceneButton;
import ui.buttons.StateChangeButton;
import ui.textBox.TextBox;

import static core.Main.CUTSCENE_ID;

public class CutScene {

    protected int id;
    protected StateBasedGame sbg;
    protected Image image;
    private String title;
    private String op1;
    private String op2;
    private String cons1;
    private String cons2;
    private int totalFrames;
    private CutsceneButton button1;
    private CutsceneButton button2;
    protected Button leaveButton;
    private int offset;
    private int buttonY;
    private int buttonW;
    private int buttonH;
    private TextBox consequence;

    public CutScene(int id, Image image, String title, String op1, String op2, String cons1, String cons2,
                    int totalFrames) {
        leaveButton = new StateChangeButton((int) (Main.getScreenWidth() * .85f), (int) (Main.getScreenHeight() * .1f), Color.yellow,
                "Leave", CUTSCENE_ID, sbg);
        this.id = id;
        this.image = image;
        this.title = title;
        this.op1 = op1;
        this.op2 = op2;
        this.cons1 = cons1;
        this.cons2 = cons2;
        this.totalFrames = totalFrames;
        offset = (int) (Main.getScreenWidth() * .02f);
        buttonY = (int) (Main.getScreenHeight() * .5f);
        buttonW = 250;
        buttonH = 150;
        button1 = new CutsceneButton(offset, buttonY, buttonW, buttonH, Color.green, op1, cons1);
        button2 = new CutsceneButton(Main.getScreenWidth() - offset - buttonW, buttonY, buttonW, buttonH, Color.red, op2, cons2);
    }

    public CutScene() {

    }

    public void render(Graphics g, int currentFrame, String choice) {
        g.setBackground(Color.darkGray);
        if (currentFrame == 1) {
            g.drawImage(image, Main.getScreenWidth() * .05f, Main.getScreenHeight() * .2f);
            button1.render(g);
            button2.render(g);
            g.setColor(Color.black);
            g.setFont(Fonts.big);
            g.drawString(title, Main.getScreenWidth() * .4f, Main.getScreenHeight() * .1f);
        } else if (currentFrame == 2) {
            g.setColor(Color.black);
            g.setFont(Fonts.big);
            if (consequence == null) {
                consequence = new TextBox(choice, Fonts.big, (int) (Main.getScreenWidth() * .25f), (int) (Main.getScreenHeight() * .15f),
                        (int) (Main.getScreenWidth() * .5f), (int) (Main.getScreenHeight() * .5f));
            }
            consequence.draw(g);
//            g.setColor(Color.black);
//            g.setFont(Fonts.big);
//            g.fillRect(Main.getScreenWidth() * .25f, Main.getScreenHeight() * .15f, Main.getScreenWidth() * .5f,
//                    Main.getScreenHeight() * .5f);
//            g.setColor(Color.white);
//            g.drawString(choice, Main.getScreenWidth() * .3f, Main.getScreenHeight() * .3f);
            leaveButton.render(g);
        }
    }

    public void mousePressed(int x, int y, StateBasedGame sbg, GameContainer gc) throws SlickException {
        this.sbg = sbg;
//        leaveButton = new StateChangeButton((int) (Main.getScreenWidth() * .85f), (int) (Main.getScreenHeight() * .1f), Color.yellow,
//                "Leave", CUTSCENE_ID, sbg);

        if (button1.isMouseOver(x, y)) {
            button1.action(sbg);
        } else if (button2.isMouseOver(x, y)) {
            button2.action(sbg);
        } else if (leaveButton.isMouseOver(x, y)) {
            Player.getCurrentLevel().nextCutScene(gc, sbg);
            sbg.getCurrentState().init(gc, sbg);
//            leaveButton.action();

        }
    }


}
