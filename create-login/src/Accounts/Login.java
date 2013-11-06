package Accounts;

import javax.swing.*;
import java.awt.*; //for layout managers and more
import java.awt.event.*; //for action events
import java.io.IOException;

public class Login extends JPanel implements ActionListener {

	public final int WIDTH = 900;
	public final int HEIGHT = WIDTH * 9 / 16;
	public final int SCALE = 1;

	protected static final String usernameField = "username";
	protected static final String passwordField = "password";
	protected static final String loginString = "Login";

	protected JPasswordField password = new JPasswordField(10);
	protected JTextField username = new JTextField(10);
	protected JLabel actionLabel;

	public Login() {
		// set window size
		Dimension screenSize = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(screenSize);

		setLayout(new BorderLayout());

		// Create a button
		JButton loginButton = new JButton("Login");
		loginButton.setActionCommand(loginString);
		loginButton.addActionListener(this);

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
		textControlsPane.add(loginButton);
		textControlsPane.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Login"),
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
		boolean isValid =false;
		if (loginString.equals(e.getActionCommand())) {
			String enteredUsername = username.getText();
			String enteredPassword = new String(password.getPassword());
			try {
				isValid = CRUD.CSVHandler.isValidCredential(enteredUsername, enteredPassword);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(isValid){
				actionLabel.setText(enteredUsername + ", you are logged in.");
			}
			else{
				actionLabel.setText("username/password cannot be identified");
			}
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		
		// Create and set up the window.
		JFrame frame = new JFrame("Login Prototype");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new Login());
		
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
