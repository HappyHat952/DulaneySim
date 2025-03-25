package ui.buttons;

import core.Main;
import setup.Fonts;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Button {
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected Color color;
    protected Image image;
    protected  String name;
    protected String info;

    public Button(int x, int y, int w, int h, Color color) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }

    public Button(int x, int y, int w, int h, Color color, String name) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        this.name = name;
    }

    public Button(int x, int y, int w, int h, Color color, String name, String info) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        this.name = name;
        this.info = info;
    }

    public Button(int x, int y, Image image, String name) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.name = name;
        w = image.getWidth();
        h = image.getHeight();
    }

    public Button(int x, int y) {
        this.x = x;
        this.y = y;
    }

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
                image.drawFlash(x,y);
            }
            if (name != null) {
                g.setFont(Fonts.small);
                g.setColor(Color.black);
                g.drawString(name, x + w / 2f - Fonts.small.getWidth(name) / 2f, y + h / 2f);
            }
            if (info != null)
            {
                g.setFont(Fonts.small);
                g.setColor(Color.black);
                g.drawString(info, x + w /2f - Fonts.small.getWidth(info) /2f , y + h *.6f);
            }

        }else if (name != null){
            g.setFont(Fonts.small);
            g.setColor(Color.white);
            g.drawString(name, x+w/2f - Fonts.small.getWidth(name)/2f, y+ h/2f);
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
        }
    }

    public void action(){}

    public int getX(){ return x;}
    public int getY(){ return y;}
    public int getWidth(){ return w;}
    public int getHeight(){ return h;}
    public String getName(){ return name;}
}