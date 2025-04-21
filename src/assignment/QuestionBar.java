package assignment;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import setup.Fonts;
import ui.buttons.Button;

import java.util.ArrayList;

public class QuestionBar {

    private Assignment assign;
    private Button[] buttons;
    public QuestionBar(Assignment assignment)
    {
        assign = assignment;
        buttons = new Button[assign.getMultipleChoice().size()];

        for (int i = 0; i< buttons.length; i++)
        {
            buttons[i] = new Button((int)(Main.getScreenWidth()*.4f + Main.getScreenWidth()*.05f*i)
                    , (int)(Main.getScreenHeight()*.75f),(int)(Main.getScreenWidth()*.046f),""+(i+1),
                    Fonts.big, Color.lightGray);
        }

        buttons[assign.getSelectedMCQ()].setColor(Color.blue);
    }

    public void render(Graphics g)
    {
        for (Button b: buttons)
        {
            b.render(g);
        }
    }

    public boolean mouseOver(int x, int y)
    {
        return (x>= buttons[0].getX()
                && x <= buttons[buttons.length -1].getX() + buttons[buttons.length -1].getWidth()
                && y>= buttons[0].getY() && y<= buttons[0].getY() + buttons[0].getHeight());
    }
    public void mouseClick(int button, int x, int y)
    {
        if (mouseOver(x,y))
        {
            for (int i= 0; i<buttons.length; i++)
            {
                Button b = buttons[i];
                if (b.isMouseOver(x,y))
                {
                    assign.setSelectedMCQ(i);
                    b.setColor(Color.blue);
                }
                else
                {
                    b.setColor(Color.lightGray);
                }
            }
        }

    }
}
