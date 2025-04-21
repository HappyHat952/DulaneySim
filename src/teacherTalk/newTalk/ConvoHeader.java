package teacherTalk.newTalk;

import core.Main;
import org.newdawn.slick.Color;
import setup.Fonts;
import ui.textBox.TextBox;

public class ConvoHeader extends TextBox {


    public ConvoHeader(String text) {
        super(text,Fonts.medium,(int)(Main.getScreenWidth()*.54f),(int)(Main.getScreenHeight()*.2f),
                (int)(Main.getScreenWidth()*.43f),(int)(Main.getScreenHeight()*.2f));
        myColor = Color.black;
        wrappedText =   wrapText(text);

    }

}
