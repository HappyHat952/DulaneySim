package ui.messages;

import setup.Fonts;
import core.Main;
import org.newdawn.slick.Color;

public class BigMessage extends Message{
	
	public BigMessage(String text, Color color, int duration)
	{
		
		super(text, Main.getScreenWidth()/2, Main.getScreenHeight()-194, color, Fonts.big, duration);
		fading = true;
		centerText();
	
	}
	
	
	

}
