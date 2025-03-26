package teacherTalk;

import core.Main;
import core.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;
import setup.Images;
import ui.buttons.StateChangeButton;

import java.util.ArrayList;

public class Conversation {
    final static int STUDENT = -1;
    final static int MCVEIGH = 0;

    private int teachId;
    private String convo;
    private ArrayList<String> dialogues;
    private String teachName;
    private String objective;
    private ArrayList<ConvoStage> stages;

    private StateChangeButton assignmentButton;

    private int activeStage;
    private boolean active;
    private boolean complete;

    private int mistakes;

    private int imageID;

    public Conversation(StateBasedGame sbg)
    {
        convo = "Teach: Mr. McVeigh\n" +
                "Obj: Ask for a test regrade\n" +
                "…\n" +
                "HT1: Here is Your quiz. You got a 60%\n" +
                "_OCA: Can I retake it?\n" +
                "HT2: I don’t think I’ll give a retake.\n" +
                "_OWA: Can we have the quiz regraded bc fire drill\n" +
                "__T2: Womp Womp.\n" +
                "__T2: Maybe you should have done better :P/\n"+
                "__S: This guy is kind of weird...\n"+
                "_OWA: Could I retake this test?\n" +
                "__T3: It’s NOT a test! It’s a quiz\n" +
                "_OCA: Could I retake this quiz?\n" +
                "__T1: Sure!\n" +
                "HT1: I'll think about it\n" +
                "_OWA: The class Average was a 64%\n" +
                "__T2: Womp Womp.\n" +
                "_OWA: But I'm cool\n" +
                "__T1: No.\n" +
                "_OCA: pretty pretty please with a cherry on Top\n" +
                "__T1: fine\n" +
                "end";
        setDialogues();
        System.out.println("conversation created");
        activeStage = 0;
        imageID = 0;
        assignmentButton = new StateChangeButton((int)(Main.getScreenWidth()*.8f),(int)(Main.getScreenHeight()*.03f),
                Color.blue,"Start Quiz", Main.ASSIGN_ID,sbg);

    }
    private void setDialogues()
    {
        //this part puts the string as an arrayList of Strings.
        String talk  = convo;
        dialogues = new ArrayList<>();
        stages = new ArrayList<>();
        String unchecked = talk;
        while (unchecked.contains("\n"))
        {
            String d = unchecked.substring(0,unchecked.indexOf("\n"));
            dialogues.add(d);
            unchecked = unchecked.substring(unchecked.indexOf("\n")+1);
        }

        //organizes the arrayList of dialogue (teacher identifier, objective identified, and seperate each stage of the conversation)
        for (int i = 0; i<dialogues.size(); i++)
        {
            //identify the teacher and objective (woah)
            String s = dialogues.get(i);
            if (s.contains("Teach: "))
            {
                teachName = s.substring("Teach: ".length());
//                System.out.println("TEACHER's NAME: " +teachName);
                if (teachName.equals( "Mr. McVeigh"))
                {
                    teachId = MCVEIGH;
                }
            }
            else if (s.contains("Obj: "))
            {
                objective = s.substring("Obj: ".length());
//                System.out.println("OBJECTIVE: " +objective);
            }

            //determine each stage of the conversation
            else if (s.substring(0,1).equals("H"))
            {
                ArrayList<String> convoStage = new ArrayList<>();
                String stgDia = s;
                convoStage.add(s);
                //will keep searching through the data until the next character starts with an H or the end of list is reached
                while (s.substring(0,1).equals("H") && i<dialogues.size()-1 && dialogues.get(i+1).charAt(0) != 'H')
                {
                    i++;
                    stgDia = dialogues.get(i);
                    convoStage.add(stgDia);
                }
                if (!convoStage.isEmpty())
                {
                    stages.add(new ConvoStage( getDialogue(convoStage), stages.size(), teachId, this));
                }
            }

        }
    }

    public void setImage(int index)
    {
        imageID = index;
    }

    public void update()
    {
        if (active)
        {
            stages.get(activeStage).update();
        }
        if (!complete && stages.get(activeStage).isComplete())
        {
            // sums up the mistakes the person makes.
            if (activeStage+1<stages.size())
            {
                activeStage++;
            }
            else
            {
                active = false;
                complete = true;
                Player.adjustGPA(.5f);
            }


        }
        if (stages.get(activeStage).isComplete())
        {

        }

    }

    public void draw(Graphics g)
    {
        g.drawImage(Images.mcVBG,0,0);
        g.drawImage(Images.mcVImage.getSubImage(0,imageID), 0,20);
        g.drawString(""+mistakes, 40,40);
        if (active && !complete)
        {
            g.setColor(Color.black);
            g.setFont(Fonts.big);
            g.drawString("Objective: "+ objective, Main.getScreenWidth()*.31f, Main.getScreenHeight()*.01f);
            stages.get(activeStage).draw(g);
        }
        if (complete)
        {
            assignmentButton.render(g);
        }

    }

    public void addMistake(){ mistakes++;
    Player.adjustGPA(-.2f);}

    public void keyPressed(int key, char c)
    {
        if (active)
        {
            stages.get(activeStage).keyPressed(key,c);
        }
    }

    public void activate(){ active = true;}


    private ArrayList<Dialogue> getDialogue(ArrayList<String> stringDialogues)
    {
        ArrayList<Dialogue> convoDialogue = new ArrayList<>();
        for (String s: stringDialogues)
        {
            Dialogue d = new Dialogue(teachId, s);

            if (d.getStrId().charAt(0) == 'O') // identifies any option and creates
                // option class instead of dialogue
            {
                d = new Choice( teachId, s);
            }

            convoDialogue.add(d);
        }
        return convoDialogue;

    }

    public void mousePressed(int button, int x, int y)
    {
        if (complete)
        {
            assignmentButton.click(x,y);
        }

    }

}
