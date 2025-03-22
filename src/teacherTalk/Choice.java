package teacherTalk;

public class Choice extends Dialogue{

    boolean correct;

    public Choice(int personId, String text)
    {
        super(personId, text);
    }

    public void setInformation()
    {
        super.setInformation();
        if (id.contains("OC"))
        {
            correct = true;
        }
        else
        {
            correct = false;
        }
    }

    public boolean getCorrect(){ return correct;}
}
