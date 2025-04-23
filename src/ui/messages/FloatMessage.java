package ui.messages;

import setup.Fonts;
import org.newdawn.slick.Color;

public class FloatMessage extends Message{
	
	public FloatMessage (String text, float x, float y, Color color, int duration)
	{
		super(text, x, y, color,Fonts.big, duration);
		fading = true;
	}
	
	public void update()
	{
		super.update();
		y--;
	}

}
