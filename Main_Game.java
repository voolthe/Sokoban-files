package Sokoban;

import java.awt.Canvas;

import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
//some coment :)


/**
 * Glowna klasa programu - gry
 * @author Patryk Glwienko
 *
 */
public class Main_Game extends Canvas implements Runnable 
{
/**
	 * UID serial version for Canvas
	 */
	private static final long serialVersionUID = 5106777602472832208L;
	

//zmienne - pola
	//------------------------------------------------------------------
	
	public  static int HEIGHT=400;
	public static int WIDTH=400;
	/*
	 * Watek
	 */
	private Thread thread;
	/*
	 * czy watek dziala - isRunning?
	 */
	private boolean isRunning;
	
	/**
	 * zmienna mówi¹ca przy gra dzia³a
	 */
	private boolean isPlaying;
	/*
	 * zmienna obiektu ktory trzyma w sobie tablice obiektow planszy 
	 */
	private Objects_Handler handler;
	
	/**
	 * zmienna rysuj¹ca na ekranie informacje tekstowe
	 */
	private Info_Display hud;
	
	/**
	 * poziom trudnosci
	 */
	private String difficulty;
	
	/**
	 * numer poziomu 
	 */
	private int level;
	
	/**
	 * czas gry
	 */
	private int time;
	
	/**
	 * zmienna nicku gracza 
	 */
	private String player_name;
	
	
	/**
	 * 
	 */
	private BestScores bestScores;
	/**
	 * wynik gracza
	 */
	private int score;
	private int general_score;
	
	/**
	 * klasa kolejnego poziomu
	 */
	private Game_Level gameLevel;
	
	/**
	 * aktualny stan gry- menu, gra, pauza
	 */
	public Game_State game_state;
	
	
	/**
	 * przechowuje rozmiar ile kratek na ile jest obecna plansza 
	 */
	private Dimension level_dimensions;
	
	/**
	 * zmienna menu 
	 */
	private Menu menu;
	
	private Game_window g_w;
	


	//funkcje
//-----------------------------------------------------------------------------------------------------------
	
	/**
	 * konstruktor glównej klasy gry
	 */
	public Main_Game()
	{
		game_state=Game_State.Menu;
		isRunning=false;
		
		handler= new Objects_Handler(this);
		
		hud=new Info_Display(player_name,level,score,time);
		menu=new Menu(this);
		
		//dodanie listenera do okna gry :) poruszanie gracza tp ! :)
		this.addKeyListener(new Key_Listener(handler,this));

		isPlaying=false;
		level=1;
		score=0;
		difficulty="easy";
		general_score=0;

		//setLevel_dimensions(new Dimension(WIDTH,HEIGHT));
		
	//	g_w=new Game_window("Sokoban",this);
		bestScores=new BestScores();
		g_w= new Game_window("Sokoban",this,new Dimension(WIDTH, HEIGHT));
		start();
		
	}
	
