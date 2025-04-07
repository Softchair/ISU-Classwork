package edu.iastate.cs228.hw2.JunitTests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import edu.iastate.cs228.hw2.*;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Sachin Patel
 *
 */

class SortMethodsTest {
	
	Point[] points;
	
	void initializePoints() throws FileNotFoundException {
		File f = new File("C:\\Users\\asoft\\Desktop\\ISU Class\\COMS228\\HW2\\HW2\\edu\\iastate\\cs228\\hw2\\JunitTests\\input.txt");
		Scanner s = new Scanner(f);
		int i = 0;
		ArrayList<Point> pts = new ArrayList<>();
		while(s.hasNextInt()) {
			int x = s.nextInt();
			int y;
			if(s.hasNextInt()) {
				y = s.nextInt();
			}
			else {
				s.close();
				throw new InputMismatchException();
			}
			pts.add(new Point(x, y));
			i++;
		}
		points = new Point[i];
		for(int j = 0; j < pts.size(); j++) {
			points[j] = pts.get(j); 
		}
		s.close();
	}

	@Test
	void QuickSortTest() throws FileNotFoundException{
		
		initializePoints();
		
		QuickSorter s = new QuickSorter(points);
		String initial = "";
		
		for(int i1 = 0; i1 < points.length; i1++) {
			initial += points[i1].toString() +"\n";
		}
		
		assertEquals("(0, 0)\n"
				+ "(-3, -9)\n"
				+ "(0, -10)\n"
				+ "(8, 4)\n"
				+ "(3, 3)\n"
				+ "(-6, 3)\n"
				+ "(-2, 1)\n"
				+ "(10, 5)\n"
				+ "(-7, -10)\n"
				+ "(5, -2)\n"
				+ "(7, 3)\n"
				+ "(10, 5)\n"
				+ "(-7, -10)\n"
				+ "(0, 8)\n"
				+ "(-1, -6)\n"
				+ "(-10, 0)\n"
				+ "(5, 5)\n", initial);
		
		s.setComparator(0);
		s.sort();
		s.getPoints(points);
		String xVals = "";
		for(int i2 = 0; i2 < points.length - 1; i2++) {
			xVals += points[i2].getX() + ", ";
		}
		xVals += points[points.length - 1].getX();
		
		assertEquals("-10, -7, -7, -6, -3, -2, -1, 0, 0, 0, 3, 5, 5, 7, 8, 10, 10", xVals);
		
		s.setComparator(1);
		s.sort();
		s.getPoints(points);
		String yVals = "";
		for(int i2 = 0; i2 < points.length - 1; i2++) {
			yVals += points[i2].getY() + ", ";
		}
		yVals += points[points.length - 1].getY();
		
		assertEquals("-10, -10, -10, -9, -6, -2, 0, 0, 1, 3, 3, 3, 4, 5, 5, 5, 8", yVals);
		
	}
	
	@Test
	void MergeSortTest() throws FileNotFoundException{
		
		initializePoints();
		
		MergeSorter s = new MergeSorter(points);
		String initial = "";
		
		for(int i1 = 0; i1 < points.length; i1++) {
			initial += points[i1].toString() +"\n";
		}
		
		assertEquals("(0, 0)\n"
				+ "(-3, -9)\n"
				+ "(0, -10)\n"
				+ "(8, 4)\n"
				+ "(3, 3)\n"
				+ "(-6, 3)\n"
				+ "(-2, 1)\n"
				+ "(10, 5)\n"
				+ "(-7, -10)\n"
				+ "(5, -2)\n"
				+ "(7, 3)\n"
				+ "(10, 5)\n"
				+ "(-7, -10)\n"
				+ "(0, 8)\n"
				+ "(-1, -6)\n"
				+ "(-10, 0)\n"
				+ "(5, 5)\n", initial);
		
		s.setComparator(0);
		s.sort();
		s.getPoints(points);
		String xVals = "";
		for(int i2 = 0; i2 < points.length - 1; i2++) {
			xVals += points[i2].getX() + ", ";
		}
		xVals += points[points.length - 1].getX();
		
		assertEquals("-10, -7, -7, -6, -3, -2, -1, 0, 0, 0, 3, 5, 5, 7, 8, 10, 10", xVals);
		
		s.setComparator(1);
		s.sort();
		s.getPoints(points);
		String yVals = "";
		for(int i2 = 0; i2 < points.length - 1; i2++) {
			yVals += points[i2].getY() + ", ";
		}
		yVals += points[points.length - 1].getY();
		
		assertEquals("-10, -10, -10, -9, -6, -2, 0, 0, 1, 3, 3, 3, 4, 5, 5, 5, 8", yVals);
	}
	
