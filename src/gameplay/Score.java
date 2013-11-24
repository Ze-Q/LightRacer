package gameplay;


/**
 * Author Dzmitry Murzich
 *  */
public class Score {
	
	public int p1 = 0;
	public int p2 = 0;
	
	/** 
	* Score class is used to store player 1 and player 2 scores
	* @param p1 	integer representing player 1 score
	* @param p2 	integer representing player 2 score
	*/
	public Score() {
		
	}

	/**
	 * Increase player 1 score
	 */ 
	public void p1Won() {
		p1++;
	}
	
	/**
	 * Increase player 2 score
	 */ 
	public void p2Won() {
		p2++;
	}
	
	/**
	 * Return player 1 score
	 * @return Return integer representing player 1 score
	 */ 
	public int getP1() {
		return p1;
	}
	
	
	/**
	 * Return player 2 score
	 * @return Return integer representing player 2 score
	 */ 
	public int getP2() {
		return p2;
	}
	
}
