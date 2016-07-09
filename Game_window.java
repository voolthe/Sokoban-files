package Sokoban;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 
 * @author Patryk
 *klasa okna glownego w ktorym rysowana jest gra/menu itp itp
 */
public class Game_window extends Canvas{
	/**
	 * serial version of UID
	 */
	private static final long serialVersionUID = -2573132332565568760L;

	/**
	 * frame g³ównego okna 
	 */
	private JFrame frame;
		
	
	
	/**
	 * konstruktor okna 
	 * @param width -szerokosc pierwotna
	 * @param height - wysokosc pierwotna
	 * @param title - tytu³ okna 
	 * @param game - obiekty gry, który zostanie wrzucony do okna
	 */
	public Game_window( String title, Main_Game game, Dimension dim)
	{
		
		frame=new JFrame(title);
		Dimension dime = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dime.width/2-100, dime.height/2-100);
	//	frame.requestFocus();
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLocationRelativeTo(game);
		frame.setResizable(true);
		frame.setPreferredSize(dim);
		frame.add(game);
		frame.setVisible(false);
		
		frame.pack();

		//game.start();
	
		
		
	}	
	/**
	 * usuwa okno
	 */
	public void dispose()
	{
		frame.dispose();
	}
	public void set_Visible(boolean x)
	{
		frame.setVisible(x);
	}
	public boolean is_Visible()
	{
		return frame.isVisible();
	}
}
