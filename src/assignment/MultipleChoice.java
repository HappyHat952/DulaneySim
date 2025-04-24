package assignment;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import setup.Fonts;
import ui.buttons.Button;

import java.util.ArrayList;

public class MultipleChoice {
    //will contain all options + the question.
    //will contain all options + the question.
    ArrayList<String> mcq;
    private int number;

    private String header;
    private ArrayList<String> options;

    private ArrayList<Option> optionClick;

    private boolean right;

    private char correct;

    private int index;

    private Assignment ass;
    private Option selected;


    public MultipleChoice(ArrayList<String> mcq, int index, Assignment a)
    {
        this.mcq = mcq;
        System.out.println(mcq);
        this.index = index;
        number = index+1;
        options = new ArrayList<>();
        setInfo();
        ass = a;
    }

    public boolean isRight(){ return right;}
    public boolean isAttempted(){ return selected != null;}

    public void draw(Graphics g)
    {
        float y = Main.getScreenHeight()*.3f;
        float x = Main.getScreenWidth()*.2f;
        g.setColor(Color.black);
        g.setFont(Fonts.medium);
        g.drawString(number +". "+header, x, y);

        for (Option s: optionClick)
        {
            s.draw(g);
        }
        if (!right && ass.isComplete())
        {
            g.setColor(Color.red);
            g.setLineWidth(5);
            g.drawLine(x,y,x+50,y+50);
            g.drawLine(x+50, y, x, y+50);
        }
    }

    public void setInfo()
    {
        try{
            //number = Integer.parseInt(mcq.getFirst().replaceAll("[//D]", ""));
            header = mcq.getFirst().replaceAll("Qstn: ","" );
            optionClick = new ArrayList<>();

            for (int i = 1; i< mcq.size()-1; i++) // looks through the options
                // (everything but first line and last line)
            {

                options.add(mcq.get(i));
            }

            float y = Main.getScreenHeight()*.3f;
            float x = Main.getScreenWidth()*.2f;

            correct = mcq.getLast().charAt(0);

            for (String o: options)
            {
                y+= Fonts.medium.getHeight()+10;
                optionClick.add(new Option((int)(x),(int)(y), (int)(Main.getScreenWidth()*.2f), o) );
            }

        } catch (NumberFormatException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }

    }

    public boolean grade()
    {
        for (Option o: optionClick)
        {
            if (o.isSelected() && o.getText().charAt(0)== correct)
            {
                right = true;
            }
            else if (o.isSelected())
            {
                right = false;
            }
        }
        return right;
    }

    public void click(int x, int y)
    {
        if (selected== null)
        {
            for (Option o: optionClick)
            {
                o.click(x,y);
                if (o.isMouseOver(x,y))
                {
                    selected = o;
                }
            }
        }
        else {
            selected.click(x,y);
            if (selected.isMouseOver(x,y))
            {
                selected = null;
            }
        }

    }
}
