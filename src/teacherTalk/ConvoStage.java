package teacherTalk;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import ui.textBox.ChoiceTextBox;
import ui.textBox.TeacherTextBox;
import ui.textBox.TextBox;

import java.util.ArrayList;

public class ConvoStage {

    private int stageId;
    private int teachId;

    private ArrayList<Dialogue> allDialogues;

    private ArrayList<Choice> choices;        //each of the user- inputted possible options
    private Choice correctChoice;
    private Dialogue header;                    //the main teacher dialogue (that may repeat)

    private TeacherTextBox mainBox;
    private ArrayList<ChoiceTextBox> choiceBoxes;
    private ArrayList<TextBox> allBoxes;

    private boolean complete;
    private boolean active;                     //whether or not this stage is active (might not be needed)
    private int selected;                       //the selected option (starts at -1 and sets when user picks an option)
    private int finalSelect;
    private int currentDia;

    private int timer;
    private int wrongCount; // the count of incorrect choices;

    private Conversation convo;

    public ConvoStage(ArrayList<Dialogue> dialogues, int stageId, int teachId, Conversation c)
    {
        allDialogues = dialogues;
        choices = new ArrayList<>();
        setConvo(dialogues);
        setTextBoxes();
        selected = -1;
        finalSelect = -1;
        currentDia = -1;
        this.stageId = stageId;
        this.teachId = teachId;
        this.convo = c;

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
    public void setTextBoxes() {
        mainBox = new TeacherTextBox(header.toString());

        choiceBoxes = new ArrayList<>();
        allBoxes = new ArrayList<>();

        int choiceY = (int)( Main.getScreenHeight()*.6f) ;
        int choiceNum = 1;

        for (int i=0; i<allDialogues.size(); i++)
        {
            Dialogue d = allDialogues.get(i);
            TextBox tb;
            if (allDialogues.get(i) instanceof Choice)
            {
                Choice c = (Choice)(d);
                tb = new ChoiceTextBox("["+choiceNum+"]" +c.toString(), (int)(Main.getScreenWidth()*.56f),
                        choiceY);
                choiceNum ++;
                choiceY+=tb.getHeight() +15;
                choiceBoxes.add((ChoiceTextBox)tb);
                if (c.getCorrect())
                {
                    ((ChoiceTextBox)tb).setCorrectTrue();
                }
            }
            else
            {
                tb = new TeacherTextBox(d.toString());
            }
            allBoxes.add(tb);

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
            if (finalSelect == -1 && selected != -1)
            {
                finalSelect = selected;
                currentDia = allDialogues.indexOf(choices.get(finalSelect));// the current dialogue location
                // is in the larger list of choices
                if (!choices.get(finalSelect).equals(correctChoice))
                {
                    wrongCount++;
                    convo.addMistake();
                }
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
                if (currentDia!= -1)
                {
                    convo.setImage(allDialogues.get(currentDia).getImgID());
                }
                else {
                    convo.setImage(header.getImgID());
                }

            }
        }
    }


    public void draw (Graphics g)
    {
        g.setColor(Color.black);

        if (finalSelect == -1)
        {
            //g.drawString(header.toString(), 20, 270);
            mainBox.draw(g);
            for (int i = 0; i< choices.size(); i++) {
                Choice c = choices.get(i);
                if (c.getCorrect()) {
                    g.setColor(Color.green);
                    choiceBoxes.get(i).setBGColor(Color.green);

                }
                if (i == selected) {
                    if (selected == finalSelect) {
                        choiceBoxes.get(i).setBGColor(Color.cyan);
                    } else {
                        choiceBoxes.get(i).setBGColor(Color.blue);
                    }
                } else {
                    choiceBoxes.get(i).setBGColor(Color.black);
                }
                choiceBoxes.get(i).draw(g);
            }

        }
        else {
            allBoxes.get(currentDia).draw(g);
        }

    }



    public boolean isActive(){ return active;}
    public boolean isComplete(){ return complete;}
    public int getStageId() { return stageId;}
    public int getWrongCount(){ return wrongCount;}
    public void activate(){ active = true;}



    public void print() {
        for (Dialogue d: allDialogues)
        {
            System.out.println(d);
        }
    }
}