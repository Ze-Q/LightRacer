package GUI;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound {

	
	public final String GREENSOUND = "./res/sfx/greenround.wav";
	public final String REDSOUND = "./res/sfx/redround.wav";
    public final String BLUESOUND = "./res/sfx/blueround.wav";
    public final String YELLOWSOUND = "./res/sfx/yellowround.wav";
    public final String COUNTDOWNSOUND = "./res/sfx/countdown.wav";
    public final String GREENWONSOUND = "./res/sfx/greenwon.wav";
    public final String REDWONSOUND = "./res/sfx/redwon.wav";
    public final String BLUEWONSOUND = "./res/sfx/bluewon.wav";
    public final String YELLOWWONSOUND = "./res/sfx/yellowwon.wav";
    public final String DRAWSOUND = "./res/sfx/draw.wav";
	public final String INGAMESOUND = "./res/sfx/ingame.wav";
	public final String SUCCESSSOUND = "./res/sfx/success.wav";
	public final String ERRORSOUND = "./res/sfx/error.wav";
	public final String BACKSOUND = "./res/sfx/back.wav";
	public final String GAMESOUND = "./res/sfx/startgame.wav";
	public final String BACKGROUNDSOUND = "./res/sfx/background.wav";
	public Clip greenClip;
	public Clip redClip;
	public Clip blueClip;
	public Clip yellowClip;
	public Clip countdownClip;
	public Clip greenWonClip;
	public Clip redWonClip;
	public Clip blueWonClip;
	public Clip yellowWonClip;
	public Clip drawClip;
	public Clip ingameClip;
	public Clip successClip;
	public Clip errorClip;
	public Clip backClip;
	public Clip gameClip;
	public static Clip backgroundClip;

	private AudioInputStream audioInputStream;
	
	public Sound() {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(GREENSOUND));
			greenClip = AudioSystem.getClip();
			greenClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(REDSOUND));
			redClip = AudioSystem.getClip();
			redClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(BLUESOUND));
			blueClip = AudioSystem.getClip();
			blueClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(YELLOWSOUND));
			yellowClip = AudioSystem.getClip();
			yellowClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(COUNTDOWNSOUND));
			countdownClip = AudioSystem.getClip();
			countdownClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(GREENWONSOUND));
			greenWonClip = AudioSystem.getClip();
			greenWonClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(REDWONSOUND));
			redWonClip = AudioSystem.getClip();
			redWonClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(BLUEWONSOUND));
			blueWonClip = AudioSystem.getClip();
			blueWonClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(YELLOWWONSOUND));
			yellowWonClip = AudioSystem.getClip();
			yellowWonClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(DRAWSOUND));
			drawClip = AudioSystem.getClip();
			drawClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(INGAMESOUND));
			ingameClip = AudioSystem.getClip();
			ingameClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(SUCCESSSOUND));
			successClip = AudioSystem.getClip();
			successClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(ERRORSOUND));
			errorClip = AudioSystem.getClip();
			errorClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(BACKSOUND));
			backClip = AudioSystem.getClip();
			backClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(GAMESOUND));
			gameClip = AudioSystem.getClip();
			gameClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File(BACKGROUNDSOUND));
			backgroundClip = AudioSystem.getClip();
			backgroundClip.open(audioInputStream);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
    public void playSound(Clip clip) {
		clip.setFramePosition(0);
		clip.start();
		while (clip.getFramePosition() != clip.getFrameLength()) {
			//wait until clip has been played
		}
		clip.stop();
	}
    
    public void announceRoundWinner(String winner) {
    	if (winner.equals("Red")) {
    		playSound(redClip);
    	}
    	else if (winner.equals("Blue")) {
    		playSound(blueClip);
    	}
    	else if (winner.equals("Green")) {
    		playSound(greenClip);
    	}
    	else if (winner.equals("Yellow")) {
    		playSound(yellowClip);
    	}
    	else {
    		playSound(drawClip);
    	}
    }
    
    public void announceGameWinner(String winner) {
    	if (winner.equals("Red")) {
    		playSound(redWonClip);
    	}
    	else if (winner.equals("Blue")) {
    		playSound(blueWonClip);
    	}
    	else if (winner.equals("Green")) {
    		playSound(greenWonClip);
    	}
    	else if (winner.equals("Yellow")) {
    		playSound(yellowWonClip);
    	}
    }
	
}
