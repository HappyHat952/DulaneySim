package ui.buttons;

import core.Main;
import org.newdawn.slick.*;
import setup.Fonts;
import org.lwjgl.input.Mouse;
import setup.Sounds;

import static locker.Locker.volume;

public class Button {
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected Color color;
    protected Image image;
    protected  String name;
    protected String info;
    protected TrueTypeFont font;

    public Button (int x, int y, int w, String s, TrueTypeFont f, Color c)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        h = f.getHeight();
        name = s;
        font = f;
        color = c;
    }
    public Button (int x, int y, String s, TrueTypeFont f, Color c)
    {
        this.x = x;
        this.y = y;
        name = s;
        font = f;
        color = c;
        w = f.getWidth(s);
        h = f.getHeight();
    }

    public Button(int x, int y, int w, int h, Color color) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        font = Fonts.small;
    }

    public Button(int x, int y, int w, int h, Color color, String name) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        this.name = name;
        font = Fonts.small;
    }

    public Button(int x, int y, int w, int h, Color color, String name, String info) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        this.name = name;
        this.info = info;
        font = Fonts.small;
    }

    public Button(int x, int y, Image image, String name) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.name = name;
        w = image.getWidth();
        h = image.getHeight();
        font = Fonts.small;
    }

    public Button(int x, int y) {
        this.x = x;
        this.y = y;
        font = Fonts.small;
    }

    public void setColor(Color c){ color = c;}
    public void render(Graphics g) {
        if (image == null)
        {
            g.setColor(color);
            g.fillRect(x, y, w, h);

        }
        else {
            g.drawImage(image, x,y);
        }
        if (isMouseOver(Mouse.getX(), Main.getScreenHeight() -Mouse.getY()))
        {
            if (image == null)
            {
                g.setColor(new Color(1f,1f,1f,.8f));
                g.fillRect(x,y,w,h);
            }
            else {
                image.draw(x, y);
                g.setColor(new Color(1f, 1f, 1f, 0.5f));
                g.fillRect(x, y, w, h);
            }
            if (name != null) {
                g.setFont(font);
                g.setColor(Color.black);
                g.drawString(name, x + w / 2f - font.getWidth(name) / 2f, y + h / 2f- font.getHeight() / 2f);
            }
            if (info != null)
            {
//                g.setFont(font);
//                g.setColor(Color.black);
//                g.drawString(info, x + w /2f - font.getWidth(info) /2f , y + h *.6f);
            }

        }else if (name != null){
            g.setFont(font);
            g.setColor(Color.white);
            g.drawString(name, x+w/2f - font.getWidth(name)/2f, y+ h/2f- font.getHeight() / 2f);
        }

    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        if (mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h) {
            return true;
        }
        return false;
    }

    public void click(int mouseX, int mouseY)
    {
        if (isMouseOver(mouseX,mouseY))
        {
            action();
            if (volume) {
                Sounds.click.play();
            }
        }
    }

    public void action(){

    }

    public int getX(){ return x;}
    public int getY(){ return y;}
    public int getWidth(){ return w;}
    public int getHeight(){ return h;}
    public String getName(){ return name;}

}