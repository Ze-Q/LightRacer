package gameplay;


/**
 * Score class is used to store player 1 and player 2 scores.
 * @author Dzmitry Murzich
 * @version 1.0
 *  */
public class Score {
	
	private int player1Score = 0;
	private int player2Score = 0;
	
	/**
	 * Score class is used to store player 1 and player 2 scores.
	 */
	public Score() {
		
	}

	/**
	 * Increase player 1 score
	 */ 
	public void player1Won() {
		player1Score++;
	}
	
	/**
	 * Increase player 2 score
	 */ 
	public void player2Won() {
		player2Score++;
	}
	
	/**
	 * Return player 1 score
	 * @return Return integer representing player 1 score
	 */ 
	public int getPlayer1Score() {
		return player1Score;
	}
	
	
	/**
	 * Return player 2 score
	 * @return Return integer representing player 2 score
	 */ 
	public int getPlayer2Score() {
		return player2Score;
	}
	
	/**
	 * Set player 1 score to a value
	 * @param score		value to set player 1 score to
	 */
	public void setPlayer1Score(int score) {
		player1Score = score;
	}
	
	
	/**
	 * Set player 2 score to a value
	 * @param score		value to set player 2 score to
	 */
	public void setPlayer2Score(int score) {
		player2Score = score;
	}
	
}
