package ui.messages;

import org.newdawn.slick.Graphics;
import setup.Fonts;
import org.newdawn.slick.Color;

public class FloatMessage extends Message{

	Color bgColor;

	public FloatMessage (String text, float x, float y, Color color, int duration)
	{
		super(text, x, y, color,Fonts.medium, duration);
		bgColor = color;
		this.color = Color.black;
		fading = true;
	}

	public void update()
	{
		super.update();
		y--;
	}

	public void render(Graphics g)
	{
		g.setColor(bgColor);
		g.fillRoundRect(x,y,font.getWidth(text), font.getHeight(text), 3);
		g.setColor(Color.white);
		g.drawRoundRect(x,y,font.getWidth(text), font.getHeight(text), 3);
		super.render(g);
	}

}
