package gameplay;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Keyboard class handles user input.
 * @author Dzmitry Murzich
 * @version 1.0
 *  */
public class Keyboard implements KeyListener {
	
	
	private boolean[] keys = new boolean[150];
	private boolean hasBeenPaused = false;
	
	/** Specifies if UP key was pressed */
	public boolean up;
		/** Specifies if DOWN key was pressed */
	public boolean down;
	/** Specifies if LEFT key was pressed */
	public boolean left;
	/** Specifies if RIGHT key was pressed */
	public boolean right;
	/** Specifies if W key was pressed */
	public boolean w;
	/** Specifies if S key was pressed */
	public boolean s;
	/** Specifies if A key was pressed */
	public boolean a;
	/** Specifies if D key was pressed */
	public boolean d;
	/** Specifies if P key was pressed */
	public boolean pause;

	/**
	 * Keyboard class handles user input.
	 */
	public Keyboard() {
		
	}
	
	/**
	 * Updates booleans if the corresponding keys were pressed, calls pause if pause key is detected.
	 */
	public void update() {
		up = keys[KeyEvent.VK_UP];
		w = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN];
		s = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT];
		a = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT];
		d = keys[KeyEvent.VK_D];
		if (hasBeenPaused) { //only 1 pause per round allowed
			pause = false;
		}
		else {
			pause = keys[KeyEvent.VK_P];
			if (pause) {
				hasBeenPaused = true;
			}
		}
	}
	
	/**
	 * Update keys array if a key press detected.
	 */
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	/**
	 * Update keys array if a key release detected.
	 */
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	/**
	 * Handle key typed detection (unused).
	 */
	public void keyTyped(KeyEvent e) {}
	
}
