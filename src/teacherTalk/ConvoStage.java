package teacherTalk;

import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class ConvoStage {

    private int stageId;
    private int teachId;

    private ArrayList<Dialogue> convoStage;

    private ArrayList<Dialogue> options;        //each of the user- inputted possible options
    private Dialogue header;                    //the main teacher dialogue (that may repeat)
    private boolean active;                     //whether or not this stage is active (might not be needed)
    private int selected;                       //the selected option (starts at -1 and sets when user picks an option)

    //    public ConvoStage(ArrayList<String> dialogues, int id)
//    {
//        convoStage = dialogues;
//        //setConvo(dialogues);
//        selected = -1;
//        this.id = id;
//    }
    public ConvoStage(ArrayList<Dialogue> dialogues, int stageId, int teachId)
    {
        convoStage = dialogues;
        options = new ArrayList<>();
        setConvo(dialogues);
        selected = -1;
        this.stageId = stageId;
        this.teachId = teachId;

    }


    public void setConvo(ArrayList<Dialogue> convo)
    {
        for (Dialogue d: convo)
        {
            if (d.getStrId().charAt(0)== 'H')
            {
                header = d;
                header.setPerson(teachId);
            }
            else if (d.getStrId().charAt(0) =='O')
            {
                options.add(d);
                d.setPerson(Conversation.STUDENT);
            }
            else
            {
                d.setPerson(teachId);
            }

        }
    }



    public void draw (Graphics g)
    {
        int i = 0;
        for (Dialogue d: convoStage)
        {
            i++;
            g.drawString(d.toString(), 20, 300* stageId +30*i);
        }
    }
    public void print ()
    {
        System.out.println(stageId);

        for (Dialogue d: convoStage)
        {
            System.out.println(d.toString());
        }
    }

    public boolean getActivate(){ return active;}
    public int getStageId() { return stageId;}

    public void activate(){ active = true;}



}