package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import ui.buttons.Button;
import ui.buttons.CutsceneButton;

public class CutScene {

    private int id;
    private Image image;
    private String title;
    private String op1;
    private String op2;
    private String cons1;
    private String cons2;
    private Button button1;
    private Button button2;
    private int offset;
    private int buttonY;
    private int buttonW;
    private int buttonH;

    public CutScene(int id, Image image, String title, String op1, String op2, String cons1, String cons2) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.op1 = op1;
        this.op2 = op2;
        this.cons1 = cons1;
        this.cons2 = cons2;
        offset = (int) (Main.getScreenWidth() * .05f);
        buttonY = (int) (Main.getScreenHeight() *  .05f);
        buttonW = 50;
        buttonH = 50;
        button1 = new CutsceneButton(offset, buttonY, buttonW, buttonH, Color.green, op1, cons1);
        button2 = new CutsceneButton(Main.getScreenWidth() - offset - buttonW, 1, buttonW, buttonH, Color.red, op2, cons2);
    }

    public void render(Graphics g) {
        g.drawImage(image, 0, 0);
        button1.render(g);
        button2.render(g);
    }




}
