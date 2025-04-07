package edu.iastate.cs228.JunitTests;

import edu.iastate.cs228.hw1.Town;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Camden Fergen
 *
 * Tests the town class
 */
class TownTest {

    @Test
    void Town() throws FileNotFoundException {
        Town s = new Town("C:\\Users\\asoft\\Desktop\\ISU\\COMS 228\\HW1\\src\\edu\\iastate\\cs228\\JunitTests\\ISP4x4.txt");
    }

    @Test
    void getWidth() {
        Town s = new Town(4,5);
        assertEquals(5, s.getWidth());
    }

    @Test
    void getLength() {
        Town s = new Town(4,5);
        assertEquals(4, s.getLength());
    }

    @Test
    void randomInit() {
        Town s = new Town(4,4);
        s.randomInit(10);
        String test = "O R O R \n" +
                "E E C O \n" +
                "E S O S \n" +
                "E O R R ";
        assertEquals(test, s.toString());
    }

    @Test
    void testToString() throws FileNotFoundException {
        System.out.println("Testing to string");
        Town s = new Town("C:\\Users\\asoft\\Desktop\\ISU\\COMS 228\\HW1\\src\\edu\\iastate\\cs228\\JunitTests\\ISP4x4.txt");

        String test = "O R O R \n" +
                "E E C O \n" +
                "E S O S \n" +
                "E O R R ";

        assertEquals (test, s.toString());
    }
}