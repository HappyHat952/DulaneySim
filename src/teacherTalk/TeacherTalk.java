package teacherTalk;

import core.Main;
import core.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import setup.Images;

import java.util.ArrayList;

public class TeacherTalk extends BasicGameState {
    private int id;
    private String talk;
    private Conversation convo;

    public TeacherTalk(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // This code happens when you enter a game state for the *first time.*
        gc.setShowFPS(true);
        convo = new Conversation(sbg);
        convo.activate();
    }

    public void setConversation()
    {

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        // This updates your game's logic every frame.  NO DRAWING.
        convo.update();
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        // This code renders shapes and images every frame.
       g.setBackground(Color.darkGray);
        convo.draw(g);
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
        convo.keyPressed(key,c);
    }

    public void mousePressed(int button, int x, int y) {
        // This code happens every time the user presses the mouse
        convo.mousePressed(button,x,y);
    }
}
