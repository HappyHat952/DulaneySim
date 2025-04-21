package ui.textBox;

import core.Main;
import org.newdawn.slick.Color;
import setup.Fonts;

public class ChoiceTextBox extends TextBox {
    boolean correct;

    public ChoiceTextBox(String text, int x, int y) {
        super(text, Fonts.medium, x, y, (int)(Main.getScreenWidth()*.35f), 70);
        correct = false;
        wrappedText = wrapText(text);
        height = wrappedText.size()*( font.getHeight() +20);
    }

    public void setCorrectTrue()
    {
        correct = true;
        myColor = new Color(.1f,.9f,.2f,.4f);
    }

    public boolean isCorrect()
    {
        return correct;
    }

}
