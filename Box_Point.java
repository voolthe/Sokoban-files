package Sokoban;

import java.awt.Color;
import java.awt.Graphics;

/*
 * Knstruktor klasy punkt docelowy skrzyni
 */
public class Box_Point extends Game_Object {

	/*
	 * Konstruktor klasy Punkt docelowy skrzyni
	 */
	public Box_Point(int x, int y, int w, int h, Type type, Color color)
	{
		super(x, y, w, h, type, color);
	}

	
	/**
	 * Implementacja meotdy z klasy abstakcyjnej bazowej
	 */
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, w, h);
		
	}

}
