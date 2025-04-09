package assignment;

import core.Main;
import core.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;
import ui.buttons.Button;
import ui.buttons.StateChangeButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment {

    private int id;
    private StateBasedGame sbg;

    private static ArrayList<String> assignment;
    private static ArrayList<MultipleChoice> mcqs;

    private static boolean complete;
    private static float grade;


    public Assignment(String testID) {
        this.id = id;
        mcqs = new ArrayList<>();
        setAssignment(testID);
    }

    public boolean isComplete(){ return complete;}

    public void update() throws SlickException {
        // This updates your game's logic every frame.  NO DRAWING.
    }

    public void render(Graphics g) throws SlickException {
        // This code renders shapes and images every frame.

        g.setBackground(Color.orange);
        g.setColor(Color.white);
        g.fillRect(200,0,Main.getScreenWidth() - 400, Main.getScreenHeight() );

        g.setFont(Fonts.big);
        g.setColor(Color.red);
        if (complete)
        {
            g.drawString((""+grade*100+"%"),Main.getScreenWidth()*.5f, Main.getScreenHeight()*.3f);
        }

        g.setColor(Color.black);
        g.setFont(Fonts.small);
//        float y = Main.getScreenHeight()*.1f;
//        for (String s: assignment)
//        {
//            y+= 20;
//            g.drawString(s,Main.getScreenWidth()*.2f, y);
//        }
        if (mcqs!= null && !mcqs.isEmpty())
        {
            for (MultipleChoice mcq: mcqs)
            {
                mcq.draw(g);
                System.out.println("mc is 'drawing' "+mcqs);
            }
        }
//        if (complete)
//        {
//            cutSceneButton.render(g);
//        }
//        else
//        {
//            submitBtn.render(g);
//        }

        g.setColor(Color.black);
        Player.render(g);

    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // This code happens when you enter a gameState.
    }

    public void leave(GameContainer gc, StateBasedGame sbg) {
        // This code happens when you leave a gameState.
    }

    public void keyPressed(int key, char c) {
        // This code happens every time the user presses a key
    }

    public void submit()
    {
        int points =0;
        for (MultipleChoice m: mcqs)
        {
            if (m.grade())
            {
                points ++;
            }
            else {
                Player.adjustGPA(-.2f);
            }
        }
        grade = points*1f/mcqs.size();
        complete = true;
    }

    public void mousePressed(int button, int x, int y) {
        // This code happens every time the user presses the mouse

        if (!complete) {

                for (MultipleChoice m : mcqs)
                {
                    m.click(x,y);
                }
        }



    }

    public void setAssignment(String id)
    {
        assignment = new ArrayList<>();
        id = id.replace(" ", "_");
        try
        {
            File f = new File("res/assignment/"+id+".txt");
            Scanner s = new Scanner(f);
            String str = s.nextLine();
            ArrayList<String> q = new ArrayList<>();

            while (!str.equals("end"))
            {
                //  if (str.contains("Qstn: ") && !assignment.isEmpty())
                if (str.length() == 1 && !assignment.isEmpty())
                {
                    q.add(str);
                    assignment.add(str);
                    mcqs.add (new MultipleChoice(q, mcqs.size(),this));
                    q = new ArrayList<>();
                }
                else
                {
                    q.add(str);
//                System.out.print(str);
                    assignment.add(str);

                }
                System.out.println("multiple Choice created."+ mcqs.size());
                str = s.nextLine();

            }
        } catch (FileNotFoundException e) {
            System.out.println("fine wasn't found");
        }

    }
}
