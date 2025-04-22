package assignment;

import core.Main;
import core.Player;
import org.newdawn.slick.*;
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
    private int selectedMCQ;
    private StateBasedGame sbg;

    private  ArrayList<String> assignment;
    private  ArrayList<MultipleChoice> mcqs;

    private  QuestionBar qSelector;

    private  boolean complete;
    private  float grade;


    public Assignment(String testID) {
        this.id = id;
        mcqs = new ArrayList<>();
        setAssignment(testID);
        selectedMCQ = 0;
        qSelector = new QuestionBar(this);

    }

    public boolean isComplete(){ return complete;}
    public ArrayList<MultipleChoice> getMultipleChoice(){ return mcqs;}
    public int getSelectedMCQ(){ return selectedMCQ;}


    public void setSelectedMCQ(int index){ selectedMCQ = index;}
    public void update() throws SlickException {
        // This updates your game's logic every frame.  NO DRAWING.
    }

    public void render(Graphics g) throws SlickException {
        // This code renders shapes and images every frame.

//        g.setBackground(Color.orange);
//        g.setColor(Color.white);
//        g.fillRect(0,200,Main.getScreenWidth() - 400, Main.getScreenHeight() );

        g.setFont(Fonts.big);
        g.setColor(Color.red);
        if (complete)
        {
            String grde = (""+grade*100);
            int maxLen = 5;
            if (grde.length() >= maxLen)
            {
                grde = grde.substring(0,maxLen);
            }
            g.drawString((grde + "%"),Main.getScreenWidth()*.5f, Main.getScreenHeight()*.2f);
        }

        g.setColor(Color.black);
        g.setFont(Fonts.small);

        if (mcqs!= null && !mcqs.isEmpty())
        {
//            for (MultipleChoice mcq: mcqs)
//            {
//                mcq.draw(g);
//            }
            mcqs.get(selectedMCQ).draw(g);
        }

        qSelector.render(g);
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
        if (Input.KEY_1<= key && Input.KEY_9>= key)
        {
            if (key -2 < mcqs.size())
            {
                selectedMCQ = key - 2;
            }
        }
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
        }
        grade = points*1f/mcqs.size();
        Player.assignmentGPAAdj(grade);
        complete = true;
    }

    public void mousePressed(int button, int x, int y) {
        // This code happens every time the user presses the mouse

        if (!complete) {

//                for (MultipleChoice m : mcqs)
//                {
//                    m.click(x,y);
//                }
            mcqs.get(selectedMCQ).click(x,y);

        }
        qSelector.mouseClick(button,x,y);



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

                str = s.nextLine();
            }
            System.out.println("multiple Choice created."+ mcqs.size());
        } catch (FileNotFoundException e) {
            System.out.println("file wasn't found");
        }

    }
}
