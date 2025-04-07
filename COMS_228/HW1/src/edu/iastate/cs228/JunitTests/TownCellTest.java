package edu.iastate.cs228.JunitTests;

import edu.iastate.cs228.hw1.Casual;
import edu.iastate.cs228.hw1.Town;
import edu.iastate.cs228.hw1.TownCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Camden Fergen
 *
 * Tests the generic towncell class
 */
class TownCellTest {

    @Test
    void census() {
        Town test = new Town(1, 1);
        test.grid[0][0] = new Casual(test, 0, 0);
        test.getCell(test, 0,0).census(Casual.nCensus);
        assertEquals(0, Casual.nCensus[1]);
    }
}