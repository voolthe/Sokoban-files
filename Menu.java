package Sokoban;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Klasa okna g³ównego, zawiera frame, panel, menu, img_loadera
 */
public class Menu extends JFrame
{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Przyciski g³ównego okna - start , koniec, opcje i pomoc
	 */
	public JButton start,end,options,help;
	/**
	 * panel g³ównego okna, jest t³em, obrazkiem.
	 */
	private ImagePanel img_panel;
	
	
	/*
	 * wpisany nick gracza
	 */
	private String player_nick;

	/**
	 * Wybrany przez gracza poziom trudnoœci - domyslnie ³atwy
	 */
	private String difficulty;
	
	/*
	 * Okno gry, ukazuj¹ce siê po klikniêciu start i zatweirdzeniu nicku enterem
	 */
	private Main_Game m_g;
	/** 
	 * Konstruktor
	 */
	public Menu(Main_Game m_g)
	{
		this.m_g=m_g;
	Dimension dime = Toolkit.getDefaultToolkit().getScreenSize();
	setLocation(dime.width/2, dime.height/2);
		
		//panel, frame
		this.setTitle("SOKOBAN");
		img_panel= new ImagePanel("sokoban.jpg");
		img_panel.setLayout(new FlowLayout());
		
		
		
		//przycisk start
		start=new JButton("Start");
		img_panel.add(start);
		start.addActionListener(new StartListener());
	
		
		//przycisk opcje
		options=new JButton("Opcje");
		img_panel.add(options);
		options.addActionListener(new OptionsListener());
		
		//przycisk opcje
		help=new JButton("Pomoc");
		img_panel.add(help);
		help.addActionListener(new HelpListener());
		
		//przycisk koniec
		end=new JButton("Koniec");
		img_panel.add(end);
		end.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			//	m_g.getG_w().dispose();
				m_g.setRunning(false);
				dispose();
				
			}
		});
		
		


		
		player_nick="Player1";
		difficulty="easy";
		
		//ustawienia okna
		
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
            	m_g.setRunning(false);
            	//System.exit(0);
            }
        });
		add(img_panel);
		pack();				
		setVisible(false);
									
		
	}
/**
 * getter zmiennej nick gracza
 * @return nick gracza
 */
	public String getPlayer_nick() {
		return player_nick;
	}

/**
 * setter nicku gracza
 * @param player_nick - nowy nick
 */
	public void setPlayer_nick(String player_nick) {
		this.player_nick = player_nick;
	}

/**
 * getter trudności
 * @return trudnosc gru
 */
	public String getDifficulty() {
		return difficulty;
	}

