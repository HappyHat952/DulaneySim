package core;

import levels.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
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
    private static boolean displayOn;


    //info display (effects)


    public Player() {
        GPA = 3.5f;
        coolness = 50;
        grade = 9;
        currentLevel = new Freshman();
        stage = 1;
        stats = new Button(0,110, "Stats", Fonts.big,
                Color.black);

    }

    public static void render(Graphics g )
    {
        boolean overStat =  stats.isMouseOver(Mouse.getX(), Main.getScreenHeight() - Mouse.getY());
        if (Mouse.isGrabbed()&& overStat)
        {
            displayOn = !displayOn;
        }

        Font f = Fonts.medium;
        if (displayOn || overStat)
        {
            g.setColor(Color.black);
            g.fillRect(0,90, 300,f.getHeight("l")*4);

            g.setFont(f);
            g.setColor(Color.red);
            g.drawString("GPA: "+ GPA, 15,125);
            g.setColor(Color.blue);
            g.drawString("Coolness: "+ coolness, 15,165);
            g.setColor(Color.white);
            g.drawString("Grade: "+ grade, 15,215);
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
    public static void setGrade(int g) {grade = g;}

    public static Level getCurrentLevel() {
        return currentLevel;
    }

    public static int stage() {
        return stage;
    }
    public static void addGrade(){
        grade++;
        if (grade == 9) {
            currentLevel = new Freshman();
        } else if (grade == 10) {
            currentLevel = new Sophomore();
        } else if (grade == 11) {
            currentLevel = new Junior();
        } else if (grade == 12) {
            currentLevel = new Senior();
        }
    }

}
