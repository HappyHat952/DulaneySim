package core;

import levels.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import setup.Fonts;
import ui.buttons.Button;
import ui.messages.FloatMessage;
import ui.messages.MessageManager;

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
        GPA = 3;
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
        //if (displayOn || overStat)
        if (true)
        {
            int xBuffer = 300;
            g.setColor(Color.black);
            g.fillRect(xBuffer,0, 300,f.getHeight("l")*4);

            g.setFont(f);
            g.setColor(Color.red);
            if (GPA<0)
            {
                g.drawString("GPA: ----", xBuffer + 15,35);
            }
            else
            {
                g.drawString("GPA: "+ GPA, xBuffer +15,35);
            }

            g.setColor(Color.blue);
            g.drawString("Coolness: "+ coolness, xBuffer +15,75);
            g.setColor(Color.white);
            g.drawString("Grade: "+ grade, xBuffer +15,125);
        }
        else {
            stats.render(g);
        }

    }

    public static float getGPA() {
        return GPA;
    }
    public static void adjustGPA(float amt) {
        float oldGPA = GPA;
        if (GPA + amt >0)
        {
            GPA += amt;
        }
        else {
            GPA = 0;
        }

        GPA = (float)(Math.round(GPA*1000))/1000;

        MessageManager.addMessage(new FloatMessage(
                "GPA: "+(float)Math.round((GPA-oldGPA)*100)/100,Main.getScreenWidth()/2,250,
                Color.red,140));

    }

    public static void assignmentGPAAdj (float grade)
    {

        float oldGPA =GPA;
        float minGPA;
        if (grade == 1)
        {
            minGPA = 5;
        }
        else if (grade >=.9)
        {
            minGPA = 4;
        }
        else if (grade >= .8)
        {
            minGPA = 3;
        }
        else if (grade >= .7)
        {
            minGPA = 4;
        }
        else if (grade >= .6)
        {
            minGPA = 1;
        }
        else
        {
            minGPA = 0;
        }

        if (GPA<0)
        {
            GPA = minGPA;
        }
        else {
            GPA = (GPA+ minGPA)/2;
        }
        GPA = (float)(Math.round(GPA*1000))/1000;

        MessageManager.addMessage(new FloatMessage(
                "GPA: "+(float)Math.round((GPA-oldGPA)*100)/100,Main.getScreenWidth()/2,250,
                Color.red,140));
    }

    public static float getCoolness() {
        return coolness;
    }
    public static void addCoolness(float amt) {
        coolness += amt;

        MessageManager.addMessage(new FloatMessage(
                "coolness: "+amt,Main.getScreenWidth()/2,300,
                Color.blue,140));
    }


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
        if (grade<12)
        {
            grade++;
        }
        if (grade == 9) {
            currentLevel = new Freshman();
        } else if (grade == 10) {
            currentLevel = new Sophomore();
        } else if (grade == 11) {
            currentLevel = new Junior();
        } else if (grade == 12) {
            currentLevel = new Senior();
        }
        currentLevel.setLevel();
    }

}
