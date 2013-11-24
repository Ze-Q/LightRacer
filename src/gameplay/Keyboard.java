package gameplay;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Author Dzmitry Murzich
 *  */
public class Keyboard implements KeyListener {
	
	
	private boolean[] keys = new boolean[150];
	private boolean hasBeenPaused = false;
	
	public boolean up, down, left, right, w, s, a, d, pause;

	/**
	 * Keyboard class handles user input
	 */
	public Keyboard() {
		
	}
	
	/**
	 * Updates booleans if the corresponding keys were pressed, calls pause if pause key is detected
	 * @see Pause
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
	 * Update keys array if a key press detected
	 */
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	/**
	 * Update keys array if a key release detected
	 */
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	/**
	 * Handle key typed detection (unused)
	 */
	public void keyTyped(KeyEvent e) {}
	
}
