package edu.iastate.cs228.JunitTests;

import edu.iastate.cs228.hw1.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Camden Fergen
 *
 * Tests the empty cell
 */
class EmptyTest {

    public static Town testTown;

    @Test
    void who() {
        TownCell testCell = new Empty(testTown, 1, 1);
        State temp = testCell.who();
        assertEquals(State.EMPTY, temp);
    }

    @Test
    void next() {
        Town testTown = new Town(2, 2); //Setup testing town and cells
        testTown.grid[0][0] = new Reseller(testTown, 0, 0);
        testTown.grid[0][1] = new Empty(testTown, 0, 1);
        testTown.grid[1][0] = new Streamer(testTown, 1, 0);
        testTown.grid[1][1] = new Casual(testTown, 1, 1);

        Town tNew = new Town(2, 2); //Create new town for the next cycle

        TownCell tempTownCell = testTown.getCell(testTown, 0, 1);
        TownCell tempState = tempTownCell.next(testTown);
        assertEquals(State.CASUAL, tempState.who());
    }
}