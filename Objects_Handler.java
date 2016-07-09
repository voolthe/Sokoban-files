package Sokoban;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 * Klasa trzymajaca tablice obiektow, odmalowuje je i nimi zarzadza co tykniecie zegara
 */
public class Objects_Handler {

	/**
	 * zmienna przechowuj¹ca wszystkie obiekty gry 
	 */
	LinkedList<Game_Object> objects= new LinkedList<>();
	/**
	 * zmienna przechowuj¹ca wszystkie poruszaj¹ce siê obiekty 
	 */
	LinkedList<Moving_Object> moving_objects= new LinkedList<>();
	

	/**
	 * czy wygrana
	 */
	boolean win=false;

	/**
	 * obiekt g³ównej klasy
	 */
	private Main_Game m_g;
	
	private  int boxes_on_point;
	private int picked_bonuses;
	
	
	//konstruktor
	public Objects_Handler(Main_Game m_g) {
		this.m_g=m_g;
		boxes_on_point=0;
		picked_bonuses=0;
	}
	
	
	//funkcje ------------------------------------------------------------------------------------
	
	public int getBoxes_on_point() {
		return boxes_on_point;
	}


	public void setBoxes_on_point(int boxes_on_point) {
		this.boxes_on_point = boxes_on_point;
	}


	/**
	 *przesuwa wszystkie obiekty co tykniecie 
	 */
	public void move()
	{
		for(int i=0; i<moving_objects.size(); i++)
		{	
			moving_objects.get(i).move();	
			m_g.setScore(count_score());
		}
	}
	
	/**
	 * sprawdza czy jest wygrana 
	 * @return wygrana lub nie 
	 */
	public boolean isWin() {
		return win;
	}

	/**
	 * ustawia wygran¹
	 * @param win
	 */
	public void setWin(boolean win) {
		this.win = win;
	}

	/*
	 * renderuje wszystkie obiekty ktore siê poruszaj¹ 
	 */
	
	
	/**
	 * rysuje obiekty trzymane w handlerze
	 * @param g - kontekst graficzny 
	 */
	public void paint(Graphics g)
	{
		Moving_Object tmp=null;
			
		for(int i=0; i<objects.size(); i++)
		{
			objects.get(i).update(g);
		}
		
		for(int i=0; i<moving_objects.size(); i++)
		{
			if(moving_objects.get(i).getType()==Type.Player) tmp=moving_objects.get(i);
						
			moving_objects.get(i).update(g);
		}
		
	//	tmp.paint(g);
		
	}
	
	/**
	 * nadpisanie metody update() wywo³uj¹cej paint()
	 * @param g
	 */
	public void update(Graphics g)
	{
		//skalowanie okna 
		//update_dim();
		paint(g);	
	//System.out.println("Wygrana: "+check_win());
	}
	
/*
 * dodaje podany obiekt do tablicy	
 */
	public void add_Object(Game_Object ob)	{objects.add(ob);  }
	
	/*
	 * dodaje podany obiekt do tablicy	 poruszaj¹cych siê obiektow
	 */
		public void add_moving_Object(Moving_Object ob)	{moving_objects.add(ob);  }
		/*
	
	/*
	 *usuwa podany obiekt do tablicy	 poruszaj¹cych siê obiektow
	 */
		public void delete_moving_Object(Moving_Object ob)	{moving_objects.remove(ob);  }
		/*
	
	
	/*
	 * usuwa zadany obiekt z tablicy 
	 */
	public void delete_Object(Game_Object ob){objects.remove(ob);  }
	
	/**
	 * sprawdza wygran¹
	 * @return true jesli skrzynki na miejscach - wygrana , false jesli nie wygrano
	 */
	public boolean check_win()
	{
		win=false;
		
		for(int i=0; i<moving_objects.size(); i++)
		{
			if(moving_objects.get(i).getType()==Type.Box)
			{
				
				Box b=(Box) moving_objects.get(i);
				if(b.get_on_box_point()==true) 
					win=true;
				else
					win=false;
			}
		}

		return win;	
	}
	
	/**
	 * zraca bliczon¹ liczbê punktów 
	 * @return liczba punktów
	 */
	public int count_score()
	{
		//int score=0;
		for(int i=0; i<moving_objects.size(); i++)
		{
			if(moving_objects.get(i).getType()==Type.Box)
			{
				
				Box b=(Box) moving_objects.get(i);
				if(b.get_on_box_point()==true) 
					boxes_on_point++;
				
			}
		}
		return 50*boxes_on_point+100*picked_bonuses;
	}
	


/**
 * getter iloœci bonusów 
 * @return iloœc bonusów 
 */
	public int getPicked_bonuses() {
		return picked_bonuses;
	}

/**
 * setter ilosci bonusów
 * @param picked_bonuses - nowa ilosc zebranych bonusów.
 */
	public void setPicked_bonuses(int picked_bonuses) {
		this.picked_bonuses = picked_bonuses;
	}
			
		
	
	
}