	@Test
	void SelectionSortTest() throws FileNotFoundException{
		
		initializePoints();
		
		SelectionSorter s = new SelectionSorter(points);
		String initial = "";
		
		for(int i1 = 0; i1 < points.length; i1++) {
			initial += points[i1].toString() +"\n";
		}
		
		assertEquals("(0, 0)\n"
				+ "(-3, -9)\n"
				+ "(0, -10)\n"
				+ "(8, 4)\n"
				+ "(3, 3)\n"
				+ "(-6, 3)\n"
				+ "(-2, 1)\n"
				+ "(10, 5)\n"
				+ "(-7, -10)\n"
				+ "(5, -2)\n"
				+ "(7, 3)\n"
				+ "(10, 5)\n"
				+ "(-7, -10)\n"
				+ "(0, 8)\n"
				+ "(-1, -6)\n"
				+ "(-10, 0)\n"
				+ "(5, 5)\n", initial);
		
		s.setComparator(0);
		s.sort();
		s.getPoints(points);
		String xVals = "";
		for(int i2 = 0; i2 < points.length - 1; i2++) {
			xVals += points[i2].getX() + ", ";
		}
		xVals += points[points.length - 1].getX();

		assertEquals("-10, -7, -7, -6, -3, -2, -1, 0, 0, 0, 3, 5, 5, 7, 8, 10, 10", xVals);
		
		s.setComparator(1);
		s.sort();
		s.getPoints(points);
		String yVals = "";
		for(int i2 = 0; i2 < points.length - 1; i2++) {
			yVals += points[i2].getY() + ", ";
		}
		yVals += points[points.length - 1].getY();
		
		assertEquals("-10, -10, -10, -9, -6, -2, 0, 0, 1, 3, 3, 3, 4, 5, 5, 5, 8", yVals);
	}
	
	@Test
	void InsertionSortTest() throws FileNotFoundException{
		
		initializePoints();
		
		InsertionSorter s = new InsertionSorter(points);
		String initial = "";
		
		for(int i1 = 0; i1 < points.length; i1++) {
			initial += points[i1].toString() +"\n";
		}
		
		assertEquals("(0, 0)\n"
				+ "(-3, -9)\n"
				+ "(0, -10)\n"
				+ "(8, 4)\n"
				+ "(3, 3)\n"
				+ "(-6, 3)\n"
				+ "(-2, 1)\n"
				+ "(10, 5)\n"
				+ "(-7, -10)\n"
				+ "(5, -2)\n"
				+ "(7, 3)\n"
				+ "(10, 5)\n"
				+ "(-7, -10)\n"
				+ "(0, 8)\n"
				+ "(-1, -6)\n"
				+ "(-10, 0)\n"
				+ "(5, 5)\n", initial);
		
		s.setComparator(0);
		s.sort();
		s.getPoints(points);
		String xVals = "";
		for(int i2 = 0; i2 < points.length - 1; i2++) {
			xVals += points[i2].getX() + ", ";
		}
		xVals += points[points.length - 1].getX();
		
		assertEquals("-10, -7, -7, -6, -3, -2, -1, 0, 0, 0, 3, 5, 5, 7, 8, 10, 10", xVals);
		
		s.setComparator(1);
		s.sort();
		s.getPoints(points);
		String yVals = "";
		for(int i2 = 0; i2 < points.length - 1; i2++) {
			yVals += points[i2].getY() + ", ";
		}
		yVals += points[points.length - 1].getY();
		
		assertEquals("-10, -10, -10, -9, -6, -2, 0, 0, 1, 3, 3, 3, 4, 5, 5, 5, 8", yVals);
	}
	
	@Test
	void PointScannerSortTest() throws FileNotFoundException{
		
		initializePoints();
		
		PointScanner[] scanners = new PointScanner[4]; 
		
//		scanners[0] = new PointScanner("input.txt", Algorithm.SelectionSort);
//		scanners[1] = new PointScanner("input.txt", Algorithm.InsertionSort);
//		scanners[2] = new PointScanner("input.txt", Algorithm.MergeSort);
//		scanners[3] = new PointScanner("input.txt", Algorithm.QuickSort);
		
		for(int i = 0; i < scanners.length; i++) {
			scanners[i].scan();
			assertEquals("MCP: (0, 1)", scanners[i].toString());
		}
	}
	

}
