import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

//import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMoves {

    public GameBoard gb;
    private Pair moveToMake;
    private State curState;


    @BeforeEach
    public void setup() throws IOException {
        gb = new GameBoard();
        gb.readInput("Sources/testFiles/1.txt");
        curState = gb.getState();
    }


    /** Testing Valid Moves **/
    @Test
    public void testValidMove() {
        // Setup test data
        moveToMake = new Pair(0, 'w');

        // Call method to test
        boolean result = gb.validMove(moveToMake, gb.getState());

        // Check result
        assertTrue(result);
    }

    @Test
    public void testValidMoveNorth() {
        Vehicle movedVehicle = gb.makeMove(new Pair(0, 'w'), curState);
        gb.getState().setMovedVehicle(movedVehicle);
        curState.printGameBoard();
        movedVehicle = gb.makeMove(new Pair(0, 'w'), curState);
        gb.getState().setMovedVehicle(movedVehicle);
        curState.printGameBoard();

        moveToMake = new Pair(1, 's');
        boolean result = gb.validMove(moveToMake, gb.getState());
        assertTrue(result);
    }

    @Test
    public void testValidMoveSouth() {
        Vehicle movedVehicle = gb.makeMove(new Pair(0, 'w'), curState);
        gb.getState().setMovedVehicle(movedVehicle);
        movedVehicle = gb.makeMove(new Pair(0, 'w'), curState);
        gb.getState().setMovedVehicle(movedVehicle);
        movedVehicle = gb.makeMove(new Pair(1, 's'), curState);
        gb.getState().setMovedVehicle(movedVehicle);

        gb.getState().printGameBoard();

        moveToMake = new Pair(1, 'n');
        boolean result = gb.validMove(moveToMake, gb.getState());
        assertTrue(result);
    }

    @Test
    public void testValidMoveEast() {
        Vehicle movedVehicle = gb.makeMove(new Pair(0, 'w'), curState); //Move 0 west one so we can move it back
        gb.getState().setMovedVehicle(movedVehicle);

        moveToMake = new Pair(0, 'e');
        boolean result = gb.validMove(moveToMake, gb.getState());
        assertTrue(result);
    }

    @Test
    public void testValidMoveWest() {
        moveToMake = new Pair(0, 'w');
        boolean result = gb.validMove(moveToMake, gb.getState());
        assertTrue(result);
    }

    /** End testing valid moves **/

    /** Test moves **/
    @Test
    public void testMoveEast() {
        Vehicle movedVehicle = gb.makeMove(new Pair(0, 'w'), curState); //Move 0 west one so we can move it back
        gb.getState().setMovedVehicle(movedVehicle);

        moveToMake = new Pair(0, 'e');
        movedVehicle = gb.makeMove(new Pair(0, 'e'), curState);
        gb.getState().setMovedVehicle(movedVehicle);

        gb.getState().printGameBoard();
    }

    @Test
    public void testInvalidMove() {
        // Setup test data
        moveToMake = new Pair(0, 'n');

        // Call method to test
        boolean result = gb.validMove(moveToMake, gb.getState());

        assertFalse(result);
    }

    @Test
    public void testOutOfBounds() {
        moveToMake = new Pair(0, 'w');
        Vehicle movedVehicle = gb.makeMove(moveToMake, curState); //Move over to edge
        gb.getState().setMovedVehicle(movedVehicle); //Update vehicle
        movedVehicle = gb.makeMove(moveToMake, curState); //Move over to edge
        gb.getState().setMovedVehicle(movedVehicle); //Update vehicle

        boolean result = gb.validMove(moveToMake, gb.getState());

        assertFalse(result);
    }

}
