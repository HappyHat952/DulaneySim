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

public class AssignState extends BasicGameState {
    private int id;
    private StateBasedGame sbg;

    private StateChangeButton cutSceneButton;
    private Button submitBtn;
    private Assignment assignment;
    private ArrayList<Assignment> allAssigned;

    public AssignState(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void setAssignment(String name){
        assignment = new Assignment(name);
        allAssigned.add(assignment);
    }


    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // This code happens when you enter a game state for the *first time.*
        gc.setShowFPS(true);
        this.sbg= sbg;
        allAssigned = new ArrayList<>();
        assignment = new Assignment("Test 1");
        allAssigned.add(assignment);
        cutSceneButton = new StateChangeButton((int)(Main.getScreenWidth()*.8f),(int)(Main.getScreenHeight()*.03f),
                Color.orange,"Go To Lunch", Main.CUTSCENE_ID,sbg);
        submitBtn = new Button((int)(Main.getScreenWidth()*.8f),(int)(Main.getScreenHeight()*.03f),75,
                50,Color.red,"Submit");
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        // This updates your game's logic every frame.  NO DRAWING.
        assignment.update();
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        // This code renders shapes and images every frame.

        g.setBackground(Color.orange);
        g.setColor(Color.white);
        g.fillRect(200,0,Main.getScreenWidth() - 400, Main.getScreenHeight() );


        g.setColor(Color.black);
        g.setFont(Fonts.small);

        assignment.render(g);
        if (assignment.isComplete())
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
        if (assignment.isComplete())
        {
            cutSceneButton.click(x,y);
        }
        else {
            if (submitBtn.isMouseOver(x,y))
            {
                assignment.submit();
            }
            else
            {
                assignment.mousePressed(button, x,y);
            }
        }



    }

}
