package teacherTalk;

public class Dialogue {

    // a single dialogue interaction
    String expression;
    int teacherID;
    int personID;
    String person;
    String text;
    String dialogue;

    int tier; //what level of the conversation -- determines what comes next.
    int readTime; // frames

    String id;

    public Dialogue(int teachID, String text)
    {
        this.teacherID = teachID;
        this.text = text;
        setInformation();
    }

    protected void setInformation()
    {
        dialogue = text.substring(text.indexOf(": ")+ 2);
        String unchoppedid = text.substring(0,text.indexOf(":"));

        id = "";
        for (int i = 0; i< unchoppedid.length(); i++)
        {
            if (unchoppedid.charAt(i)!='_')
            {
                id = id + unchoppedid.charAt(i);
            }
            else {
                tier++;
            }
        }

        if (id.contains("T"))
        {
            personID = teacherID;
            person = "Mr. McVeigh";
        }
        else
        {
            personID = Conversation.STUDENT;
            person = "You";
        }

        if (id.contains("T1"))
        {
            expression = ":)";
        }
        else if (id.contains("T2"))
        {
            expression = ":(";
        }
        else {
            expression = "---";
        }

        readTime = dialogue.length() * 10;

    }

    public void setTeacherID(int personId){ teacherID = personId;}

    public String getStrId(){ return id;}
    public String getDialogue(){ return dialogue;}
    public int getTeacherID(){ return teacherID;}
    public String getText(){ return text;}
    public int getTier(){ return tier;}
    public int getReadTime(){ return readTime;}

    @Override
    public String toString() {
        return tier +"||"+id+"|" +expression+"| " + person +": " +dialogue;
    }
}
