package hw2;

import java.util.Random;

/**
 * @author Camden
 *
 */
public class Padlock {
	
	public static final int TOOTH = 2;
	
	private boolean open = true;
	
	private int pos1 = 2 * TOOTH;
	private int pos2 = TOOTH;
	private int pos3 = 0;
	
	private int key1;
	private int key2;
	private int key3;
	
	public Padlock (int n1, int n2, int n3) {
		key1 = n1;
		key2 = n2;
		key3 = n3;
	}
	
	/**
	 * Returns the current position of the given disc (1, 2, or 3, where disc 3 is the front disc attached to the dial)
	 * @param which
	 * @return
	 */
	public int getDiscPosition(int which) {
		int pos = 0;
		if (which == 1) {
			pos = pos1;
		} if (which == 2) {
			pos = pos2;
		} if (which == 3) {
			pos = pos3;
		}
		return pos;
	}
	
	/**
	 * Returns true if all three discs are aligned, that is, for all discs the current position is equal to the offset. 
	 * @return
	 */
	public boolean isAligned() {
		if (normalize(key1 - 2 * TOOTH) == pos1 && normalize(key2 + TOOTH) == pos2 && key3 == pos3) {
			return true;
		} else {
		return false;
		}
	}
	
	/**
	 * Determines whether the lock is currently open.
	 * @return
	 */
	public boolean isOpen() {
		return open;
	}
	
	/**
	 * Opens the lock, if possible.
	 */
	public void open() {
		if (isAligned()) {
			open = true;
		}
	}
	
	/**
	 * Closes the lock whether or not the discs are aligned.
	 */
	public void close() {
		open = false;
	}

	/**
	 * Set the discs to random, valid positions.
	 * @param rand
	 */
	public void randomizePositions(Random rand) {
		pos1 = normalize(rand.nextInt(360) + 2 * TOOTH);
		pos2 = normalize(rand.nextInt(360) + TOOTH);
		pos3 = normalize(rand.nextInt(360));
	}
	
	/**
	 * Sets the positions of the three discs to given angles, as closely as possible while ensuring the positions are valid.
	 * @param n1
	 * @param n2
	 * @param n3
	 */
	public void setPositions(int n1, int n2, int n3) {
		pos3 = normalize(n3);
		pos2 = normalize(n2);
		if (normalize(pos3 - pos2) < 2) {
			pos2 = normalize(n2 - 1);
		} else if (normalize(pos2 - pos3) < 2) {
			pos2 = normalize(n2 + 1);
		}
		pos1 = normalize(n1);
		if (normalize(pos2 - pos1) < 2) {
			pos1 = normalize(n1 - 1);
		} else if (normalize(pos1 - pos2) < 2) {
			pos1 = normalize(n1 + 1);
		} 
	}
	
	/**
	 * Turns the dial (disc 3) the given number of degrees, where a positive number represents a counterclockwise rotation and a negative number represents a clockwise rotation.
	 * @param degrees
	 */
	public void turn(int degrees) {
				
		if (degrees > 0) { //counterclockwise movement
			
			int pos2Spin = normalize(pos2 - pos3 - TOOTH); //Gets how much pos3 can spin before pos2 moves
			
			if (degrees > pos2Spin) {
				int pos1Spin = normalize(pos1 - pos2 - TOOTH); //Gets how much pos2 can spin before pos1 moves
				
				if (degrees > pos2Spin + pos1Spin) {
					pos1 = normalize(pos1 + (degrees - pos1Spin - pos2Spin));
				}
				
				pos2 = normalize(pos2 + (degrees - pos2Spin));
			}
			
		} 
		
		if (degrees < 0) { //clockwise movement
			
			int pos2Spin = normalize(pos3 - pos2) - TOOTH; //Gets how much pos3 can spin before pos2 moves
			
			if (Math.abs(degrees) > pos2Spin) {
				int pos1Spin = normalize(pos2 - pos1) - TOOTH ; //Gets how much pos2 can spin before pos1 moves
				
				if (Math.abs(degrees) > pos2Spin + pos1Spin) {
					pos1 = normalize(pos1 - (Math.abs(degrees) - pos1Spin - pos2Spin));
				}
				
				pos2 = normalize(pos2 - (Math.abs(degrees) - pos2Spin));
			}
		}
		
		pos3 = normalize(pos3 + degrees);
	}
	
	/**
	 * Turns the dial (disc 3) clockwise until its position is the given number.
	 * @param number
	 */
	public void turnRightTo(int number) {
		int degrees = normalize(number - pos3) - 360;
		turn(degrees);
		
		pos3 = number;
	}
	
	/**
	 * Turns the dial (disc 3) counterclockwise until its position is the given number.
	 * @param number
	 */
	public void turnLeftTo(int number) {
		int degrees = normalize(number - pos3);
		turn(degrees);
		
		pos3 = number;
	}
	
	/**
	 * Normalizes a number to always be positive and within the turn of the lock
	 * @param number that you want to normalize
	 * @return the normalized number
	 */
	private int normalize (int number) {
		number = Math.floorMod((number + 360), 360);
		return number;
	}
}
