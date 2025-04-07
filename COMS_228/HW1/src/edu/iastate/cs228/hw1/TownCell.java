package edu.iastate.cs228.hw1;

/**
 * @author Camden Fergen
 * Generic TownCell object
 */
public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;
	
	
	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;
	
	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	/**
	 * Creates a new townCell placed at r, c
	 * @param p Which town
	 * @param r Row
	 * @param c Column
	 */
	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}
	
	/**
	 * Checks all neighborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 *  
	 * @param nCensus of all customers
	 */
	public void census(int nCensus[]) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0; 
		nCensus[EMPTY] = 0; 
		nCensus[CASUAL] = 0; 
		nCensus[OUTAGE] = 0; 
		nCensus[STREAMER] = 0;

		State stateTemp = plain.getCell(plain, row, col).who(); //Gets the cells state to remove 1 from nCensus
		int tempNum = 0;
		switch (stateTemp) {
			case RESELLER: tempNum = 0; break;
			case EMPTY: tempNum = 1; break;
			case CASUAL: tempNum = 2; break;
			case OUTAGE: tempNum = 3; break;
			case STREAMER: tempNum = 4; break;
		}
		nCensus[tempNum] -= 1;

		//Setup variables for the edges of neighbors
		int startingRow = row;
		int startingCol = col;
		int endingRow = row;
		int endingCol = col;

		//Make sure that the col or row doesn't go negative
		if (row != 0) {
			startingRow = row - 1;
		} if (col != 0) {
			startingCol = col - 1;
		} if (row < plain.getLength() - 1) {
			endingRow = row + 1;
		} if (col < plain.getWidth() - 1) {
			endingCol = col + 1;
		}

		for (int i = startingRow; i <= endingRow; i++) { //iterates over the cells neighboring cells and adds them all up
			for (int j = startingCol; j <= endingCol; j++) {

				 State temp = plain.grid[i][j].who();

				 if (temp == State.RESELLER) { //adds to array depending on which State it is
					 nCensus[RESELLER] += 1;
				 } else if (temp == State.EMPTY) {
					 nCensus[EMPTY] += 1;
				 } else if (temp == State.CASUAL) {
					 nCensus[CASUAL] += 1;
				 } else if (temp == State.OUTAGE) {
					 nCensus[OUTAGE] += 1;
				 } else if (temp == State.STREAMER) {
					 nCensus[STREAMER] += 1;
				 }
			}
		}

	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);

}
