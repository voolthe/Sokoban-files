package Sokoban;

import java.awt.Color;
import java.awt.Graphics;

public class Bonus extends Game_Object {

	public Bonus(int x, int y, int w, int h, Type type, Color color) {
	super(x, y, w-10, h-10, type, color);
		
	}



	
	
	
	public void paint(Graphics g) {

		g.setColor(color);
		//g.setColor(Color.blue);
		g.fillOval(x, y, w, h);
	}

}
