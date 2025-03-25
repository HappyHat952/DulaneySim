package ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class TextBox {
    protected int x;
    protected int y;

    protected int width;
    protected int height;

    protected String rawText;
    protected String wrappedText;

    protected TrueTypeFont font;
    protected Color myColor;


    public TextBox(String text, TrueTypeFont f, int x, int y, int w, int h)
    {
        rawText = text;
        font = f;
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        myColor = new Color(0f,0f,0f,.4f);
    }

    public void setBGColor(Color c)
    {
        myColor = c;
    }

    public void draw (Graphics g)
    {
        g.setColor(myColor);
        g.fillRect(x,y,width, height);

        g.setColor(Color.white);
        g.setLineWidth(4);
        g.drawRect(x,y,width, height);

        g.setFont(font);
        g.drawString (rawText, x + 10, y +10);
    }


    public String wrapText(String s)
    {
        return s;
    }


}
