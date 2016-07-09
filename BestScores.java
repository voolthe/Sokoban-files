package Sokoban;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * klasa zarz¹dzaj¹ca najlepszymi wynikami
 * @author Patryk 
 *
 */
public class BestScores {

	
	/**
	 * lista graczy 
	 */
	private LinkedList<UserProfile> players;
	
	//plik z najlepszymi wynikami 
	File file;
	
	
	/**
	 * konstruktor klasy best scores 
	 */
	public BestScores()
	{
		file=new File("best_scores.txt");
		players=new LinkedList<UserProfile>();
		
		//wczytanie pliku z wynikami , któy ju¿ istniej 
		if(file.isFile())
		{
			try {
				loadScores();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * funkcja dodaj¹ca gracza do listy najlepszych
	 * @param player - nowy profil gracza 
	 */
	public void  addPlayer(UserProfile player)
	{
		// nie ma graczy 10 - wstawienie 
		if(players.size()<=10)
		{
			
		//wstawienie 
		players.add(player);
		sortPlayers();
		}
		//lista pe³na, dodanie jesli lepszy niz 10 gracz
		else
		{
			if(isTop10(player))
			{
				//wstawienie 
				players.add(player);
				sortPlayers();
			}
		}
	}
	
	
	private void loadScores() throws IOException
	{
		String[] parts;
		String line = "";
		
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		
		// parsowanie pocz¹tkowych linii
		while(((line=br.readLine())!=null))
		{
		parts = line.split(" ");
		UserProfile tmp_player=new UserProfile(parts[0], Integer.parseInt(parts[1]));
		players.add(tmp_player);
		
		}
		br.close();
		
	}
	
	
	/**
	 * zapisuje listê najlepszych do pliku 
	 * @throws FileNotFoundException 
	 */
	public void writeFile() throws FileNotFoundException
	{
		
		file.delete();
		PrintWriter pr= new PrintWriter("best_scores.txt");
		//zapis wszystkich graczy do pliku 
		
		
		for (int i=0; i< players.size(); i++)
		{
			pr.println(players.get(i).getNick()+ " "+ players.get(i).getScore());
		}
		pr.close();
	}
	
	/**
	 * sprawdza czy gracz ma wynik nale¿¹cy do 10  najlepszych
	 * @param player - sprawdzany gracz
	 * @return czy nale¿y do 10 najlepszych wyników
	 */
	public boolean isTop10(UserProfile player)
	{
		boolean isTop10=false;
		
		//jako ¿e lista jest posortowana, mo¿na sprawdziæ czy wiêkszy wynik 
		//od ostatniego gracza, tj o indeksie 9 (10 -tego ) 
		
		if(player.getScore()>players.getLast().getScore());
		isTop10=true;
		
		return isTop10;
		
	}
	
	/**
	 * funkcja dodaj¹ca gracza i tworz¹ca plik, po zakoñczeniu obecnej gry
	 * @param player - dodawany gracz
	 */
	public void updateScores(UserProfile player)
	{
		addPlayer(player);
		try {
			writeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	private void sortPlayers()
	{
		//sortowanie listy, 
		Collections.sort(players, new Comparator<UserProfile>()
		{
			public int compare(UserProfile o1, UserProfile o2) 
			{
				return Collator.getInstance().compare(o1.getScore(), o2.getScore());
			}
		});
		
		//odwrocenie, ¿eby max wynik na pocz¹tku 
		Collections.reverse(players);		
	}
	
}
