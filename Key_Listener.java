package Sokoban;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;




public class Key_Listener extends KeyAdapter {

	
	private boolean pause_pressed;
	private Main_Game m_g;
	private Objects_Handler handler;
	private boolean [] key_pressed;
	public Key_Listener(Objects_Handler handler, Main_Game m_g)
	{
		pause_pressed=false;
		this.m_g=m_g;
		this.handler=handler;
		key_pressed=new boolean[4];
		for(int i=0;i<4;i++)
			key_pressed[i]=false;
	}
	
	public void keyPressed(KeyEvent e)
	{
		
		
		int key= e.getKeyCode();
		//System.out.println(key);
		
		
		if(key==80&&pause_pressed==false)
		{
			m_g.setGame_state(Game_State.Pause);
			pause_pressed=true;
			System.out.println("wlaczono pause");
			
		}
		else
		if(key==80&&pause_pressed==true)
		{
			pause_pressed=false;
			m_g.setGame_state(Game_State.Game);
			
			System.out.println("chce koniec pausy  "+ m_g.getGame_state());
		}
		
		
		for(int i=0; i<handler.moving_objects.size(); i++)
		{
			//System.out.println("hi");
			Moving_Object tmp=handler.moving_objects.get(i);
			if(handler.moving_objects.get(i).getType()==Type.Player)
			{
				//moving player after pressing the key 
				if(key==KeyEvent.VK_UP)
				{
					tmp.setvelY(-2);
					key_pressed[0]=true;
				}
				if(key==KeyEvent.VK_DOWN)
				{
					tmp.setvelY(2);
					key_pressed[1]=true;
				}
				if(key==KeyEvent.VK_LEFT)
					{
					tmp.setvelX(-2);
					key_pressed[2]=true; 
					}
				if(key==KeyEvent.VK_RIGHT)
				{
					tmp.setvelX(2);
					key_pressed[3]=true; 
				}		
			}
		}	
		
	}

	public void keyReleased(KeyEvent e)
	{
int key= e.getKeyCode();
		
	
if(key==80)
	m_g.setGame_state(Game_State.Game);
else
		for(int i=0; i<handler.moving_objects.size(); i++)
		{
			Moving_Object tmp=handler.moving_objects.get(i);
			if(tmp.getType()==Type.Player)
			{
				if(key==KeyEvent.VK_UP)  key_pressed[0]=false;//	tmp.setvelY(0);
				if(key==KeyEvent.VK_DOWN) key_pressed[1]=false;	//	tmp.setvelY(0);
				if(key==KeyEvent.VK_LEFT) key_pressed[2]=false;	//tmp.setvelX(0);
				if(key==KeyEvent.VK_RIGHT)  key_pressed[3]=false;//tmp.setvelX(0);
				
				if(key_pressed[0]==false && key_pressed[1]==false)  
					tmp.setvelY(0);
				if(key_pressed[2]==false && key_pressed[3]==false)
					tmp.setvelX(0);
			}
		}
	}
}
