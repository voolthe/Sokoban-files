package Sokoban;

import java.awt.Color;
import java.awt.Dimension;

/*'
 * klasa reprezentujaca pojedynczy level gry 
 */
public class Game_Level {

	//zmienne ----------------------------------------
	
	/*
	 * obiekt klasy pobierajcej plansze z pliku 
	 */
	private Config_File_Loader c_f_l;	
	/*
	 * klasa tablicy obiektow
	 */
	private Objects_Handler handler;
	
	
	
	//funckje ------------------------------------------------------
	public Game_Level(Config_File_Loader config,Objects_Handler handler)
	{
		this.handler=handler;
		c_f_l=config;
		
	}
	
	
	/**
	 * metoda wype³niaj¹ca tablicê obiektów bêd¹cych elemetami planszy
	 */
	public void fill_handler()
	{
		int x = 50;//szerokosc elmetu planszy 
		int y = 50;//wysokosc
	
	//	game.resize(new Dimension(x*c_f_l.getWidth(),y*c_f_l.getHeight()));
	//	game.setVisible(true);
		
		for(int i=0; i<c_f_l.getHeight();i++)
		{
			for(int j=0;j<c_f_l.getWidth();j++)
			{
				switch(c_f_l.level_table[i][j])
				{
				case '#':
					handler.add_Object(new Wall(x*j,y*i,x,y,Type.Wall,Color.black)); //tworzymy obiekt - œciana
					break;
					
				case '0':
					handler.add_Object(new Floor(x*j,y*i,x,y,Type.Floor,Color.white)); //tworzymy obiekt - podloga		
					break;
					
				case '$':
					handler.add_moving_Object(new Box(x*j,y*i,x,y,Type.Box,Color.green,handler)); //tworzymy obiekt - skrzynia
					break;
					
				case 'x':
					handler.add_Object(new Box_Point(x*j,y*i,x,y,Type.BoxPoint,Color.gray)); //tworzymy obiekt - boxpoint
					
					break;
					
				case '@':
					handler.add_moving_Object(new Player(x*j,y*i,x,y,Type.Player,Color.red,handler)); //tworzymy obiekt - gracza
					
					break;	
				case 'b':
					handler.add_Object(new Bonus(x*j,y*i,x,y,Type.Bonus,Color.yellow)); //tworzymy obiekt - gracza
					
					break;	
				
				default:
					handler.add_Object(new Wall(x*j,y*i,x,y,Type.Floor,Color.black)); //tworzymy obiekt - sciana 
					break;
				}
			}
		}
	}
	
	public Dimension get_size()
	{
		return new Dimension(c_f_l.getWidth(),c_f_l.getHeight());
		
	}
	
	
	
	
	
}
