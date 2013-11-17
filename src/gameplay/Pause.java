package gameplay;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Pause {
		
	public static final int WIDTH = 300;
	public static final int HEIGHT = WIDTH * 9 / 16 ;
	JFrame frame = new JFrame();
	boolean resume;
	Game game;
	
	public Pause(Game game) {
		this.game = game;
		resume = false;
		JButton button = new JButton("Resume");
		frame.add(button);
		frame.setLayout(new FlowLayout());
		frame.setSize(200,200);
		frame.setResizable(false);
    frame.setTitle("Paused");
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resume();
				frame.setVisible(false);
				frame.dispose();
			}
		});
	}
	
	public void resume() {
		game.resume = true;
		game.key.pause = false;
	}
	
	
	
}
