package teacherTalk.newTalk;

import core.Main;
import org.newdawn.slick.Graphics;
import teacherTalk.TeacherTalk;

import java.util.ArrayList;


public class EndNode extends Node{

    public EndNode(ArrayList<String> dialogues) {
        super(dialogues);
        setResults();
        choices = new ArrayList<>();
        choices.add(new Choice("ok.", nodeID,
                (int)(Main.getScreenHeight()*.6f)));

    }



    public String toString(){
        return "END: "+nodeID +"|| GPADJ: "+gpaAdj+"|| COOLADJ: "+coolAdj;
    }


    public void mousePressed(int button,int x, int y)
    {
        super.mousePressed(button,x,y);
        if (choices.getFirst().mouseOver(x,y))
        {
            TeacherTalk.endConvo();
        }
    }


}


