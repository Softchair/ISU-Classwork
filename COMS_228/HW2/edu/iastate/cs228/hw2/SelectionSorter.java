package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author Camden Fergen
 *
 */

/**
 * 
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		algorithm = "Selection Sort";
	}
	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.
	 */
	@Override 
	public void sort() {
		int length = points.length; //Easy access length

		for (int i = 0; i < length-1; i++) {
			for (int j = i+1; j < length; j++) {
				//if (points[i] > points[j]) {
				if (pointComparator.compare(points[i], points[j]) >= 1 ) {
					swap(i, j); //Swap i and j if i is bigger than j
				}
			}
		}

		//End of method
	}	
}
