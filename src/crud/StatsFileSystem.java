package crud;
import java.io.*;

/**
 * StatsFileSystem contains methods to interact with the CSV file which contains the stats info.
 * @author Ze
 *	@version 1.0
 */
public class StatsFileSystem {

	private static final StatsFileSystem INSTANCE = new StatsFileSystem();
	
	private int maxNumOfUsers = 100;
	
	private String statsArray[][];
	
	private StatsFileSystem() {
		readStatsFromFile();
	}
	
	/**
	 * The getter for singleton design, it makes sure that there is only one instance of this object.
	 * @return return the only instance of the StatsFileSystem object
	 */
	public static StatsFileSystem getInstance() { 
        return INSTANCE;
    }
	

	private void generateNewStatsArray(int size) {
		statsArray = new String[size][size];
		statsArray[0][0]="0";
	}
	
	/**
	 * Return the stats array contained in the StatsFileSystem object
	 * @return return the stats array, which contains the versus records of all players
	 */
	public String[][] getStatsArray() {
		return statsArray;
	}
	
	/**
	 * This method sets a new maximum number of users. 
	 * <p>
	 * It means that the stats array will have this number as the new size.
	 * This method is currently not used, it is here to allow the future developers to 
	 * increase the amount of users this program can hold.
	 * The default maximum number of users is currently 100.
	 * @param newMaxNumOfUsers Takes in an int to set the new maximum of users.
	 */
	public void setMaxNumOfUsers(int newMaxNumOfUsers) {
		maxNumOfUsers=newMaxNumOfUsers;
	}
	
	/**
	 * print the array to stdout, for testing purpose only
	 */
	public void printStatsArray() {
		for(int row=0; row<maxNumOfUsers; row++){
			for(int column=0; column<maxNumOfUsers; column++){
				if(statsArray[row][column]!=null){
					System.out.print(statsArray[row][column]+" | ");
				}
				else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * This method is used to read the stats data from file.
	 * @return return true if success, false if fail.
	 */
	public boolean readStatsFromFile() {
		try{
			generateNewStatsArray(maxNumOfUsers);
			File dir = new File ("./res/data");
			File statsCSV = new File(dir, "stats.csv");
		
			//check whether userCSV already exists, if not then do not read anything
			if(!statsCSV.exists()){
				return false;
			}
			
			@SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(new FileReader(statsCSV));
	        
			String oneLine;
			int row = 0;
			
	        while((oneLine = bufferedReader.readLine()) != null) {
	             String [] temporary = oneLine.split(",");
	             readOneLine(temporary, row);
	             row++;
	        }
			return true;
			
		}catch(IOException e){
    		e.printStackTrace();
    		return false;
    	}
		
	}
	
	private void readOneLine(String[] inputs, int row) {
		int numberOfinputs = inputs.length;
		for(int column = 0; column<numberOfinputs; column++) {
			statsArray[row][column]=inputs[column];
		}
	}
	
	/**
	 * Write the information currently contained in the stats array to the file on hard drive. 
	 * @return return true if success, false if fail.
	 */
	public boolean writeStatsToFile() {
		try{
			
			File dir = new File ("./res/data");
			File statsCSV = new File(dir, "stats.csv");
		
			//check whether userCSV already exists, if not then create userCSV.csv
			if(!statsCSV.exists()){
				statsCSV.createNewFile();
			}
			
			
			FileWriter fileWriter = new FileWriter(statsCSV,false);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			for(int row=0; row<statsArray.length; row++){
				for(int column=0; column<statsArray.length; column++){
					if(statsArray[row][column]!=null){
						printWriter.print(statsArray[row][column]);
					}
					if(column<statsArray.length-1){
						if(statsArray[row][column+1]!=null){
							printWriter.print(",");
						}
					}
				}
				if(row<statsArray.length-1){
					if(statsArray[row+1][0]!=null){
						printWriter.print("\n");	
					}
				}
			}
			
			printWriter.flush();
			printWriter.close();
			fileWriter.close();
			
			return true;
			
		}catch(IOException e){
    		e.printStackTrace();
    		return false;
    	}
		
	}
	
	/**
	 * This method looks for the number of times that user1 won against user2.
	 * @param user1 A string which contains the username of user1.
	 * @param user2 A string which contains the username of user2.
	 * @return return the number of wins as a string
	 */
	public String searchForVersus (String user1, String user2) {
		int rowPosition = 0;
		int columnPosition = 0;
		
		for(int row = 1; row<statsArray.length; row++) {
			if(statsArray[row][0].equals(user1)){
				rowPosition=row;
				break;
			}
		}
		
		for(int column=1; column<statsArray.length; column++) {
			if(statsArray[0][column].equals(user2)) {
				columnPosition=column;
				break;
			}
		}
		return statsArray[rowPosition][columnPosition];
	}
	
	/**
	 * This method updates the versus records of two users user1 and user2 in the stats array.
	 * @param user1 a String that is the username of user1.
	 * @param user1Wins the number of times that user1 won against user2.
	 * @param user2 a String that is the username of user2.
	 * @param user2Wins the number of times that user2 won against user1.
	 */
	public void updateRecords (String user1, int user1Wins, String user2, int user2Wins) {
		int rowPosition;
		int columnPosition;
		
		//look for the spot to write user1's wins against user2
		rowPosition = findRow (user1);
		columnPosition = findColumn (user2);
		statsArray[rowPosition][columnPosition] = String.valueOf(user1Wins);
		
		//look for the spot to write user2's wins against user1
		rowPosition = findRow (user2);
		columnPosition = findColumn (user1);
		statsArray[rowPosition][columnPosition] = String.valueOf(user2Wins);
		
	}
	
	private int findRow (String user) {
		int rowPosition=0;
		for(int row = 1; row<statsArray.length; row++) {
			if(statsArray[row][0].equals(user)){
				rowPosition=row;
				break;
			}
		}
		return rowPosition;
	}
	
	private int findColumn (String user) {
		int columnPosition=0;
		for(int column=1; column<statsArray.length; column++) {
			if(statsArray[0][column].equals(user)) {
				columnPosition=column;
				break;
			}
		}
		return columnPosition;
	}
	
	/**
	 * Add a new user to the stats array, with the corresponding 0s 
	 * since this player has not played any game yet.
	 * @param username this method takes in a string that is the username of the new user.
	 */
	public void addNewUserToArray (String username) {
		int rowPosition=0;
		for(int row = 1; row<statsArray.length; row++) {
			if(statsArray[row][0]==null){
				rowPosition=row;
				break;
			}
		}
		statsArray[rowPosition][0] = username;
		
		int columnPosition=0;
		for(int column=1; column<statsArray.length; column++) {
			if(statsArray[0][column]==null) {
				columnPosition=column;
				break;
			}
		}
		statsArray[0][columnPosition] = username;
		
		for (int column = 1; column<= columnPosition; column++) {
			statsArray[rowPosition][column]="0";
		}
		
		for (int row = 1; row<= rowPosition; row++) {
			statsArray[row][columnPosition]="0";
		}
		
	}
}
