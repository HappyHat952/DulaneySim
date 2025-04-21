package teacherTalk.newTalk;

import core.Main;
import org.newdawn.slick.Graphics;
import teacherTalk.Dialogue;

import java.util.ArrayList;

public class Node {

    protected ArrayList<String> script;

    protected ArrayList<Dialogue> listDialogue;

    protected String nodeID;

    protected String teachImgID;
    protected int teachImgIndex;
    protected String headerTxt;
    protected ConvoHeader header;
    protected ArrayList<String> choicesTxt;
    protected ArrayList<Choice> choices;

    protected int coolAdj;
    protected float gpaAdj;

    //MainTextBox text;
    //choice button arraylist of choices

    public Node(ArrayList<String> dialogues) {
        this.script = dialogues;
        choicesTxt = new ArrayList<>();
        setConvo();

        //checks the id is correct for nodes and options
//        System.out.println("id: " + nodeID + "header: "+ headerTxt);
//        System.out.println("options: ");
//        for (Choice c: choices) {
//            System.out.println(c);
//        }
    }

    //ACCESSORS
    public String getNodeID() {
        return nodeID;
    }

    public String getTeachImgID() {
        return teachImgID;
    }

    public int getTeachImgIndex() {
        return teachImgIndex;
    }

    public int getCoolAdj() {
        return coolAdj;
    }

    public float getGpaAdj() {
        return gpaAdj;
    }

    public String toString() {
        return nodeID + ": " + header;
    }


    //MUTATORS
    public void render(Graphics g) {
        header.draw(g);
        for (Choice c : choices) {
            c.draw(g);
        }
    }

    public void mousePressed(int button, int x, int y) {
        for (Choice c : choices) {
            c.click(x, y);
        }
    }

    protected void setResults() {

        for (int i = 1; i < script.size(); i++) {
            String s = script.get(i);
            String id = s.substring(0, s.indexOf(":"));
            s = s.substring(id.length() + 1);

            int co = 1; // the pos or negative coefficient.
            if (s.charAt(0) == '-') {
                co = -1;
            } else if (s.charAt(0) == '+') {
                co = 1;
            }
            s = s.substring(1);

            if (id.equalsIgnoreCase("cool")) {
                coolAdj = (int) (co * StringToFloat(s, 1));
            } else if (id.equalsIgnoreCase("GPA")) {
                gpaAdj = (co * StringToFloat(s, 0));
            }
        }
    }

    public float StringToFloat(String strNum, int power10) {
        if (strNum.isEmpty()) {
            return 0;
        }

        char c = strNum.charAt(0);
        if (c >= 48 && c <= 57) {
            int i = c - 48;
            return (float) (i * Math.pow(10, power10))
                    + StringToFloat(strNum.substring(1), power10 - 1);
        }
        return 0;
    }

    public void setConvo() {
        headerTxt = script.get(0);

        nodeID = headerTxt.substring(0, headerTxt.indexOf("-"));
        headerTxt = headerTxt.substring(nodeID.length() + 1);

        teachImgID = headerTxt.substring(0, headerTxt.indexOf(":"));
        headerTxt = headerTxt.substring(teachImgID.length() + 1);

        header = new ConvoHeader(headerTxt);

        if (teachImgID.charAt(0) < 10) {
            teachImgIndex = teachImgID.charAt(0);
        }

        choicesTxt = new ArrayList<>();
        choices = new ArrayList<>();

        float yVal = Main.getScreenHeight() * .6f;

        for (int i = 1; i < script.size(); i++) {
            String ch = script.get(i); // the string choice
            choicesTxt.add(ch);
            String id = ch.substring(0, ch.indexOf(":"));
            ch = ch.substring(id.length() + 1);

            choices.add(new Choice(ch, id, (int) (yVal)));
            yVal += choices.get(i - 1).getHeight() + 15;
        }

    }
}