	/**
	 * Funkcja uruchamiajaca watek
	 */
	public synchronized void start ()
	{
		thread= new Thread(this);//glowny watek gry
		thread.start();
		isRunning=true;
		
	}
	/**
	 * Funkcja zatrzymujaca watek
	 */
	public synchronized void stop ()
	{
		try{
			thread.join();
			isRunning=false;
			
		}catch(Exception ex)
		{
		ex.printStackTrace();	
		}
	}
	/*
	 * Metoda odpowiedzialna za dzialanie watku glównego gry 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		requestFocus();
		
		long lastTime=System.nanoTime();
		double amountofTicks=60;
		double delta=0;
		long timer= System.currentTimeMillis();
		//int frames=0;
		double ns=1000000000/amountofTicks;
		while(isRunning)
		{
		
			long now=System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			//decyzje i dzia³anie gry 
			while(delta>=1)
			{
				tick();
				delta--;
			}
			//rysowanie
			if(isRunning)
				
				update();
		//	frames++;
			
			if(System.currentTimeMillis()-timer>1000)
			{
				timer+=1000;
				//frames=0;
			}
			
			/*
			if(handler.check_win()==true)
			{
				System.out.println("win: "+handler.check_win());
			stop();
			}*/
		}
		
		stop();
	}
	

	
	
	/*
	 * funkcja rysujaca elementy planszy  
	 */	
	public void paint() //jakby render
	{		
		BufferStrategy bs=this.getBufferStrategy();
		if(bs==null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g=bs.getDrawGraphics();
		
		if(game_state==Game_State.Game)
		{
			 handler.update(g);	 
			 hud.update(g);
			 g.dispose();
			 bs.show();
		}
	}
	



	/**
	 * nadpisanie metody update()
	 */
	 public void update() {
	        paint();
	    }
	
	 
	 public void tick()
	 {
		 double tmp=0;
		 
		 long time_start=System.nanoTime();
			switch(game_state)
			{
			//jesli stan gry to GRA to odmaluj planszê gry 
			case Game:
				handler.move();
				//wygrana, przeszed³ wszyskie poziomy xd
				if(level>3)
				{
					bestScores.addPlayer(new UserProfile(player_name,general_score) );
					try {
						bestScores.writeFile();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
					game_state=Game_State.Menu;
				}
				
				
				//if(!g_w.is_Visible())
					g_w.set_Visible(true);
				if(menu.isVisible())
					menu.setVisible(false);
				/**
				 * gdy nie rozpoczê³a siê gra, a jestesmy w stanie game - tworzenie odpowiedniej planszy
				 * lub chcemy nowy poziom po ukoñczonym przed chwil¹
				 */
			if(!isPlaying)
				{
				isPlaying=true;
				prepareLevel();
				time_start=System.nanoTime();
				}
		//gra trwa
			else
			{
			//	general_score+=score;
				tmp+=(double) (System.nanoTime()-time_start);
				if(tmp>=100000000)
				{
				time+=1;
				tmp=0;
				time_start=System.nanoTime();
				}
				//wygrana - kolejna plansza :)
				if(handler.check_win()==true)
				{
					general_score+=score;
					level++;
					isPlaying=false;
				}
			}
				break;
				
			case Menu:
				
				if(g_w.is_Visible())
					g_w.set_Visible(false);
					
					if(!menu.isVisible())
					menu.setVisible(true);
					
					
					player_name=menu.getPlayer_nick();
					difficulty=menu.getDifficulty();
					
				break;
				
			case Pause:
				
				break;
				
				
			default:
				break;
			
		
			}
		 
		hud.tick(player_name,level,general_score,time);

	 }

	 


	
	
	/**
	 * funkcja pozwalaj¹ca na pobranie rozmaiaru okna
	 */
	 public Dimension getPreferredSize() {
	        return new Dimension(HEIGHT, WIDTH);
	    }
	 
	/**
	 * zfunkcja ograniczaj¹ca zmienn¹ do min oraz max, zeby obiekty nie wychodzi³y za ekran
	 * @param min
	 * @param max
	 * @param var
	 * @return zwracana zmienna - min,max, lub sama zmienna
	 */
	 public static int restrict_var(int min,int max, int var)
	 {
		 if(var>=max) return max;
		 else if(var<=min) return min;
		 else return var;
	 }
	 
	 
	 /**
	  * getter zmiennej level_dimensions
	  * @return zmienna level dimensions
	  */
	 	public Dimension getLevel_dimensions() {
	 		return level_dimensions;
	 	}
	 	/**
	 	 * setter zmiennej level_dimensions
	 	 * @param level_dimensions - nowa iloœæ bloków planszy 
	 	 */
	 	public void setLevel_dimensions(Dimension level_dimensions) {
	 		this.level_dimensions = level_dimensions;
	 	}
	 
	 	
	 	/**
	 	 * getter Stanu gry 
	 	 * @return stan gry 
	 	 */
	 	public Game_State getGame_state() {
			return game_state;
		}
/**
 * ustawia stan gry
 * @param game_state  - nowy stan gry
 */
		public void setGame_state(Game_State game_state) {
			this.game_state = game_state;
		}
		
		

		
		/**
		 * getter isRunning
		 * @return czy w¹tek dzia³a
		 */
			public boolean getRunning() {
				return isRunning;
			}

			/**
			 * setter isRunning 
			 * @param isRunning - nowa wartoœc dzia³ania w¹tku 
			 */
			public void setRunning(boolean isRunning) {
				this.isRunning = isRunning;
			}


			/**
			 * getter Game Window
			 * @return okno g³ówne
			 */
			public Game_window getG_w() {
				return g_w;
			}

			/**
			 * ustawia okno g³ówne
			 * @param g_w- okno g³ówne
			 */
			public void setG_w(Game_window g_w) {
				this.g_w = g_w;
			}
		
			
			public void prepareLevel()
			{
				handler= new Objects_Handler(this);			
				Main_Game.this.addKeyListener(new Key_Listener(handler,Main_Game.this));
				g_w.dispose();
				
				//poziom ³atwy
				if(difficulty=="easy")
				{
			
				switch(level)
				{
				case 1:
					gameLevel=new Game_Level(new Config_File_Loader("poziom_1.txt"), handler); 
					gameLevel.fill_handler();
					System.out.println(gameLevel.get_size().getHeight());
					HEIGHT=(int) (50*gameLevel.get_size().getHeight());
					WIDTH=(int) (50*gameLevel.get_size().getWidth());
					g_w= new Game_window("Sokoban",this,new Dimension(WIDTH, HEIGHT));
					break;
					
				case 2:
					gameLevel=new Game_Level(new Config_File_Loader("poziom_2.txt"), handler); 
					gameLevel.fill_handler();
					HEIGHT=(int) (50*gameLevel.get_size().getHeight());
					WIDTH=(int) (50*gameLevel.get_size().getWidth());
					g_w= new Game_window("Sokoban",this,new Dimension(WIDTH, HEIGHT));
					break;
					
				case 3:
					gameLevel=new Game_Level(new Config_File_Loader("poziom_3.txt"), handler); 
					gameLevel.fill_handler();
					HEIGHT=(int) (50*gameLevel.get_size().getHeight());
					WIDTH=(int) (50*gameLevel.get_size().getWidth());
					g_w= new Game_window("Sokoban",this,new Dimension(WIDTH, HEIGHT));
					break;
					
					
				default:
						
					break;
				}
				}
				//poziom trudny
				else
				{
					switch(level)
					{
					case 1:
						gameLevel=new Game_Level(new Config_File_Loader("hard_1.txt"), handler); 
						gameLevel.fill_handler();
						System.out.println(gameLevel.get_size().getHeight());
						HEIGHT=(int) (50*gameLevel.get_size().getHeight());
						WIDTH=(int) (50*gameLevel.get_size().getWidth());
						g_w= new Game_window("Sokoban",this,new Dimension(WIDTH, HEIGHT));
						break;
						
					case 2:
						
						gameLevel=new Game_Level(new Config_File_Loader("hard_2.txt"), handler); 
						gameLevel.fill_handler();
						System.out.println(gameLevel.get_size().getHeight());
					HEIGHT=(int) (50*gameLevel.get_size().getHeight());
					WIDTH=(int) (50*gameLevel.get_size().getWidth());
					g_w= new Game_window("Sokoban",this,new Dimension(WIDTH, HEIGHT));
					
						break;
						
					case 3:
						gameLevel=new Game_Level(new Config_File_Loader("hard_3.txt"), handler); 
						gameLevel.fill_handler();
						HEIGHT=(int) (50*gameLevel.get_size().getHeight());
						WIDTH=(int) (50*gameLevel.get_size().getWidth());
						g_w= new Game_window("Sokoban",this,new Dimension(WIDTH, HEIGHT));
						break;
						
						
					default:
							
						break;
					
				}
				}
			}
		
			public String getDifficulty() {
				return difficulty;
			}

			public void setDifficulty(String difficulty) {
				this.difficulty = difficulty;
			}

			public int getLevel() {
				return level;
			}

			public void setLevel(int level) {
				this.level = level;
			}

			public String getPlayer_name() {
				return player_name;
			}

			public void setPlayer_name(String player_name) {
				this.player_name = player_name;
			}

			public int getScore() {
				return score;
			}

			public void setScore(int score) {
				this.score = score;
			}
			
		
			
			
public int getTime() {
				return time;
			}

			public void setTime(int time) {
				this.time = time;
			}

	public int getGeneral_score() {
				return general_score;
			}

			public void setGeneral_score(int general_score) {
				this.general_score = general_score;
			}

			
			/**
			 * zwraca menu, getter 
			 * @return menu
			 */
			public Menu getMenu()
			{
				return menu;
			}
	//-------------------------------------------------------------
	/**
	 * funckja main
	 * @param args
	 */
	public static void main(String args[])
	{
		new Main_Game();
	}
	}


	
