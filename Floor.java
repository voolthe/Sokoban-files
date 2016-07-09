package Sokoban;

import java.awt.Color;
import java.awt.Graphics;


/**
 * klasa re[rezentuj�ca pod�og� planszy

 */
public class Floor extends Game_Object{

	
	/**
	 * konstruktor klasy Floor
	 * @param x- po�ozenie
	 * @param y- po�o�enie
	 * @param w - wysokosc
	 * @param h - szeroko�� 
	 * @param type
	 * @param color
	 */
	public Floor(int x, int y, int w, int h, Type type, Color color) {
		super(x, y, w, h, type, color);
	}

/**
 * nadpisanie metody rysuj�cej element
 */
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, w, h);
		
	}

}
