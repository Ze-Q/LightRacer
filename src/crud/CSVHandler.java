package crud;

import java.io.*;

public class CSVHandler {
	private static final CSVHandler INSTANCE = new CSVHandler();
	
	private CSVHandler() {
		
	}
	public static CSVHandler getInstance() { 
        return INSTANCE;
    }
	// Login
	public static boolean isValidCredential(String username, String password)
			throws IOException {
		File dir = new File("./res");
		File userCSV = new File(dir, "userCSV.csv");
		if (!userCSV.exists()) {
			return false;
		}

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(userCSV));
		String line;
		while ((line = br.readLine()) != null) {
			String[] credentials = line.split(",");
			String thisUsername = credentials[0];
			String thisPassword = credentials[1];
			boolean checkUsername = thisUsername.equals(username);
			boolean checkPassword = thisPassword.equals(password);
			if (checkUsername && checkPassword)
				return true;
		}
		return false;
	}

	// CreateAccount
	public static boolean isExist(String username) throws IOException {
		File dir = new File("./res");
		File userCSV = new File(dir, "userCSV.csv");
		if (!userCSV.exists()) {
			return false;
		}
		
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(userCSV));
		String line;
		while ((line = br.readLine()) != null) {
			String[] credentials = line.split(",");
			String thisUsername = credentials[0];
			if (thisUsername.equals(username))
				return true;
		}
		return false;
		
	}

	public static boolean addUser(String username, String password) {
		String data = username + "," + password;
		try {

			File dir = new File("./res");
			File userCSV = new File(dir, "userCSV.csv");

			// check whether userCSV already exists, if not then create
			// userCSV.csv
			if (!userCSV.exists()) {
				userCSV.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(userCSV, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.print(data);
			printWriter.print("\r\n");
			

			printWriter.flush();
			printWriter.close();
			fileWriter.close();

			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	public static boolean validateCredential(String username, String password) {
		boolean isValidUsername = (isValidUsername(username).length() == 0);
		boolean isValidPassword = (isValidPassword(password).length() == 0);
		boolean isExist = false;
		try {
			isExist = isExist(username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (isValidUsername && isValidPassword && !isExist);
	}

	public static String isValidUsername(String username) {
		String message = "";
		// regex which check for alphanumeric characters
		String matchThis = "^[a-zA-Z0-9]{1,}$";
		if (!username.matches(matchThis)) {
			message = "Username must only contains alphanumeric characters.<br>";
		}
		return message;
	}

	public static String isValidPassword(String password) {
		String message = "";
		String nonAlpha = ".*\\W+.*";
		int length = password.length();
		boolean lower, upper, digit, nonAlphanumeric;
		lower = upper = digit = nonAlphanumeric = false;
		nonAlphanumeric = password.matches(nonAlpha);
		if (length < 8) {
			message = message + "Password minimum length is 8.<br>";
		}
		for (int i = 0; i < length; i++) {
			char c = password.charAt(i);
			if ('0' <= c && c <= '9')
				digit = true;
			if ('a' <= c && c <= 'z')
				lower = true;
			if ('A' <= c && c <= 'Z')
				upper = true;
		}
		if (!digit)
			message = message + "Password must contain at least one digit.<br>";
		if (!lower)
			message = message
					+ "Password must contain at least one lowercase character.<br>";
		if (!upper)
			message = message
					+ "Password must contain at least one uppercase character.<br>";
		if (!nonAlphanumeric)
			message = message
					+ "Password must contain at least one non-alphanumeric character.<br>";
		return message;
	}

}
