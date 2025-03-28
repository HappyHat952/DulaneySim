package assignment;

import core.Main;
import core.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;
import ui.buttons.Button;
import ui.buttons.StateChangeButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Assignment extends BasicGameState {
    private int id;
    private StateBasedGame sbg;

    private ArrayList<String> assignment;
    private ArrayList<MultipleChoice> mcqs;

    private boolean complete;
    private float grade;

    private StateChangeButton cutSceneButton;
    private Button submitBtn;

    public Assignment(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public boolean isComplete(){
        return complete;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // This code happens when you enter a game state for the *first time.*
        gc.setShowFPS(true);
        this.sbg= sbg;
        mcqs = new ArrayList<>();
        setAssignment("Test 1");
        cutSceneButton = new StateChangeButton((int)(Main.getScreenWidth()*.8f),(int)(Main.getScreenHeight()*.03f),
                Color.orange,"Go To Lunch", Main.CUTSCENE_ID,sbg);
        submitBtn = new Button((int)(Main.getScreenWidth()*.8f),(int)(Main.getScreenHeight()*.03f),75,
                50,Color.red,"Submit");
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        // This updates your game's logic every frame.  NO DRAWING.
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
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
        if (complete)
        {
            cutSceneButton.render(g);
        }
        else
        {
            submitBtn.render(g);
        }

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

    public void mousePressed(int button, int x, int y) {
        // This code happens every time the user presses the mouse
        if (complete)
        {
            cutSceneButton.click(x,y);
        }
        else {
            for (MultipleChoice m : mcqs)
            {
                m.click(x,y);
            }
            if (submitBtn.isMouseOver(x,y))
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
