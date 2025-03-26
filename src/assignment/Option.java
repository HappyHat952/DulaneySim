package assignment;

import org.newdawn.slick.Color;
import setup.Fonts;
import ui.TextBox;
import ui.buttons.Button;

public class Option extends TextBox {
    boolean selected;

    public Option(int x, int y,int w, String text) {
        super (text, Fonts.small, x,  y,  w);
        selected = false;
        wrappedText = wrapText(text);
        height = wrappedText.size()*font.getHeight();
        myColor = Color.white;
        textColor = Color.black;

    }

    public String getText(){ return rawText;}
    public Boolean isSelected()
    {
        return selected;
    }

    public boolean isMouseOver( int x, int y)
    {
        return (x>this.x && y>this.y && x<this.x+width && y<this.y+height);
    }
    public void click(int x, int y)
    {
        if (isMouseOver(x,y))
        {
            action();
        }
    }

    public void action()
    {
        selected = !selected;
        if (selected)
        {
            myColor = Color.green;
        }
        else {
            myColor = Color.white;
        }

    }
}
