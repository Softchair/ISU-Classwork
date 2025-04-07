package edu.iastate.cs228.JunitTests;

import edu.iastate.cs228.hw1.ISPBusiness;
import edu.iastate.cs228.hw1.Town;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static edu.iastate.cs228.hw1.ISPBusiness.getProfit;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Camden Fergen
 *
 * Tests the ISPBusiness class
 */
class ISPBusinessTest {

    @Test
    void updatePlain() throws FileNotFoundException {
        Town test = new Town("C:\\Users\\asoft\\Desktop\\ISU\\COMS 228\\HW1\\src\\edu\\iastate\\cs228\\JunitTests\\ISP4x4.txt");
        Town newTown = ISPBusiness.updatePlain(test);
        String temp = "E E E E \n" +
                "C C O E \n" +
                "C O E O \n" +
                "C E E E ";

        assertEquals(temp, newTown.toString());
    }

    @Test
    void getProfitTest() {
        Town test = new Town(4, 4);
        test.randomInit(10);
        int s = getProfit(test);
        assertEquals(1, s);
    }

    @Test
    void main() {
    }
}