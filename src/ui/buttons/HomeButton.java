package ui.buttons;

import core.Main;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

public class HomeButton extends Button {
    StateBasedGame sbg;
    public HomeButton(int x, int y, Image image, StateBasedGame sbg) {
        super(x,y,image,"Home");
    }

    public void action()
    {
        sbg.enterState(Main.TITLE_ID);
    }

}
