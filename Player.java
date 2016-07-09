package Sokoban;

import java.awt.Color;
import java.awt.Graphics;

/**
 * klasa reprezentujaca gracza
 */
public class Player extends Moving_Object {

	/**
	 * obiekt przechowuj¹cy elementy planszy
	 */
	Objects_Handler handler;

	/**
	 * Konstruktor klasy gracza
	 * 
	 * @param x
	 *            - polozenie
	 * @param y-
	 *            po³ozenie
	 * @param w
	 *            - szerokosc elementu
	 * @param h
	 *            - wysokosc elementu
	 * @param type
	 *            - typ
	 * @param color
	 *            - kolor rysowania
	 */
	public Player(int x, int y, int w, int h, Type type, Color color, Objects_Handler handler) {
		super(x , y, w - 10, h - 10, type, color);// wywolanie
															// konstruktora z
															// klasy
															// abstrakcyjnej

		this.handler = handler;
		handler.add_Object(new Floor(x,y,w,h, Type.Floor, Color.white));
	}

	/**
	 * nadpisanie metody z klasy bazowej
	 */
	public void move() {
		x += velX;
		y += velY;

		x = Main_Game.restrict_var(0, Main_Game.WIDTH - 40, x);
		y = Main_Game.restrict_var(0, Main_Game.HEIGHT - 40, y);

		collision();

	}

	/**
	 * funkcja obs³uguj¹ca zderzenia gracza z innymi elementami planszy
	 */
	public void collision() {
		// obsluga zderzenia ze œcian¹
		for (int i = 0; i < handler.objects.size(); i++) {
			Game_Object tmp = handler.objects.get(i);

			if (tmp.getType() == Type.Wall) {
				// chce przejœæ przez œcianê
				if (getBounds().intersects(tmp.getBounds())) {
					// nie przechodzi przez sciane
					do_not_move();
				}
			}
			// obsluga zebrania bonusa
			if (tmp.getType() == Type.Bonus) {
				if (getBounds().intersects(tmp.getBounds())) {
					// zbiera bonus
					handler.add_Object(new Floor(tmp.getX(), tmp.getY(), tmp.getW()+10, tmp.getH()+10, Type.Floor, Color.white));
					handler.delete_Object(tmp);

					handler.setPicked_bonuses(handler.getPicked_bonuses() + 1);
				}
			}
		}

		// obs³uga zderzenia ze skrzyni¹
		for (int i = 0; i < handler.moving_objects.size(); i++) {
			Moving_Object tmp = handler.moving_objects.get(i);

			if (tmp.getType() == Type.Box) {

				if (getBounds().intersects(tmp.getBounds())) {
					// Przesuwa skrzyniê
					tmp.setvelX(velX);
					tmp.setvelY(velY);
					tmp.move();
					Box b = (Box) tmp;
					//jes³i nie uda³o siê przesun¹æ skrzyni bo j¹ coœ blokuje- nie poruszamy siê
					if (b.get_moved() == false)
						do_not_move();
				
				}
				
			}
		}

	}

	/**
	 * funcja blokuj¹ca ruch gracza, gdy chce wejœc na œcianê itp cofa go zanim zostanie narysowany
	 */
	private void do_not_move() {
		x -= velX;
		y -= velY;
		velX = 0;
		velY = 0;

	}

	/**
	 * implementacja metody paint dla gracza
	 * 
	 * @param g - kontekst graficzny
	 */
	public void paint(Graphics g) {
		
		g.setColor(color);
		g.fillOval(x, y, w, h);

	}

}
