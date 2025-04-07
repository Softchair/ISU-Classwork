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
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{

	private int pLength = points.length - 1;

	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts) {
		super(pts);
		algorithm = "Quick Sort";
	}

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 */
	@Override 
	public void sort() {
		quickSortRec(0, pLength);
	}
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last) {
		if (first < last) { //If first is less than last/is not a single point
			int pivotPoint = partition(first, last);
			quickSortRec(first, pivotPoint-1);
			quickSortRec(pivotPoint+1, last);
		}
 	}
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last) {

		Point right = points[last]; //Sets rightmost point as a variable
		int i = first - 1; //Gets first point to start with

		for (int j = first; j < last; j++) {
			if (pointComparator.compare(points[j], right) <= 0) { //If points[j] is less than or equal to right, swap it
				i++;
				swap(i, j);
			}
		}
		swap(i+1, last);

		return i+1;

	}
}
