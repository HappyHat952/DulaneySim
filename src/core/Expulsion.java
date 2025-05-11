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

public class Expulsion extends BasicGameState {
    private int id;
    private static StateBasedGame sbg;

    public Expulsion(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        gc.setShowFPS(true);
        Images.loadImages();
        Fonts.loadFonts();
        Expulsion.sbg = sbg;
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.black);
        g.drawImage(Images.expulsion,0,0);
        g.setFont(Fonts.big);

        if (Player.getGPA()< 1)
        {
            g.drawString("You are expelled for your GPA of "+Player.getGPA()+". Your parents kick you out",
                    Main.getScreenWidth()*.2f, Main.getScreenHeight()/2);
        }
        else if (Player.getCoolness()< -20)
        {
            g.drawString("You got expelled for being too lame. Your parents kick you out",
                    Main.getScreenWidth()*.2f, Main.getScreenHeight()/2);
        }


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
