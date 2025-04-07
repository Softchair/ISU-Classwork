package edu.iastate.cs228.hw2;

/**
 *  
 * @author Camden Fergen
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import edu.iastate.cs228.hw2.Algorithm;


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{

		Scanner userInput = new Scanner(System.in);
		System.out.println("Performances of Four Sorting Algorithms in Point Scanning \n");
		System.out.println("Keys: 1 (random integers) 2 (file input) 3 (exit)");

		int type = userInput.nextInt();
		int trialNum = 1;

		Point[] pts;
		PointScanner[] scanners = new PointScanner[4];

		while (type != 3) {

			System.out.println("Trial " + trialNum + ": " + type);

			if (type == 1) { //Ask user what they want
				System.out.println("Enter number of points");
				Random rand = new Random();
				pts = generateRandomPoints(userInput.nextInt(), rand);
				for (int i = 0; i < 4; i++) {
					switch (i) {
						case 0:
							scanners[i] = new PointScanner(pts, Algorithm.SelectionSort); break;
						case 1:
							scanners[i] = new PointScanner(pts, Algorithm.InsertionSort); break;
						case 2:
							scanners[i] = new PointScanner(pts, Algorithm.MergeSort); break;
						case 3:
							scanners[i] = new PointScanner(pts, Algorithm.QuickSort); break;
					}
				}
				//End rand
			} else if (type == 2) {

				System.out.println("Enter file name");
				String fName = userInput.next();

				for (int i = 0; i < 4; i++) {
					switch (i) {
						case 0:
							scanners[i] = new PointScanner(fName, Algorithm.SelectionSort); break;
						case 1:
							scanners[i] = new PointScanner(fName, Algorithm.InsertionSort); break;
						case 2:
							scanners[i] = new PointScanner(fName, Algorithm.MergeSort); break;
						case 3:
							scanners[i] = new PointScanner(fName, Algorithm.QuickSort);	break;
					}
				}
				//End text file
			}

			for (int i = 0; i < 4; i++) {
				scanners[i].scan();
			}
			System.out.println("Algorithm   size    time  (ns)");
			System.out.println("------------------------------");
			System.out.println(scanners[0].stats());
			System.out.println(scanners[1].stats());
			System.out.println(scanners[2].stats());
			System.out.println(scanners[3].stats());
			System.out.println("------------------------------");

			System.out.println("Keys: 1 (random ints) 2 (file input) 3 (exit)"); //Ask for user input again
			type = userInput.nextInt();
			trialNum++;

		}

		System.exit(0);

		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round, 
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       PointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort.



		
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() 
		//        method in the PointScanner class.  
		//
		//     c) After all four scans are done for the input, print out the statistics table from
		//		  section 2.
		//
		// A sample scenario is given in Section 2 of the project description. 
		
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] × [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException {
		if (numPts < 1) {
			throw new IllegalArgumentException();
		}

		Point[] list = new Point[numPts];

		for (int i = 0; i < numPts; i++) {
			int x =  rand.nextInt(101) - 50;
			int y =  rand.nextInt(101) - 50;
			list[i] = new Point(x, y);
		}

		return list;
	}
	
}
