package crud;
import java.io.*;

public class CSVHandler {
	public static void main(String[]args){
		String username = "cheng";
		String password = "123Aa5555-";
		if (validateCredential (username,password)) addUser(username,password);
		else System.out.println("cannot added user");
		try {
			if(isValidCredential(username, password)) System.out.println("valid");
			else System.out.println("invalid");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//Login
	public static boolean isValidCredential (String username, String password) throws IOException{
		File userCSV = new File("userCSV.csv");
		if(!userCSV.exists()){
			return false;
		}
		BufferedReader br = new BufferedReader(new FileReader(userCSV));
        String line;
        while((line = br.readLine()) != null) {
             String [] credentials = line.split(",");
             String thisUsername = credentials[0];
             String thisPassword = credentials[1];
             boolean checkUsername = thisUsername.equals(username);
             boolean checkPassword = thisPassword.equals(password);
             if(thisUsername.equals(username) && thisPassword.equals(password)) return true;
        }
        return false;
	}
	

//CreateAccount
	public static boolean isExist (String username) throws IOException{
		File userCSV = new File("userCSV.csv");
		if(!userCSV.exists()){
			return false;
		}
		BufferedReader br = new BufferedReader(new FileReader(userCSV));
        String line;
        while((line = br.readLine()) != null) {
             String [] credentials = line.split(",");
             String thisUsername = credentials[0];
             if(thisUsername.equals(username)) return true;
        }
        return false;
	}
	
	public static void addUser(String username, String password){
		try{
    		String data = username + "," + password;
 
    		File userCSV = new File("userCSV.csv");
 
    		//check whether userCSV already exists, if not then create userCSV.csv
    		if(!userCSV.exists()){
    			userCSV.createNewFile();
    		}
 
    		FileWriter fileWritter = new FileWriter(userCSV.getName(),true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.write(data);
    	        bufferWritter.newLine();
    	        bufferWritter.close();
 
    	}catch(IOException e){
    		e.printStackTrace();
    	}
	}
	
	public static boolean validateCredential(String username, String password){
		boolean isValidUsername = isValidUsername(username);
		boolean isValidPassword = isValidPassword(password);
		boolean isExist = false;
		try {
			isExist = isExist(username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (isValidUsername && isValidPassword && !isExist);
	}
	
	public static boolean isValidUsername(String username){
		//regex which check for alphanumeric characters
		String matchThis = "^[a-zA-Z0-9]{1,}$";
		
		return(username.matches(matchThis));
	}
	
	// TODO: 
	//put minimum passowrdLength in a global class
	//using try catch and generate exception to show the user what is missing.
	public static boolean isValidPassword(String password){
		boolean isValid = false;
		String nonAlpha = ".*\\W+.*";
		int length = password.length();
		boolean lower,upper,digit,nonAlphanumeric;
		lower = upper = digit = nonAlphanumeric = false;
		nonAlphanumeric = password.matches(nonAlpha);
		if(length < 8){
			return false;
		}
		for (int i = 0; i < length; i++){
			char c = password.charAt(i);
			if ('0'<=c && c<='9') digit = true;
			if ('a'<=c && c<='z') lower = true;
			if ('A'<=c && c<='Z') upper = true;
		}
		isValid = (lower && upper && digit && nonAlphanumeric);
		return isValid;
	}
	
}
