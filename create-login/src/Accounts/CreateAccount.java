package Accounts;

import javax.swing.*;

import java.awt.*; //for layout managers and more
import java.awt.event.*; //for action events
import java.io.IOException;

public class CreateAccount extends JPanel implements ActionListener {

	public final int WIDTH = 900;
	public final int HEIGHT = WIDTH * 9 / 16;
	public final int SCALE = 1;

	protected static final String usernameField = "username";
	protected static final String passwordField = "password";
	protected static final String createAccountString = "Create";

	protected JPasswordField password = new JPasswordField(10);
	protected JTextField username = new JTextField(10);
	protected JLabel actionLabel;

	public CreateAccount() {
		// set window size
		Dimension screenSize = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(screenSize);

		setLayout(new BorderLayout());

		// Create a button
		JButton createAccount = new JButton("Create");
		createAccount.setActionCommand(createAccountString);
		createAccount.addActionListener(this);

		// Create some labels for the fields.
		JLabel textFieldLabel = new JLabel(usernameField + ": ");
		textFieldLabel.setLabelFor(username);
		JLabel passwordFieldLabel = new JLabel(passwordField + ": ");
		passwordFieldLabel.setLabelFor(password);

		// Create a label to put messages during an action event.
		actionLabel = new JLabel("");
		actionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		// Lay out the text controls and the labels.
		JPanel textControlsPane = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		textControlsPane.setLayout(gridbag);

		JLabel[] labels = { textFieldLabel, passwordFieldLabel };
		JTextField[] textFields = { username, password };
		addLabelTextRows(labels, textFields, gridbag, textControlsPane);

		c.gridwidth = GridBagConstraints.REMAINDER; // last
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 1.0;
		textControlsPane.add(actionLabel, c);
		textControlsPane.add(createAccount);
		textControlsPane.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Create Account"),
				BorderFactory.createEmptyBorder(25, 25, 25, 25)));

		// Put everything together.
		JPanel leftPane = new JPanel(new BorderLayout());
		leftPane.add(textControlsPane, BorderLayout.PAGE_START);
		add(leftPane, BorderLayout.CENTER);

	}

	private void addLabelTextRows(JLabel[] labels, JTextField[] textFields,
			GridBagLayout gridbag, Container container) {
			GridBagConstraints c = new GridBagConstraints();
			c.anchor = GridBagConstraints.WEST;
			int numLabels = labels.length;

			for (int i = 0; i < numLabels; i++) {
				c.gridwidth = GridBagConstraints.RELATIVE; // next-to-last
				c.fill = GridBagConstraints.NONE; // reset to default
				c.weightx = 0; // reset to default
				container.add(labels[i], c);

				c.gridwidth = GridBagConstraints.REMAINDER; // end row
				//c.fill = GridBagConstraints.HORIZONTAL;
				c.weightx = 0.0;
				container.add(textFields[i], c);
			}
		}

	public void actionPerformed(ActionEvent e) {
		if (createAccountString.equals(e.getActionCommand())) {
			String enteredUsername = username.getText();
			String enteredPassword = new String(password.getPassword());
			actionLabel.setText(generateErrorMessage(enteredUsername,enteredPassword));
		}
	}
	
	public String generateErrorMessage(String username, String password){
		String result = "";
		boolean isValidUsername = CRUD.CSVHandler.isValidUsername(username);
		boolean isValidPassword = CRUD.CSVHandler.isValidPassword(password);
		boolean isExist = true;
		try {
			isExist = CRUD.CSVHandler.isExist(username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isValidUsername && isValidPassword && !isExist){
			CRUD.CSVHandler.addUser(username, password);
			return result = "Your account has been created.";
		}
		if(isExist) return result = "username has been taken";
		if(!isValidUsername) result = "username must contain at least 8 alphanumeric characters.<br>";
		if(!isValidPassword) result = result + "password need to contain ...<br>"; 
		result = "<html>" + result + "</html>";
		return result;
	}
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		
		// Create and set up the window.
		JFrame frame = new JFrame("Create Account Prototype");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new CreateAccount());
		
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatching thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}
