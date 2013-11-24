package crud;
import java.io.*;

/**
 * 
 * @author Ze
 *
 */
public class StatsFileSystem {
	
	/**
	 * Singleton design, the only instance of this StatsFileSystem object
	 */
	private static final StatsFileSystem INSTANCE = new StatsFileSystem();
	
	/**
	 * The maximum number of users is defined to be 50 in this case, but it can be changed easily if we need to have more users.
	 */
	private int maxNumOfUsers = 50;
	
	/**
	 * This private array stores all the stats information in the memory
	 */
	private String statsArray[][];
	
	/**
	 * Private constructor for the singleton design, Read from the stats file on the disk
	 */
	private StatsFileSystem() {
		readStatsFromFile();
	}
	
	/**
	 * The getter for singleton design, make sure that there is only one instance of this object.
	 * @return return the only instance of the object
	 */
	public static StatsFileSystem getInstance() { 
        return INSTANCE;
    }
	
	/**
	 * This method generates a new stats array with a '0' at [0][0]
	 * @param size take in a variable size which determines the size of the stats array
	 */
	public void generateNewStatsArray(int size) {
		statsArray = new String[size][size];
		statsArray[0][0]="0";
	}
	
	/**
	 * Return the stats array
	 * @return return the stats array object
	 */
	public String[][] getStatsArray() {
		return statsArray;
	}
	
	public void setMaxNumOfUsers(int newMaxNumOfUsers) {
		maxNumOfUsers=newMaxNumOfUsers;
	}
	
	//print the array to stdout, for testing purpose only
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
	
	public boolean readStatsFromFile() {
		try{
			generateNewStatsArray(maxNumOfUsers);
			File dir = new File ("./res");
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
	
	public boolean writeStatsToFile() {
		try{
			
			File dir = new File ("./res");
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
