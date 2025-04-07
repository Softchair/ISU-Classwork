package edu.iastate.cs228.hw1;
import org.junit.jupiter.api.parallel.Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static edu.iastate.cs228.hw1.State.CASUAL;

/**
 * @author Camden Fergen
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {
	
	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth()); //Creates a new town with the same length and width

		for (int i = 0; i < tOld.getLength(); i++) { //Iterates over the new/old town
			for (int j = 0; j < tOld.getWidth(); j++) {
				tNew.grid[i][j] = tOld.grid[i][j].next(tNew); //Assigns the new cell type to the new Town
			}
		}

		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return number of casual cells
	 */
	public static int getProfit(Town town) {

		int i, j;
		int cCount = 0; //Number of casual cells
		for (i = 0; i < town.getLength(); i++) { //Iterate over the grid and get each cell's identity and add them up
			for (j = 0; j < town.getWidth(); j++) {
				if (town.getCell(town, i, j).who() == CASUAL) {
					cCount += 1;
				}
			}
		}

		return cCount; //Returns the number of Casual cells
	}
	

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) throws FileNotFoundException {

		Scanner in = new Scanner(System.in); //Scanner to read user input
		Town s = null; //Town to be used

		System.out.println("How should the grid be populated (type 1 or 2): 1: From a file. 2: Randomly with seed.");
		if (in.nextInt() == 1) { //If user wants to populate town from file
			System.out.println("Please enter a file name");
			s = new Town(in.nextLine());
		} else if (in.nextInt() == 2) { //If user wants to populate town randomly (with seed)
			System.out.println("Provide rows, cols, and seed integer separated by spaces:");
			int row = in.nextInt();
			int col = in.nextInt();
			int seed = in.nextInt();
			s = new Town(row, col);
			s.randomInit(10);
		}

		double cCount = 0;
		for (int months = 0; months < 12; months++) {
			cCount += getProfit(s);
			s = updatePlain(s);
		}

		double calc = cCount * 100/ (12 * (s.getWidth()*s.getLength()));

		System.out.println(String.format("%,.2f", calc) + "%");

	}
}
