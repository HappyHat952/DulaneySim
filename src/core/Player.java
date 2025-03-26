package core;

import levels.Freshman;
import levels.Level;
import org.newdawn.slick.Graphics;
import setup.Fonts;

public class Player {

    private static float GPA;
    private static float coolness;
    private static int grade;
    private static Level currentLevel;
    private static int stage;

    public Player() {
        GPA = 3.5f;
        coolness = 50;
        grade = 9;
        currentLevel = new Freshman();
        stage = 1;
    }

    public static void render(Graphics g)
    {
        g.setFont(Fonts.big);
        g.drawString("GPA: "+ GPA, 15,15);
        g.drawString("Coolness: "+ coolness, 15,55);
        g.drawString("Grade: "+ grade, 15,105);
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
