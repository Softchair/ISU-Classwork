package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException;
import java.time.Clock;
import java.util.InputMismatchException;

/**
 *  
 * @author Camden Fergen
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) {
		super(pts);
		algorithm = "Merge Sort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort() {
		if (points.length == 1) { //Base case, nothing to sort
			return;
		}

		Point[] mergedPoints = mergeSortRec(points);
		System.arraycopy(mergedPoints, 0, points, 0, points.length);
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private Point[] mergeSortRec(Point[] pts) {
		int length = pts.length;
		int odd = 0;

		if (length <= 1) { //Base case
			return pts;
		}

		int middle = length / 2; //Find middle of the array

		Point[] ptsLeft = new Point[middle]; //Initialize the arrays for use
		Point[] ptsRight = new Point[length-middle];

		//Copy pts to the two arrays
		System.arraycopy(pts, 0, ptsLeft, 0, middle); //Splits the array and copys it to two different ones

		if (length %2 != 0) { odd += 1; } //if length is odd, add an extra so it can have all the elements
		System.arraycopy(pts, middle, ptsRight, 0, middle + odd);

		Point[] leftTemp = mergeSortRec(ptsLeft); //Recursive call to sort the arrays
		System.arraycopy(leftTemp, 0, ptsLeft, 0, ptsLeft.length); //Make sure ptsLeft is correctly sorted, had alot of problems with this

		Point[] rightTemp = mergeSortRec(ptsRight);
		System.arraycopy(rightTemp, 0, ptsRight, 0, ptsRight.length);

		Point[] temp = merge(ptsLeft, ptsRight);
		System.arraycopy(temp, 0, pts, 0, pts.length);
		return pts;
	}

	/**
	 * Merges two arrays into one big sorted array
	 * @param left the left array of points
	 * @param right the right array of points
	 * @return Sorted array of points
	 */
	private Point[] merge(Point[] left, Point[] right) {
		int leftL = left.length, rightL = right.length;
		Point[] merged = new Point[leftL + rightL]; //Creates the array of merged points

		int i = 0, j = 0, cursor = 0;

		while (i < leftL && j < rightL) {
			//if left[i] less than or equal to right[j]
			if (pointComparator.compare(left[i], right[j]) <= 0 ) {
			//if (left[i].getX() <= right[j].getX() ) {
				merged[i+j] = left[i];
				i++;
			} else {
				merged[i+j] = right[j];
				j++;
			}
		}

		if (i >= leftL) { //Add all leftover elements to the array
			for (int k = j; k < right.length; k++) {
				merged[i+j] = right[j];
				j++;
			}
		} else {
			for (int k = i; k < left.length; k++) {
				merged[i+j] = left[i];
				i++;
			}
		}

		return merged;
	}

}
