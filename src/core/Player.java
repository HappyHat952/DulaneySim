package core;

import levels.Freshman;
import levels.Level;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import setup.Fonts;
import ui.buttons.Button;

public class Player {

    //basic stats
    private static float GPA;
    private static float coolness;
    private static int grade;
    private static Level currentLevel;
    private static int stage;
    private static Button stats;


    //info display (effects)


    public Player() {
        GPA = 3.5f;
        coolness = 50;
        grade = 9;
        currentLevel = new Freshman();
        stage = 1;
        stats = new Button(10,10, "Stats", Fonts.big,
                Color.black);

    }

    public static void render(Graphics g )
    {
        if (stats.isMouseOver(Mouse.getX(), Main.getScreenHeight() - Mouse.getY()))
        {
            g.setColor(Color.black);
            g.fillRect(10,10, 400,Fonts.big.getHeight()*4);

            g.setFont(Fonts.big);
            g.setColor(Color.red);
            g.drawString("GPA: "+ GPA, 15,15);
            g.setColor(Color.blue);
            g.drawString("Coolness: "+ coolness, 15,55);
            g.setColor(Color.white);
            g.drawString("Grade: "+ grade, 15,105);
        }
        else {
            stats.render(g);
        }

    }

    public static float getGPA() {
        return GPA;
    }
    public static void adjustGPA(float amt) { GPA += amt;}

    public static float getCoolness() {
        return coolness;
    }
    public static void addCoolness(float amt) {coolness += amt;}


    public static int getGrade() {
        return grade;
    }

    public static Level getCurrentLevel() {
        return currentLevel;
    }

    public static int stage() {
        return stage;
    }

}
