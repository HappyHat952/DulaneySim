package core;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import setup.Images;
import setup.Fonts;

public class Game extends BasicGameState 
{	
	private int id;
	private static StateBasedGame sbg;
	private static Player p;

	public Game(int id) 
	{
		this.id = id;
	}
	
	public int getID() 
	{
		return id;		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		gc.setShowFPS(true);
		Images.loadImages();
		Fonts.loadFonts();
		p = new Player();
		this.sbg = sbg;
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		// This updates your game's logic every frame.  NO DRAWING.
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		// This code renders shapes and images every frame.
		g.setColor(Color.white);
		g.drawString("Hello World!", Main.getScreenWidth() * .5f, Main.getScreenHeight() * .5f);
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a gameState.  
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		// This code happens when you leave a gameState. 
	}

	public void keyPressed(int key, char c)
	{
		if (key == Input.KEY_X)
		{
			sbg.enterState(Main.RACER_ID);
		}
		if (key == Input.KEY_L)
		{
			sbg.enterState(Main.LOCKER_ID);
		}
		// This code happens every time the user presses a key
	}
	
	public void mousePressed(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
	}
	
	


}
