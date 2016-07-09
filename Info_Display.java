package Sokoban;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Klasa wyœwietlj¹ca informacje o graczu i grze na planszy 
 */
public class Info_Display  {

/**
 * nick gracza 
 */
	private String nick;
	/**
	 * level , punkty, czas gru
	 */
	private int level,score,game_time;
	
	/**
	 * konstruktor 
	 * @param nick nick graca
	 * @param level poziom 
	 * @param score punkty
	 * @param game_time czas gru
	 */
	public Info_Display(String nick, int level,int score, int game_time)
	{
		this.nick=nick;
		this.level=level;
		this.score=score;
		this.game_time=game_time;
	}
		/**
		 * rysuje dane na planszy 
		 * @param g- kontekst graficzny 
		 */
	public void paint(Graphics g)
	{
	g.setColor(Color.white);
	g.drawString("Gracz:  "+ nick, 10, 20);
	g.drawString("Poziom: "+Integer.toString(level),10, 40);
	g.drawString("Wynik:  "+Integer.toString(score), 10, 60);
	g.drawString("Czas:  "+Integer.toString(game_time), 10, 80);
		
	}
	/**
	 * nadpisanie update
	 * @param g
	 */
	public void update(Graphics g)
	{
		paint(g);
	}
	/**
	 * aktualizacja danych wyœwietlanych na planszy
	 * @param nick nowy nick
	 * @param level nowy lvl
	 * @param score nowy wynik
	 * @param game_time nowy czas
	 */
	public void tick(String nick, int level,int score, int game_time)
	{
		this.nick=nick;
		this.level=level;
		this.score=score;
		this.game_time=game_time;
	}
	
}
