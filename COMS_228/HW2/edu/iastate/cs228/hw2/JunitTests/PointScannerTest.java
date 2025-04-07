package edu.iastate.cs228.hw2.JunitTests;

import edu.iastate.cs228.hw2.Algorithm;
import edu.iastate.cs228.hw2.CompareSorters;
import edu.iastate.cs228.hw2.Point;
import edu.iastate.cs228.hw2.PointScanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Pradyumna Dahal
 */

class PointScannerTest {
    PointScanner[] first;
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        first = null;
    }

    @Test
    void scan() {
        Random rand = new Random(26);
        for (int i = 0; i < 6000; i++) {
            int points = rand.nextInt(100)+1;
            int seed = rand.nextInt(1000);
            first = createScanner(CompareSorters.generateRandomPoints(rand.nextInt(100)+1, new Random(rand.nextInt(1000))));
            assertEquals(first[0].toString(),first[1].toString(), "|" + i + ": " + Algorithm.values()[1] + "|" +points + "|" +seed);
            assertEquals(first[0].toString(),first[2].toString(), "|" + i + ": " + Algorithm.values()[2]+ "|" +points + "|" +seed);
            assertEquals(first[0].toString(),first[3].toString(), "|" + i + ": " + Algorithm.values()[3]+ "|" +points + "|" +seed);
        }
    }
    public PointScanner[] createScanner(Point[] points){
        PointScanner[] scanners = new PointScanner[4];
        scanners[Algorithm.SelectionSort.ordinal()] = new PointScanner(points, Algorithm.SelectionSort);
        scanners[Algorithm.InsertionSort.ordinal()] = new PointScanner(points, Algorithm.InsertionSort);
        scanners[Algorithm.MergeSort.ordinal()] = new PointScanner(points, Algorithm.MergeSort);
        scanners[Algorithm.QuickSort.ordinal()] = new PointScanner(points, Algorithm.QuickSort);
        for (int i = 0; i < scanners.length; i++) {
            scanners[i].scan();
        }
        return scanners;
    }
}