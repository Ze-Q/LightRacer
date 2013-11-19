package gameplay;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Pause {
		
	private JFrame frame = new JFrame();
	private Game game;
	private String successSound = "src/res/sfx/success.wav";
	private AudioInputStream audioInputStream;
	private Clip successClip;
	
	public Pause(Game game) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(successSound).getAbsoluteFile());
			successClip = AudioSystem.getClip();
			successClip.open(audioInputStream);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		this.game = game;
		JButton button = new JButton("Press to Resume");
		button.setSize(100,100);
		button.setFont(new Font("Dialog", 1, 15));
		frame.setLayout(new BorderLayout());
		frame.add(button, BorderLayout.CENTER);
		frame.setSize(200,100);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				playSound(successClip);
				resume();
				frame.setVisible(false);
				frame.dispose();
			}
		});
		frame.setTitle("Paused");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound(successClip);
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
	
    private void playSound(Clip clip) {
		clip.setFramePosition(0);
		clip.start();
		while (clip.getFramePosition() != clip.getFrameLength()) {
			//wait until clip has been played
		}
		clip.stop();
	}
	
}
