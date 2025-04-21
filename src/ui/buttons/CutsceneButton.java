package ui.buttons;

import core.CutState;
import core.Main;
import core.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;

public class CutsceneButton extends Button {


    public CutsceneButton(int x, int y, int w, int h, Color color, String name, String info) {
        super(x, y, w, h, color, name, info);

    }
    public void action(StateBasedGame sbg)
    {
        System.out.println("Changing choice and frame");
        ((CutState) (sbg.getCurrentState())).setChoice(info);
        ((CutState) (sbg.getCurrentState())).nextFrame();
    }

    public void render(Graphics g) {
        super.render(g);

    }
}
