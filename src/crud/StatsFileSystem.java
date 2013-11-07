package crud;
import java.io.*;

public class StatsFileSystem {
	
	public static String statsArray[][];
	
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
}
