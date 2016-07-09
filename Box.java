package Sokoban;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Klasa Skrzyni
 */
public class Box extends Moving_Object {

	/**
	 * Pojemnik obiektów dla tej klasy
	 */
	Objects_Handler handler;

	/**
	 * zmienna mówi¹ca czy uda³o siê poruszyæ skrzyniê
	 */
	boolean moved;

	/**
	 * zmienna mówi¹ca czy skrzynia jest na box_poincie
	 */
	boolean on_box_point;

	/**
	 * Konstruktor klasy box
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
	public Box(int x, int y, int w, int h, Type type, Color color, Objects_Handler handler) {
		super(x, y, w, h, type, color);
		this.handler = handler;

		handler.add_Object(new Floor(x,y,w,h, Type.Floor, Color.white));
		moved = false;
	}

	/**
	 * implementacja metody paint dla boxa
	 * 
	 * @param g
	 *            - kontekst graficzny
	 */
	public void paint(Graphics g) {

		// g.clearRect(x, y, w, h);
		if (on_box_point)
			color = Color.BLUE;

		g.setColor(color);
		g.fillRect(x, y, w, h);

	}

	/**
	 * nadpisanie metody z klasy bazowej
	 */
	public void move() {
		moved = true;
		x += velX;
		y += velY;

		//x = Main_Game.restrict_var(0, Main_Game.WIDTH - 67, x);
		//y = Main_Game.restrict_var(0, Main_Game.HEIGHT - 87, y);

		collision();
		check_box_point();
		velX=0;
		velY=0;
		
	}

	/**
	 * funkcja obs³uguj¹ca zderzenia gracza z innymi elementami planszy
	 */
	public void collision() {
		// obsluga zderzenia ze œcian¹ lub inn¹ skrzyni¹
		for (int i = 0; i < handler.objects.size(); i++) 
		{
			Game_Object tmp = handler.objects.get(i);

			if (tmp.getType() == Type.Wall || tmp.getType() == Type.Box) {
				if (getBounds().intersects(tmp.getBounds())) {
					// nie przechodzi przez sciane
					moved = false;
					do_not_move();
				
				}
			}
				// obsluga zniszczenia bonusu
			if (tmp.getType() == Type.Bonus) {
				if (getBounds().intersects(tmp.getBounds())) {
					// zbiera bonus
					handler.add_Object(new Floor(tmp.getX(), tmp.getY(), tmp.getW()+10, tmp.getH()+10, Type.Floor, Color.white));
					handler.delete_Object(tmp);
					}
				}
		}

	}

	/**
	 * funkcja sprawdza czy skrzynia jest na polu docelowym
	 * @return on_box_point, wartoœæ informuj¹c¹ czy skrzynia jest na punkcie docelowym
	 */
	public boolean check_box_point() {
		on_box_point = false;

		for (int i = 0; i < handler.objects.size(); i++) {
			if (handler.objects.get(i).getType() == Type.BoxPoint) {
				Rectangle r = handler.objects.get(i).getBounds();
				
				r.setBounds((int) r.getX()+(int) r.getWidth() / 3, (int) r.getY()+(int) r.getHeight() / 3, (int) r.getWidth() / 3, (int) r.getHeight() / 3);
				if (getBounds().intersects(r))
					on_box_point = true;

			}

		}
		return on_box_point;

	}

	/**
	 * mówi czy poruszono skrzyniê
	 * @return moved
	 */
	public boolean get_moved() {
		return moved;
	}

	/**
	 * funkcja zwraca informacjê czy skrzynia jest na docelowym punkcie
	 * 
	 * @return
	 */
	public boolean get_on_box_point() {
		return on_box_point;
	}
	
	/**
	 * funcja blokuj¹ca ruch skrzyni, gdy chce wejœc na œcianê itp cofa go zanim zostanie narysowany
	 */
	private void do_not_move() {
		x -= velX;
		y -= velY;
		velX = 0;
		velY = 0;

	}

}
