package assignment;

import core.Main;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class MultipleChoice {
    //will contain all options + the question.
    ArrayList<String> mcq;
    int number;

    String header;
    ArrayList<String> options;
    char correct;

    public MultipleChoice(ArrayList<String> mcq)
    {
        this.mcq = mcq;
        setInfo();
    }

    public void draw(Graphics g)
    {
        float y = Main.getScreenHeight()*.1f+20;
        float x = Main.getScreenWidth()*.5f;
        g.drawString(number +". "+header, x, y);
        for (String s: options)
        {
            y+=20;
            if (s.charAt(0)== correct)
            {
                g.drawString(s,x,y);
            }

        }
    }

    public void setInfo()
    {
        try{
            //number = Integer.parseInt(mcq.getFirst().replaceAll("[//D]", ""));
            String s = mcq.getFirst().substring(0,3).replaceAll("[\\D.]", "");
            number = Integer.parseInt(s);
            if (number/10 == 0)
            {
                header = mcq.getFirst().substring(3,mcq.getFirst().length());
            }
            options = mcq;
            correct = mcq.getLast().charAt(0);
//            options.remove(0);
//            options.remove(1);
        } catch (NumberFormatException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }

    }
}
