//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;
import setup.Images;

public class Graduation extends BasicGameState {
    private int id;
    private static StateBasedGame sbg;

    public Graduation(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        gc.setShowFPS(true);
        Images.loadImages();
        Fonts.loadFonts();
        Graduation.sbg = sbg;
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.drawImage(Images.graduation,0,0);

        g.setFont(Fonts.big);
        g.drawString("You Graduate with a GPA of "+Player.getGPA(),
                Main.getScreenWidth()*.2f, Main.getScreenHeight()/2 - 140);
        String message;
        if (Player.getCoolness()< 10)
        {
            message = "You didn't make many friends...";
        }
        else if (Player.getCoolness()>70)
        {
            message = "YOU WERE THE COOLEST KID AT SCHOOL!";
        }
        else
        {
            message = "Not bad... you had a coolness of " + Player.getCoolness();
        }

        g.drawString("You Graduate with a GPA of "+Player.getGPA(),
                Main.getScreenWidth()*.2f, Main.getScreenHeight()/2 - 75);
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    public void leave(GameContainer gc, StateBasedGame sbg) {
    }

    public void keyPressed(int key, char c) {
    }

    public void mousePressed(int button, int x, int y) {
    }
}

