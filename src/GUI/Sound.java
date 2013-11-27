package GUI;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Sound class contains all the music Clips which will be used in the program.
 * @author Dzmitry Murzich
 * @version 1.0
 */
public class Sound {

	private final String GREENSOUND = "./res/raw/greenround.wav";
	private final String REDSOUND = "./res/raw/redround.wav";
    private final String BLUESOUND = "./res/raw/blueround.wav";
    private final String YELLOWSOUND = "./res/raw/yellowround.wav";
    private final String COUNTDOWNSOUND = "./res/raw/countdown.wav";
    private final String GREENWONSOUND = "./res/raw/greenwon.wav";
    private final String REDWONSOUND = "./res/raw/redwon.wav";
    private final String BLUEWONSOUND = "./res/raw/bluewon.wav";
    private final String YELLOWWONSOUND = "./res/raw/yellowwon.wav";
    private final String DRAWSOUND = "./res/raw/draw.wav";
	private final String INGAMESOUND = "./res/raw/ingame.wav";
	private final String SUCCESSSOUND = "./res/raw/success.wav";
	private final String ERRORSOUND = "./res/raw/error.wav";
	private final String BACKSOUND = "./res/raw/back.wav";
	private final String GAMESOUND = "./res/raw/startgame.wav";
	private final String BACKGROUNDSOUND = "./res/raw/background.wav";
	/** Clip object that stores green.wav. */
	public Clip greenClip;
	/** Clip object that stores red.wav. */
	public Clip redClip;
	/** Clip object that stores blue.wav. */
	public Clip blueClip;
	/** Clip object that stores yellow.wav. */
	public Clip yellowClip;
	/** Clip object that countdown.wav */
	public Clip countdownClip;
	/** Clip object that stores greenwon.wav */
	public Clip greenWonClip;
	/** Clip object that stores redwon.wav. */
	public Clip redWonClip;
	/** Clip object that stores bluewon.wav. */
	public Clip blueWonClip;
	/** Clip object that stores yellowwon.wav. */
	public Clip yellowWonClip;
	/** Clip object that stores draw.wav. */
	public Clip drawClip;
	/** Clip object that stores ingame.wav. */
	public Clip ingameClip;
	/** Clip object that stores success.wav. */
	public Clip successClip;
	/** Clip object that stores error.wav. */
	public Clip errorClip;
	/** Clip object that stores back.wav. */
	public Clip backClip;
	/** Clip object that stores startgame.wav. */
	public Clip gameClip;
	/** Clip object that stores background.wav. */
	public Clip backgroundClip;

	private AudioInputStream audioInputStream;
	
    /**
     * Sound class contains all the music Clips which will be used in the program.
     */
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
	
    /**
     * This method plays a clip and pauses the program execution until clip has finished playing.
     * @param clip			Clip object that will be played.
     */
    public void playSound(Clip clip) {
		clip.setFramePosition(0);
		clip.start();
		while (clip.getFramePosition() != clip.getFrameLength()) {
			//wait until clip has been played
		}
		clip.stop();
	}
    
    /**
     * This method plays a clip at the end of each round announcing the winner.
     * @param winner		String which contains the winner of the round.
     */
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
    
    /**
     * This method plays a clip at the end of each match announcing the winner.
     * @param winner		String which contains the winner of the match.
     */
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
