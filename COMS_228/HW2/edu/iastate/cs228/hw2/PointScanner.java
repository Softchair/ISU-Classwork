package edu.iastate.cs228.hw2;

/**
 * 
 * @author Camden Fergen
 *
 */

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    

	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException {
		if (pts == null || pts.length == 0) {
			throw new IllegalArgumentException("Points is null or 0 length");
		}

		points = new Point[pts.length]; //Creates an array with length of pts

		for (int i = 0; i < pts.length; i++) {
			points[i] = pts[i]; //Copys pts to points
		}

		sortingAlgorithm = algo;
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException {
		sortingAlgorithm = algo;

		File f = new File(inputFileName);
		Scanner s = new Scanner(f);

		int size = 0; //Size of temp

		ArrayList<Point> temp = new ArrayList<>(); //Creates a temp array to store all the points as size is unknown

		while(s.hasNextInt()) {
			int x;
			int y;

			x = s.nextInt(); //Sets x as the first int

			if(s.hasNextInt()) { //If there is another int, make it the y
				y = s.nextInt();
			} else { //If no other int, cannot make a point and exits
				s.close();
				throw new InputMismatchException();
			}

			temp.add(new Point(x, y)); //Adds the new point to the temp array

			size++;
		}

		points = new Point[size];

		for(int j = 0; j < temp.size(); j++) { //Copys all the points from temp to points
			points[j] = temp.get(j);
		}

		s.close();
	}

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * //@param algo
	 * @return
	 */
	public void scan() {
		AbstractSorter aSorter;

		switch (sortingAlgorithm) {
			case MergeSort: aSorter = new MergeSorter(points); break;
			case QuickSort: aSorter = new QuickSorter(points); break;
			case InsertionSort: aSorter = new InsertionSorter(points); break;
			case SelectionSort: aSorter = new SelectionSorter(points); break;
			default:
				throw new IllegalStateException("Unexpected value: " + sortingAlgorithm);
		}

		Point median;
		int x = 0, y = 0;
		long start, end;

		start = System.nanoTime();
		for (int xy = 0; xy < 2; xy++) {
			aSorter.setComparator(xy);
			aSorter.sort();
			if (xy == 0) {
				x = points[points.length/2].getX();
			} else {
				y = points[points.length/2].getY();
			}
		}
		end = System.nanoTime();

		long total = end - start;
		scanTime = total;

		median = new Point(x, y);

		medianCoordinatePoint = median;
		
		// create an object to be referenced by aSorter according to sortingAlgorithm. for each of the two 
		// rounds of sorting, have aSorter do the following: 
		// 
		//     a) call setComparator() with an argument 0 or 1. 
		//
		//     b) call sort(). 		
		// 
		//     c) use a new Point object to store the coordinates of the medianCoordinatePoint
		//
		//     d) set the medianCoordinatePoint reference to the object with the correct coordinates.
		//
		//     e) sum up the times spent on the two sorting rounds and set the instance variable scanTime. 
		
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		String algo;

		switch (sortingAlgorithm) {
			case SelectionSort: algo = "Selection Sort"; break;
			case InsertionSort: algo = "Insertion Sort"; break;
			case QuickSort: algo = "Quick Sort"; break;
			case MergeSort: algo = "Merge Sort"; break;
			default:
				algo = "null";
		}

		return algo + " " + points.length + " " + scanTime;
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		return "(" + medianCoordinatePoint.getX() + ", " + medianCoordinatePoint.getY() + ")";
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws IOException {
		String outputFileName = "Output.txt";
		File f = new File(outputFileName);

		String output = "";

		for(int i = 0; i < points.length; i++) {
			output += points[i].toString() +"\n";
		}

		BufferedWriter to = new BufferedWriter(new FileWriter((f)));

		to.write(output);

		to.close();
	}	

	

		
}
