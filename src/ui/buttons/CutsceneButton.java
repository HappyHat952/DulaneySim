package ui.buttons;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;

public class CutsceneButton extends Button {

    boolean showText;
    public CutsceneButton(int x, int y, int w, int h, Color color, String name, String info) {
        super(x, y, w, h, color, name, info);
        showText = false;
    }
    public void action()
    {
        showText = true;
    }

    public void render(Graphics g) {
        super.render(g);

    }
}
