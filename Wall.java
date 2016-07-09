package Sokoban;

import java.awt.Color;
import java.awt.Graphics;


/*
 * œciana
 */
public class Wall extends Game_Object {

/*
 * Konstrukdtor klasy Wall
 */
	public Wall(int x, int y, int w, int h, Type type, Color color) 
	{
		super(x, y, w, h, type, color);
	}

	/**
	 * impelementacja metody z klasy bazowej abstrakcyjnej
	 */
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, w, h);
		
	}

}
