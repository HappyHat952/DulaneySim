package teacherTalk.newTalk;

import core.Main;
import org.newdawn.slick.Color;
import setup.Fonts;
import teacherTalk.TeacherTalk;
import ui.textBox.TextBox;

public class Choice extends TextBox {

    private String nextNodeID;

    public Choice(String text, String nodeID, int y) {
        super(text, Fonts.medium, (int)(Main.getScreenWidth()*.56f)
                , y, (int)(Main.getScreenWidth()*.35f), 70);
        wrappedText = wrapText(text);
        this.nextNodeID = nodeID;

        height = wrappedText.size()*( font.getHeight() +20);
        myColor = Color.blue;
    }

    @Override
    public String toString() {
        return        ( "id: " +nextNodeID +"---" + rawText) ;
    }

    public boolean mouseOver(int mX, int mY)
    {
        return (mX> x && mX< x+width && mY>y && mY<y+height);
    }

    public void setY( int newY)
    {
        y = newY;
    }

    public void click(int mX, int mY)
    {
        if (mouseOver(mX, mY))
        {
            //actions (move to the next stage -- call conversation)
            TeacherTalk.setConvoNode(nextNodeID);
        }
    }


}
