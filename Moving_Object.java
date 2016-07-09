package Sokoban;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *Rozszerza klasê obiektu gry o szybkoœæ i obs³ugê poruszania siê po planszy
 */
public abstract class Moving_Object {

	
	/**
	 * szybkosc poruszania sie obiektu
	 */
	protected int velX,velY;
	

	//zmienne---------------------------------------------------
	/**
	 * polozenie na planszy 
	 */
	protected int x,y;
	/**
	 * typ obiektu
	 */
	protected Type type;

	/*
	 * szerokosc i wysokosc rysowanego elementu 
	 */
	protected int w,h;
	
	/**
	 * kolor rysowania ele
	 */
	protected Color color;
	
	
	
	//--------------------------------------------------------
	//funckje 
	
	/**
	 * konstruktor obiektu gry
	 * @param w- szerokosc obiektu 
	 * @param h- wysokosc obiektu 
	 * @param color - kolor obiektu 
	 * @param x - polozenie 
	 * @param y - polozenie
	 * @param type - typ tworzonego obiektu
	 */
	public Moving_Object(int x,int y,int w, int h, Type type, Color color)
	{
		this.x=x;
		this.y=y;
		this.type=type;
		this.w=w;
		this.h=h;
		this.color=color;
		
		velX=0;
		velY=0;
	}
	
	/**
	 * funkcja odpowiedzialna za ruch obiektu
	 * @param velY2 
	 * @param velX2 
	 */
	public void move()
	{
		x+=velX;
		y+=velY;
		
		x =Main_Game.restrict_var(0, Main_Game.WIDTH-50, x);
		y=Main_Game.restrict_var(0, Main_Game.HEIGHT-50, y);
		
	}
	
	
	/**
	 * Funkcja zwracaj¹ca prostok¹t o takich rozmiarach jak obiekt 
	 * @return prostok¹t o rozmiarach danego obietku
	 */
	public Rectangle getBounds()
	{
		if(type==Type.Player)
		return new Rectangle(x+5, y+5, w-10, h-10);
		else
		return new Rectangle(x, y, w, h);
			
	}
	
	/**
	 * metoda odmalowujaca obiekt
	 * @param g - przeslany konstekst graficzny
	 */
	public abstract void paint(Graphics g);
	
	
	/**
	 * wywo³anie metody paint - odswie¿enie kontekstu graficznego
	 * @param g - kontekst graficzny
	 */
	public void update(Graphics g)
	{
		paint(g);
	}

	
	
	/*
	 * getter zmiennej x 
	 */
	public int getX(){ return x; }
	/*
	 * getter zmiennej y
	 */
	public int getY(){ return y; }
	
	/*
	 * getter zmiennej w 
	 */
	public int getW(){ return w; }
	/*
	 * getter zmiennej h
	 */
	public int getH(){ return h; }
	
	public Color getColor(){ return color;}

	
	/*
	 * getter zmiennej type
	 */
	public Type getType(){ return type; } 
	
	
	
	/*
	 * setter zmiennej x 
	 */
	public void  setX(int x){ this.x=x; }
	/*
	 * setter zmiennej y
	 */
	public void  setY(int y){ this.y=y; }
	/*
	 * setter zmiennej type
	 */
	public void  setType(Type type){ this.type=type; }
	
	public void setColor(Color color){ this.color=color; }
	/*
	 * setter dla w 
	 */
	public void set_w(int w) {this.w=w; }
	/*
	 * setter dla h
	 */
	public void set_h(int h) {this.h=h; }


	/*
	 * getter zmiennej velX
	 */
	public int getVelX(){ return velX; }
	/*
	 * getter zmiennej velY 
	 */
	public int getVelY(){ return velY; }
	
	/*
	 * setter zmiennej velX
	 */
	public void  setvelX(int velX){ this.velX=velX; }
	/*
	 * setter zmiennej velY
	 */
	public void  setvelY(int velY){ this.velY=velY; }


}
	