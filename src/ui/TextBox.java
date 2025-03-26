package ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import java.util.ArrayList;

public class TextBox {
    protected int x;
    protected int y;

    protected int width;
    protected int height;

    protected String rawText;
    protected ArrayList<String> wrappedText;

    protected TrueTypeFont font;
    protected Color myColor;
    protected Color textColor;

    public int getHeight(){ return height;}


    public TextBox(String text, TrueTypeFont f, int x, int y, int w, int h)
    {
        rawText = text;
        font = f;
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        myColor = new Color(0f,0f,0f,.4f);
        textColor = Color.white;
    }

    public TextBox(String text, TrueTypeFont f, int x, int y, int w)
    {
        rawText = text;
        font = f;
        this.x = x;
        this.y = y;
        width = w;
        height = font.getWidth(text);
        textColor = Color.white;

        myColor = new Color(0f,0f,0f,.4f);
    }

    public void setBGColor(Color c)
    {
        myColor = c;

    }

    public void draw (Graphics g)
    {
        g.setColor(myColor);
        g.fillRect(x,y,width, height);

        g.setColor(textColor);
        g.setLineWidth(1);
        g.drawRect(x,y,width, height);

        g.setFont(font);
        if (wrappedText!= null)
        {
            for (String line: wrappedText)
            {
                g.drawString(line, x+ 2, y+ 2 + font.getHeight()*1.3f* wrappedText.indexOf(line));
            }
        }
        else {
            g.drawString (rawText, x + 10, y +10);
        }

    }


    public ArrayList<String> wrapText(String text)
    {
        //seperates all the words
        ArrayList<String> words = new ArrayList<>();
        String currWord = "";
        for (int i= 0; i< text.length(); i++) {
            if (text.charAt(i) != ' ' && text.charAt(i) != '\n') {
                currWord = currWord + text.charAt(i);
            }
            else {
                words.add(currWord);
                currWord = "";
            }
        }
        words.add(currWord);

        // puts the words in lists based on the length on the font.
        ArrayList<String> lines = new ArrayList<>();
        //String currLine = "";

        for (int wordI = 0; wordI< words.size(); wordI++)
        {
            if (lines.isEmpty() || font.getWidth(lines.getLast() + words.get(wordI)) >= width)
            {
                lines.add("");
                System.out.println(width);
            }
            if (!lines.isEmpty() && font.getWidth(lines.getLast() + words.get(wordI)) < width)
            {
                String line = lines.getLast();
                line = line +words.get(wordI)+ " ";
                lines.set(lines.size() -1 , line);
            }
        }

        return lines;
    }


}
