package teacherTalk;

public class Dialogue {

    // a single dialogue interaction
    String expression;
    int person;
    String text;
    String dialogue;
    Dialogue nextDialogue;

    String id;

//    public Dialogue(String id, String dialogue, String expression, int person)
//    {
//        this.id = id;
//        this.dialogue = dialogue;
//        this.expression = expression;
//        this.person = person;
//
//    }

    public Dialogue(int person, String text)
    {
        this.person = person;
        this.text = text;
        setInformation();
    }

    private void setInformation()
    {
        dialogue = text.substring(text.indexOf(": ")+ 2);
        String unchoppedid = text.substring(0,text.indexOf(":"));

        id = "";
        for (int i = 0; i< unchoppedid.length(); i++)
        {
            if (unchoppedid.charAt(i)!='_')
            {
                id = id + unchoppedid.substring(i,i+1);
            }
        }

        if (id.contains("T1"))
        {
            expression = ":)";
        }
        else if (id.contains("T2"))
        {
            expression = ":(";
        }

    }

    public void setPerson(int personId){ person = personId;}

    public String getStrId(){ return id;}
    public String getDialogue(){ return dialogue;}
    public int getPerson(){ return person;}

    @Override
    public String toString() {
        return expression+"| " +person +": " +dialogue;
    }
}
