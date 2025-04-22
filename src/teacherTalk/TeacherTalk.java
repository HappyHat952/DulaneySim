package teacherTalk;

import core.Main;
import core.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import setup.Images;
import teacherTalk.newTalk.NewConversation;

import java.util.ArrayList;

import static locker.Locker.renderVolume;
import static locker.Locker.updateVolume;

public class TeacherTalk extends BasicGameState {
    private int id;
    private static StateBasedGame sbg;
    private String talk;
    private Conversation convo;
    private static NewConversation convo2;

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
        convo2 = new NewConversation(sbg, "mcVeigh", Images.mcVBG, Images.mcVImage);
        this.sbg = sbg;
    }

    public static void setConversation(String convoID, Image bg, SpriteSheet teach)
    {
        convo2 = new NewConversation(sbg, convoID, bg, teach);
    }

    public static void setConvoNode(String id)
    {
        convo2.setNode(id);
    }
    public static void endConvo()
    {
        convo2.end();
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        // This updates your game's logic every frame.  NO DRAWING.
        convo.update();
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        // This code renders shapes and images every frame.
       g.setBackground(Color.darkGray);
     //  convo.draw(g);
        convo2.render(g);
        Player.render(g);
        renderVolume(g);

    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // This code happens when you enter a gameState.
    }

    public void leave(GameContainer gc, StateBasedGame sbg) {
        // This code happens when you leave a gameState.
    }

    public void keyPressed(int key, char c) {
        // This code happens every time the user presses a key
    //    convo.keyPressed(key,c);
    }

    public void mousePressed(int button, int x, int y) {
        // This code happens every time the user presses the mouse
//        convo.mousePressed(button,x,y);
        updateVolume(x, y);
     //   convo.mousePressed(button,x,y);
        convo2.mousePressed(button,x,y);
    }
}
