package gameplay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

import GUI.Sound;

/**
 * Pause class handles pause logic.
 * @author Dzmitry Murzich
 * @version 1.0
 *  */
public class Pause {
		
	private JFrame frame = new JFrame();
	private Game game;
	private Sound sound = new Sound();
	
	/**
	 * Pause class handles pause logic.
	 * @param game	 Game object that contains currently running game, required to resume game run loop.
	 */
	public Pause(Game game) {
		this.game = game;
		JButton button = new JButton("Press to Resume");
		button.setSize(100,100);
		button.setForeground(Color.WHITE);
		button.setBackground(Color.DARK_GRAY);
		button.setOpaque(true);
		button.setBorderPainted(false);
		button.setFont(new Font("Dialog", 1, 15));
		frame.setLayout(new BorderLayout());
		frame.add(button, BorderLayout.CENTER);
		frame.setSize(200,100);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//check if window is being closed
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				sound.playSound(sound.successClip);
				resume();
				frame.setVisible(false);
				frame.dispose();
			}
		});
		frame.setTitle("Paused");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		//check if button was clicked
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playSound(sound.successClip);
				resume();
				frame.setVisible(false);
				frame.dispose();
			}
		});
	}
	
	private void resume() {
		game.resume = true;
		game.key.pause = false;
	}
	

}
