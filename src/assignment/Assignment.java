package assignment;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment extends BasicGameState {
    private int id;
    private StateBasedGame sbg;

    private ArrayList<String> assignment;

    public Assignment(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // This code happens when you enter a game state for the *first time.*
        gc.setShowFPS(true);
        this.sbg= sbg;
        setAssignment("test1");
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        // This updates your game's logic every frame.  NO DRAWING.
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        // This code renders shapes and images every frame.
        g.setBackground(Color.orange);
        g.setColor(Color.white);
        g.drawRect(50,0,Main.getScreenWidth() - 100, Main.getScreenHeight() );
        g.setColor(Color.black);
        g.drawString("Assignment:", Main.getScreenWidth() * .5f, Main.getScreenHeight() * .1f);
        float y = Main.getScreenHeight()*.1f;
        for (String s: assignment)
        {
            y+= 20;
            g.drawString(s,Main.getScreenWidth()*.2f, y);
        }

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
    }

    public void setAssignment(String id)
    {
        assignment = new ArrayList<>();
        try
        {
            File f = new File("res/assignment/"+id+".txt");
            Scanner s = new Scanner(f);
            String str = s.nextLine();

            while (!str.equals("_"))
            {
                System.out.print(str);
                assignment.add(str);
                str = s.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("fine wasn't found");
        }

    }

}
