package Sokoban;


import javax.swing.JPanel;
import java.awt.*;

/*
 * Klasa odpowiedzialna za Panel ³aduj¹cy zdjêcie t³a do g³ównego okna gry
 */

public class ImagePanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * obrazek
	 */
	Image img;
	/**
	 * flaga informuj¹ca czy obrazek jest za³adowany
	 */
	boolean if_loaded;

	/**
	 * Konstruktor tej klasy
	 */
	public ImagePanel(String filename)
	{
		//metoda rysuj¹ca obrazek 
		loadImage(filename);
	}

	/**
	 * Przedefiniowanie metody PaintComponent 
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(img!=null&&if_loaded)
		{
			g.drawImage(img, 0, 0, getWidth(),getHeight(),this);
		}
		else
			g.drawString("Brak obrazka", 10, getHeight()-10);
	}

	/**
	 * Funkcja pobieraj¹ca wysokoœc i szerokosæ obrazka, ustawia je.
	 * @param imgFilename -nazwa pliku z obrazkiem
	 */
	private void loadImage(String imgFilename)
	{
		img= Toolkit.getDefaultToolkit().getImage(imgFilename);
		MediaTracker mt= new MediaTracker(this);
		mt.addImage(img, 1);
		try{
			mt.waitForID(1);
		}catch(InterruptedException iE){
		}
		int w=img.getWidth(this); //szerokoœæ
		int h=img.getHeight(this);//wysokoœæ
		
		if(w != -1 && w != 0 && h != -1 && h != 0)
		{
		if_loaded= true;
		setPreferredSize(new Dimension(w, h));
		}
		else
			setPreferredSize(new Dimension(200, 200));
		
	}
	
	
	
	
}