/**
 * setter trudności 
 * @param difficulty nowa trudność 
 */
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	
	
	//klasy potrzebne w tym oknie

	/**
	 * klasa słuchacza przycisku opcje
	 */
	private class OptionsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		
			
			JFrame f=new JFrame("Opcje");
			options.setEnabled(false);
			f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			f.setLocationRelativeTo(Menu.this);
			f.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent we) {
	            	options.setEnabled(true);
	            }
	        });
		
			
			JPanel p=new JPanel();
			p.setLayout(new FlowLayout());
			
			JTextPane text=new JTextPane();
			text.setText("Wybierz poziom trudnosci:");
			text.setEditable(false);
			p.add(text);
			
			JRadioButton easy= new JRadioButton("£atwy");
			JRadioButton hard= new JRadioButton("Trudny");
			//grupa ¿eby 1 na raz mo¿na by³o zaznaczyæ radio button
			ButtonGroup b_g=new ButtonGroup();
			b_g.add(easy);
			b_g.add(hard);
			
			easy.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					difficulty="easy";
				}
			});
			hard.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					difficulty="hard";
				}
			});
			
			
			p.add(easy);
			p.add(hard);
			f.getContentPane().add(p);
			f.pack();
			f.setVisible(true);
			
			
		}
	}
	
	/**
	 * Klasa odpowiedzielna za wyswietlenie pomocy/ zasad gry po klikniêciu przycisku pomoc
	 */
	private class HelpListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JDialog d= new JDialog();
			d.setTitle("Zasady gry");
			d.setModal(true);
			
			JPanel p=new JPanel();
			p.setLayout(new FlowLayout());
			
			JTextPane text=new JTextPane();
			text.setText("Celem gry jest przesunięcie zielonych skrzynek na szare pola docelowe.\nPoruszasz się czerwoną kulką za pomocą strzałek. \nZbieraj żółte piłkeczki - bonusy/naby uzyskać więcej punktów!");
			text.setEditable(false);
			p.add(text);
			
		
			JButton j_b=new JButton("Ok");
			j_b.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				d.dispose();
				}
			});
			p.add(j_b);
			
			d.setLocationRelativeTo(Menu.this);
			d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			d.add(p);
			d.pack();
			d.setVisible(true);
		}
	}
	
	
	/**
	 * 
	 *Klasa implenetuj¹ca nas³uch klikniêcia przycisku start w tym oknie
	 */
	private class StartListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
	
			
			start.setEnabled(false);
		//	setVisible(false);
		//	m_g.getMenu().setVisible(false);
			Nickname_Frame nick_dialog =new Nickname_Frame();
			
		}
	}
	
	
	/**
	 *Klasa implementuj¹ca okienko dialogowe po naciœniêciu przycisku start
	 */
	private class Nickname_Frame extends JFrame
	{
		  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public String typedText;
		  private JTextField textField;
		  private JPanel panel;
		 
		
		  private String[] button_names = {"Enter","Cancel"};
		  private JButton b_enter,b_cancel;
		 
		 
		  /**
		   * konstruktor okienka dialogowego
		   */
		    public Nickname_Frame() {
		       
		    	Dimension dime = Toolkit.getDefaultToolkit().getScreenSize();
				setLocation(dime.width/2, dime.height/2);
		    addWindowListener(new WindowAdapter() {
		            public void windowClosing(WindowEvent we) {
		            	start.setEnabled(true);
		            	we.getWindow().dispose();
		            }
		        });
			
		            	
		    	
		    	//panel g³ówny + label
		    	panel= new JPanel(new FlowLayout());
		        setTitle("Wprowadzanie nazwy gracza");
		        JLabel jl= new JLabel("Podaj swój nick: ");
		        panel.add(jl);
		        
		        
		        
		        //pole tekstowe do wpisywania nicku 
		       textField = new JTextField(10);
		       textField.addCaretListener(new textFieldListener() );
		       panel.add(textField);
		       
		       //przycisk zatwierdzania nicku 
		       b_enter=new JButton(button_names[0]);
		       b_enter.addActionListener(new b_enterListener());
		       b_enter.setEnabled(false);
		       panel.add(b_enter);
		       
		       
		       //przycisk zamkniêcia okna dilogowego
		       b_cancel=new JButton(button_names[1]);
		       b_cancel.addActionListener(new b_cancelListener());
		       panel.add(b_cancel);
		       
		 
		    //zamkniêcie po wciœniêciu x, dodanie panelu z wszystkimi elementami itd
		        add(panel);
		        Nickname_Frame.this.setVisible(true);
		      pack();
		    }
		 
		    
		    /**
		     * Ns³uch klikniêcia przycisku enter - wprowadzania nicku 
		     */
		    private class b_enterListener implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent e) {
					typedText= textField.getText();
					player_nick=typedText;
					System.out.println(player_nick);
					//nick wpisany - rozpoczęcie gry ! :)
					m_g.setGame_state(Game_State.Game);
					Nickname_Frame.this.dispose();
					//Menu.this.setVisible(false);
							
				}
			}
		    /**
		     * Nas³uch przycisku cansel - zamikniêcie okna
		     */
		    private class b_cancelListener implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent e) {
				
				Nickname_Frame.this.dispose();
				Menu.this.setVisible(true);
				start.setEnabled(true);
				}
			}
		    /**
		     * Nas³uch pola tekstowego - gdy wpiszemy coœ - staje siê to nickiem oraz mozemy zatwierdziæ enterem
		     */
		    private class textFieldListener implements CaretListener{

				@Override
				public void caretUpdate(CaretEvent e) {
					typedText= textField.getText();
					
					if(typedText.isEmpty())
					{
						b_enter.setEnabled(false);
					}
					else 
					{
						b_enter.setEnabled(true);
					}
				}	
		    }
		    
		}

	

}
