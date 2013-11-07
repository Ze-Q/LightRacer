package crud;
import java.io.*;

public class StatsFileSystem {
	
	public static String statsArray[][];
	public static boolean alreadyRead=false;
	
	public static void generateNewStatsArray(int size) {
		statsArray = new String[size][size];
		statsArray[0][0]="0";
	}
	
	//print the array to stdout, for testing purpose only
	public static void printStatsArray(int limit) {
		for(int row=0; row<limit; row++){
			for(int column=0; column<limit; column++){
				if(statsArray[row][column]!=null){
					System.out.print(statsArray[row][column]);
				}
				else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	
	public static boolean readStatsFromFile(int numberOfUsers) {
		try{
			generateNewStatsArray(numberOfUsers+20);
			File dir = new File ("./res");
			File statsCSV = new File(dir, "stats.csv");
		
			//check whether userCSV already exists, if not then create userCSV.csv
			if(!statsCSV.exists()){
				return false;
			}
			BufferedReader bufferedReader = new BufferedReader(new FileReader(statsCSV));
	        
			String oneLine;
			int row = 0;
			
	        while((oneLine = bufferedReader.readLine()) != null) {
	             String [] temporary = oneLine.split(",");
	             readOneLine(temporary, row);
	             row++;
	        }
	        alreadyRead=true;
			return true;
			
		}catch(IOException e){
    		e.printStackTrace();
    		return false;
    	}
		
	}
	
	private static void readOneLine(String[] inputs, int row) {
		int numberOfinputs = inputs.length;
		for(int column = 0; column<numberOfinputs; column++) {
			statsArray[row][column]=inputs[column];
		}
	}
	
	public static boolean writeStatsToFile() {
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
	
	public static String searchForVersus (String user1, String user2) {
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
	
	public static void updateRecords (String user1, int user1Wins, String user2, int user2Wins) {
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
	
	private static int findRow (String user) {
		int rowPosition=0;
		for(int row = 1; row<statsArray.length; row++) {
			if(statsArray[row][0].equals(user)){
				rowPosition=row;
				break;
			}
		}
		return rowPosition;
	}
	
	private static int findColumn (String user) {
		int columnPosition=0;
		for(int column=1; column<statsArray.length; column++) {
			if(statsArray[0][column].equals(user)) {
				columnPosition=column;
				break;
			}
		}
		return columnPosition;
	}
	
	public static void addNewUserToArray (String username) {
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
