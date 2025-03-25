package assignment;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class MultipleChoice {
    //will contain all options + the question.
    ArrayList<String> mcq;
    int number;

    String header;
    ArrayList<String> options;
    char correct;

    int index;


    public MultipleChoice(ArrayList<String> mcq, int index)
    {
        this.mcq = mcq;
        System.out.println(mcq);
        this.index = index;
        number = index+1;

        options = new ArrayList<>();
        setInfo();
    }

    public void draw(Graphics g)
    {
        float y = Main.getScreenHeight()*.1f+Main.getScreenHeight()*.4f*index;
        float x = Main.getScreenWidth()*.2f;
        g.setColor(Color.black);
        g.drawString(number +". "+header, x, y);
        for (String s: options)
        {
            y+=30;
            if (s.charAt(0)== correct)
            {
                g.setColor(Color.green);
            }
            else
            {
                g.setColor(Color.black);
            }

            g.drawString(s,x,y);

        }
    }

    public void setInfo()
    {
        try{
            //number = Integer.parseInt(mcq.getFirst().replaceAll("[//D]", ""));
            header = mcq.getFirst().replaceAll("Qstn: ","" );

            for (int i = 1; i< mcq.size()-1; i++) // looks through the options
                // (everything but first line and last line)
            {
                options.add(mcq.get(i));
            }

            correct = mcq.getLast().charAt(0);
        } catch (NumberFormatException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }

    }
}
