package core;

import org.newdawn.slick.Image;

public class CutScene {

    private int id;
    private Image image;
    private String title;
    private String op1;
    private String op2;
    private String cons1;
    private String cons2;

    public CutScene(int id, Image image, String title, String op1, String op2, String cons1, String cons2) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.op1 = op1;
        this.op2 = op2;
        this.cons1 = cons1;
        this.cons2 = cons2;
    }




}
