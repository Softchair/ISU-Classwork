package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import static edu.iastate.cs228.hw1.State.CASUAL;

/**
 *  @author Camden Fergen
 *
 */
public class Town {

	private int length, width;  //Row and col (first and second indices)
	public TownCell[][] grid;
	
	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length how many cells tall the town should be
	 * @param width how many cells wide the town should be
	 */
	public Town(int length, int width) {
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];
	}
	
	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simply throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		File townFile = new File(inputFileName);
		Scanner getter = new Scanner(townFile);

		length = getter.nextInt(); //gets the length and width of the town/array
		width = getter.nextInt();
		grid = new TownCell[width][length]; //generates the empty new array

		for (int i = 0; i < length; i++) { //Iterates over the text file to fill in grids cells
			for (int j = 0; j < width; j++) {
				switch (getter.next().charAt(0)) {
					case 'C': grid[i][j] = new Casual(this, i, j); break;
					case 'S': grid[i][j] = new Streamer(this, i, j); break;
					case 'R': grid[i][j] = new Reseller(this, i, j); break;
					case 'O': grid[i][j] = new Outage(this, i, j); break;
					case 'E': grid[i][j] = new Empty(this, i, j); break;
				}

			}
		}

		getter.close();
	}

    /**
     * Returns the TownCell at a certain point in the grid
     * @param t The town the cell is in
     * @param r Row the cell is in
     * @param c Col the cell is in
     * @return Object at cell
     */
    public TownCell getCell (Town t, int r, int c) {
        return grid[r][c];
    }

	/**
	 * Returns width of the grid.
	 * @return int, width of town cell
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns length of the grid.
	 * @return int, length of town cell
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);

		int i, j;
		for (i = 0; i < length; i++) { //Iterate over the grid and print out each cell's identity
			for (j = 0; j < width; j++) {
				int temp = rand.nextInt(5); //Generate random number
				switch (temp) {
					case 0: grid[i][j] = new Reseller(this, i, j); break; //Uses temp to assign the cell to a TownCell based on the static variables in TownCell.java
					case 1: grid[i][j] = new Empty(this, i, j); break;
					case 2: grid[i][j] = new Casual(this, i, j); break;
					case 3: grid[i][j] = new Outage(this, i, j); break;
					case 4: grid[i][j] = new Streamer(this, i, j); break;
				}
			}
		}

	}
	
	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between 
	 * the rows.
	 */
	public String toString() {
		String s = ""; //Creates a new String s

		int i, j;
		for (i = 0; i < length; i++) { //Iterate over the grid and print out each cell's identity
			for (j = 0; j < width; j++) {
				switch (grid[i][j].who()) {
					case CASUAL: s += "C "; break;
					case STREAMER: s += "S "; break;
					case RESELLER: s += "R "; break;
					case OUTAGE: s += "O "; break;
					case EMPTY: s += "E "; break;
				}
				//s += " "; //Adds a space after each cell
			}
			if (i < length - 1) {s += "\n";}  //Creates a new line after each row
		}

		return s;
	}
}
