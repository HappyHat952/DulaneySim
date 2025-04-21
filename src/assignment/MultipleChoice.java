package assignment;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class MultipleChoice {
    //will contain all options + the question.
    //will contain all options + the question.
    ArrayList<String> mcq;
    int number;

    String header;
    ArrayList<String> options;

    ArrayList<Option> optionClick;

    boolean right;

    char correct;

    int index;
    boolean complete;

    Assignment ass;


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

    public void draw(Graphics g)
    {
        float y = Main.getScreenHeight()*.1f+Main.getScreenHeight()*.2f*index;
        float x = Main.getScreenWidth()*.2f;
        g.setColor(Color.black);
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

            float y = Main.getScreenHeight()*.1f+Main.getScreenHeight()*.2f*index;
            float x = Main.getScreenWidth()*.2f;

            correct = mcq.getLast().charAt(0);

            for (String o: options)
            {
                y+= 30;
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
        for (Option o: optionClick)
        {
            o.click(x,y);
        }
    }
}
