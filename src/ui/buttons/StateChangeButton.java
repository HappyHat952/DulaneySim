package ui.buttons;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;

public class StateChangeButton extends Button {
    StateBasedGame sbg;
    int destination;
    public StateChangeButton(int x, int y, Image image, StateBasedGame sbg, String name, int destination) {

        super(x,y,image,name);
        this.sbg = sbg;
        this.destination = destination;
    }

    public StateChangeButton(int x, int y, Color c, String name, int destination ,StateBasedGame sbg) {

        super(x,y,name, Fonts.medium,c);
        w = font.getWidth(name) + 40;
        h = font.getHeight(name) + 40;
        this.sbg = sbg;
        this.destination = destination;
    }
    public void action()
    {
        sbg.enterState(destination);
        System.out.println("leaving");
    }

}
