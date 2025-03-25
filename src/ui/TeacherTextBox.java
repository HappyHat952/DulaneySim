package ui;

import core.Main;
import setup.Fonts;

public class TeacherTextBox extends TextBox{
    public TeacherTextBox(String text)
    {
        super(text, Fonts.medium, (int)(Main.getScreenWidth()*.54f), (int)(Main.getScreenHeight()*.2f),
                (int)(Main.getScreenWidth()*.43f), (int)(Main.getScreenHeight()*.2f));

        wrappedText = wrapText(text);
    }
}
