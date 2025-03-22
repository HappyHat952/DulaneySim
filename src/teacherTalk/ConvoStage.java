package teacherTalk;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import javax.swing.*;
import java.util.ArrayList;

public class ConvoStage {

    private int stageId;
    private int teachId;

    private ArrayList<Dialogue> allDialogues;

    private ArrayList<Choice> choices;        //each of the user- inputted possible options
    private Choice correctChoice;
    private Dialogue header;                    //the main teacher dialogue (that may repeat)

    private boolean complete;
    private boolean active;                     //whether or not this stage is active (might not be needed)
    private int selected;                       //the selected option (starts at -1 and sets when user picks an option)
    private int finalSelect;
    private int currentDia;

    private int timer;
    private int wrongCount; // the count of incorrect choices;

    //    public ConvoStage(ArrayList<String> dialogues, int id)
//    {
//        convoStage = dialogues;
//        //setConvo(dialogues);
//        selected = -1;
//        this.id = id;
//    }
    public ConvoStage(ArrayList<Dialogue> dialogues, int stageId, int teachId)
    {
        allDialogues = dialogues;
        choices = new ArrayList<>();
        setConvo(dialogues);
        selected = -1;
        finalSelect = -1;
        currentDia = -1;
        this.stageId = stageId;
        this.teachId = teachId;

    }


    public void setConvo(ArrayList<Dialogue> convo)
    {
        for (int i = 0; i< allDialogues.size(); i++) {
            Dialogue d = allDialogues.get(i);
            if (d.getStrId().charAt(0) == 'H') {
                header = d;
                header.setTeacherID(teachId);
            } else if (d instanceof Choice) {
                // creates a copy of the dialogue that is an option

                if (((Choice)d).getCorrect()) {
                    correctChoice = (Choice)d;
                }
                choices.add((Choice)d);
                d.setTeacherID(Conversation.STUDENT);
            } else {
                d.setTeacherID(teachId);
            }
        }
    }

    public void keyPressed(int key, char c)
    {
        if (  1< key && key <= choices.size()+1 && finalSelect == -1)
        {

            int num = key-2; // selects the correct index option.
            if (num == selected)
            {
                selected = -1; // deselects an item
            }
            else {
                selected = num;
            }
        }
        if (key == Input.KEY_ENTER)
        {
            if (finalSelect == -1)
            {
                finalSelect = selected;
                currentDia = allDialogues.indexOf(choices.get(finalSelect));// the current dialogue location
                // is in the larger list of choices
            }

        }
    }


    public void update()
    {
        if (finalSelect!= -1)
        {
            timer ++;
            if (timer == allDialogues.get(currentDia).getReadTime())
            {
                timer = 0;
                //checks if the next dialogue exists AND if its in the same tier. If the next one is a same
                // or bigger tier, make that the new current dialogue.
                if (currentDia +1< allDialogues.size()
                        && allDialogues.get(currentDia+1).getTier()>= allDialogues.get(currentDia).getTier())
                {
                    currentDia +=1;
                }
                // if you've reached the end, and the choice was right
                else if (choices.get(finalSelect).getCorrect())
                {
                    active = false;
                    complete = true;
                }
                else
                {
                    //resets all data, but adds to incorrect count;
                    selected = -1;
                    finalSelect = -1;
                    currentDia = -1;

                    wrongCount++;
                }
            }
        }
    }


    public void draw (Graphics g)
    {
        g.setColor(Color.black);

        if (finalSelect == -1)
        {
            g.drawString(header.toString(), 20, 270);
            for (int i = 0; i< choices.size(); i++) {
                Choice c = choices.get(i);
                if (c.getCorrect()) {
                    g.setColor(Color.green);
                } else if (i == selected) {
                    if (selected == finalSelect) {
                        g.setColor(Color.cyan);
                    } else {
                        g.setColor(Color.blue);
                    }
                    g.fillRect(20, 300 * stageId + 30 * i, 50, 20);
                } else {
                    g.setColor(Color.black);
                }
                g.drawString(c.toString(), 20, 300 + 30 * i);
            }

        }
        else {
            g.drawString(allDialogues.get(currentDia).toString()+"|| " + timer +"/ "+allDialogues.get(currentDia).getReadTime(), 20, 300);
        }


        g.drawString(""+selected,0,0);
    }



    public boolean isActive(){ return active;}
    public boolean isComplete(){ return complete;}
    public int getStageId() { return stageId;}
    public void activate(){ active = true;}



    public void print() {
        for (Dialogue d: allDialogues)
        {
            System.out.println(d);
        }
    }
}