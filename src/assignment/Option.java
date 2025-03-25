package assignment;

import org.newdawn.slick.Color;
import setup.Fonts;
import ui.TextBox;
import ui.buttons.Button;

public class Option extends TextBox {
    boolean selected;

    public Option(int x, int y,int w, String text) {
        super (text, Fonts.medium, x,  y,  w);
        selected = false;
    }

    public void action()
    {
        selected = !selected;
    }
}
