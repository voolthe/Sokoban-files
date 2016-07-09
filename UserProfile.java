package Sokoban;

/**
 * klasa przechowuj¹ca nick gracza oraz jego wynik 
 * @author Patryk
 *
 */
public class UserProfile {
	
	
	/**
	 * nick 
	 */
	private String nick;
	/**
	 * wynik 
	 */
	private int score;
	/**
	 * konstruktor klasy UserProfile
	 * @param nick - nick gracza 
	 * @param score - wynik gracza 
	 */
	public UserProfile(String nick, int score)
	{
		this.nick=nick;
		this.score=score;
	}

	/**
	 * getter nazwy gracza 
	 * @return nick 
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * setter nazwy gracza 
	 * @param nick - nowa nazwa gracza 
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}
/**
 * gettter wyniku gracza
 * @return score 
 */
	public int getScore() {
		return score;
	}

	/**
	 * setter wyniku gracza
	 * @param score - nowy  wynik 
	 */
	public void setScore(int score) {
		this.score = score;
	}
	

}
