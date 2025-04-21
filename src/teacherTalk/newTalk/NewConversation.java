package teacherTalk.newTalk;

import core.Main;
import core.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;
import setup.Fonts;
import setup.Images;
import ui.buttons.StateChangeButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NewConversation {
    final static int STUDENT = -1;
    final static int MCVEIGH = 0;

    private int teachId;
    private String convo;
    private ArrayList<String> dialogues;
    private ArrayList<String> allLines;
    private ArrayList<ArrayList<String>> nodesTxt; // the list of lines seperated into nodes
    private ArrayList<Node> nodes;
    private Node currNode;
    private String teachName;
    private String objective;
  //  private ArrayList<teacherTalk.ConvoStage> nodes;

    private StateChangeButton assignmentButton;

    private int activeStage;
    private boolean active;
    private boolean complete;

    private int mistakes;

    private int imageID;
    private static SpriteSheet teacherSheet;
    private static int teacherIndex;

    public NewConversation(StateBasedGame sbg)
    {
        readFile("mcVeigh");
        System.out.println(nodesTxt.size());
        teacherSheet = Images.mcVImage;

        nodes = new ArrayList<>();
        complete = false;

        for (int i = 0; i< nodesTxt.size(); i++)
        {
            Node n = new Node(nodesTxt.get(i));
            if(n.getNodeID().contains("BE") ||n.getNodeID().contains("GE"))
            {
                n = new EndNode(nodesTxt.get(i));
            }
            nodes.add(n);
            System.out.println(n);
        }

        currNode = nodes.get(0);

        assignmentButton = new StateChangeButton((int)(Main.getScreenWidth()*.8f),(int)(Main.getScreenHeight()*.03f),
                Color.blue,"Start Quiz", Main.ASSIGN_ID,sbg);

    }

    public void render(Graphics g)
    {
        g.drawImage(Images.mcVBG,0,0);
        g.drawImage(Images.mcVImage.getSubImage(0,imageID), 0,20);
        if (!complete)
        {
            g.setColor(Color.black);
            g.setFont(Fonts.big);
            String text = "Objective: "+ objective;
            g.drawString(text, Main.getScreenWidth()*.98f - Fonts.big.getWidth(text),
                    Main.getScreenHeight()*.01f);
           currNode.render(g);
        }
        if (complete)
        {
            assignmentButton.render(g);
        }
    }

    public void mousePressed(int button,int x, int y)
    {
        currNode.mousePressed(button,x,y);
        if (complete)
        {
            assignmentButton.click(x,y);
        }
    }

    public void setNode(String nodeID)
    {
        for (Node n: nodes)
        {
            //only switches if its different.
            if (n.getNodeID().equals( nodeID) && !currNode.getNodeID().equals(nodeID))
            {
                currNode = n;
            }
        }
    }

    public void end()
    {
        complete = true;
        Player.adjustGPA(((EndNode)(currNode)).getGpaAdj());
        Player.addCoolness(((EndNode)(currNode)).getCoolAdj());
    }

    public static void changeTeacher()
    {
        //add later
    }

    public static void changeImage(int id)
    {
        if (id>= 0 && id<teacherSheet.getVerticalCount())
        {
            teacherIndex = id;
        }
    }

    public void readFile(String id)
    {
        allLines = new ArrayList<>();
        //id = id.replace(" ", "_");
        try {
            File f = new File("res/convo/" + id);
            Scanner s = new Scanner(f);
            String str = s.nextLine();
            // ArrayList<String> arrayList = new ArrayList<>();

            nodesTxt = new ArrayList<>();
            ArrayList<String> currNode = new ArrayList<>();

            while (!str.equals("end")) {
                if (str.startsWith("O: "))
                {
                    objective = str.substring(3);
                }
                if (str.charAt(0)== '+')
                {
                    if (!currNode.isEmpty())
                    {
                        nodesTxt.add(currNode);
                    }
                    currNode = new ArrayList<>();
                    currNode.add(str.substring(1));
                }
                else if (str.startsWith("____"))
                {
                    currNode.add(str.substring(4));
                }

                allLines.add(str);
                str = s.nextLine();
            }

            if (!currNode.isEmpty())
            {
                nodesTxt.add(currNode);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file wasn't found");
        }


    }



}
