package assignment;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import setup.Fonts;
import setup.Sounds;
import ui.textBox.TextBox;

import static locker.Locker.volume;

public class Option extends TextBox {
    boolean selected;

    public Option(int x, int y, String text) {
        super (text, Fonts.medium, x,  y);
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
        if (volume) {
            Sounds.click.play();
        }
    }

    public void draw(Graphics g)
    {
        super.draw(g);
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
