package teacherTalk;

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

    public Conversation()
    {
        convo = "Teach: Mr. McVeigh\n" +
                "Obj: Ask for a test regrade\n" +
                "…\n" +
                "HT2: Here is Your quiz. You got a 60%\n" +
                "_OCA: Can I retake it?\n" +
                "HT2: I don’t think I’ll give a retake.\n" +
                "_OWA: Can we have the quiz regraded bc fire drill\n" +
                "__T2: Womp Womp.\n" +
                "_OWA: Could I retake this test\n" +
                "__T2: It’s NOT a test! It’s a quiz\n" +
                "_OCA: Could I retake this quiz\n" +
                "__T1: Sure!\n" +
                "HT1: I'll think about it\n" +
                "_OWA: The class Average was a 64%\n" +
                "__T2: Womp Womp.\n" +
                "_OWA: But I'm cool\n" +
                "__T1: no ur not\n" +
                "_OCA: pretty pretty please with a cherry on Top\n" +
                "__T1: fine\n" +
                "end";
        setDialogues();
        System.out.println("conversation created");
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
                    stages.add(new ConvoStage( getDialogue(convoStage), stages.size(), teachId));
                }
            }

        }

        for (ConvoStage c: stages){
            c.print();
        }
    }
    private ArrayList<Dialogue> getDialogue(ArrayList<String> stringDialogues)
    {
        ArrayList<Dialogue> convoDialogue = new ArrayList<>();
        for (String s: stringDialogues)
        {
            Dialogue d = new Dialogue(teachId, s);
            convoDialogue.add(d);
        }
        return convoDialogue;

    }

}
