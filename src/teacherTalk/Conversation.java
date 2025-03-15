package teacherTalk;

import java.util.ArrayList;

public class Conversation {
    String convo;
    ArrayList<String> dialogues;

    public Conversation()
    {
        convo =  "Mr. Mcveigh\n" +
                "O: Ask for a test regrade\n" +
                "…\n" +
                "1T: Hello! Here is your quiz/ You got a 60%/ I don’t think I’ll give a retake.\n" +
                " 1.1-1WA: Can we have the quiz regraded bc fire drill\n" +
                " 1.1-2WT2: Womp Womp.\n" +
                " 1.2-1WA: Could I retake this test\n" +
                " 1.2-2WT2: It’s NOT a test! It’s a quiz\n" +
                " 1.3-1CA: Could I retake this quiz\n" +
                " 1.3-2CT1: Sure!\n" +
                "2T: I'll think about it\n" +
                " 2.1-1WA: The class Average was a 64%\n" +
                " 2.1-2WT2: Womp Womp.\n" +
                " 2.2-1WA: But I'm coool\n" +
                " 2.2-2WT1: no ur not\n" +
                " 2.3-1CA: pretty pretty please with a cherry on TOp\n" +
                " 2.3-2CT1: fine\n";
        setDialogues();
        System.out.println("conversation created");
    }
    private void setDialogues()
    {
        String talk  = convo;
        dialogues = new ArrayList<>();
        String unchecked = talk;
        while (unchecked.contains("\n"))
        {
            String d = unchecked.substring(0,unchecked.indexOf("\n"));
            dialogues.add(d);
            unchecked = unchecked.substring(unchecked.indexOf("\n")+1);
        }
    }
    private void setDialogue(String s)
    {
        Dialogue d = new Dialogue(s);
    }

}
