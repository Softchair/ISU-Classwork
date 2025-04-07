import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class GameboardTests {
    public GameBoard gb;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setupGameboard() throws IOException {
        gb = new GameBoard();
        gb.readInput("Sources/testFiles/1.txt");
    }

    @Test
    public void testGameBoard() {
        System.setOut(new PrintStream(outContent));
        gb.getState().printGameBoard();
        assertEquals("e e 1 e e e \n" +
                "e e 1 2 2 2 \n" +
                "e e 0 0 3 e \n" +
                "e e e e 3 e \n" +
                "e e e e 4 e \n" +
                "e e e e 4 e",
                outContent.toString().trim());
    }

    @Test
    public void testValidMove1() {
        // Arrange
        Pair moveToMake = new Pair(0, 'w');

        // Act
        boolean result = gb.validMove(moveToMake, gb.getState());

        // Assert
        assertTrue(result);
    }

    @Test
    public void testInvalidMove1() {
        // Arrange
        Pair moveToMake = new Pair(0, 'e');
        State curState = gb.getState(); // Assuming you have a constructor that sets up a valid state

        // Act
        boolean result = gb.validMove(moveToMake, gb.getState());

        // Assert
        assertFalse(result);
    }

    @Test
    public void testInvalidMove2() {
        // Arrange
        Pair moveToMake = new Pair(0, 'n');
        State curState = gb.getState(); // Assuming you have a constructor that sets up a valid state

        // Act
        boolean result = gb.validMove(moveToMake, gb.getState());

        // Assert
        assertFalse(result);
    }

    @Test
    public void testMoveCar() {
        Pair move = new Pair(0, 'w');

        Vehicle movedVehicle = gb.makeMove(move, gb.getState());
        gb.getState().setMovedVehicle(movedVehicle);

        System.setOut(new PrintStream(outContent));
        gb.getState().printGameBoard();
        assertEquals("e e 1 e e e \n" +
                        "e e 1 2 2 2 \n" +
                        "e 0 0 e 3 e \n" +
                        "e e e e 3 e \n" +
                        "e e e e 4 e \n" +
                        "e e e e 4 e",
                outContent.toString().trim());
    }

    //Will fail, but does work as of now
    @Test
    public void testGenerateNewState() {
        Vehicle movedVehicle = gb.makeMove(new Pair(0, 'w'), gb.getState());
        gb.getState().setMovedVehicle(movedVehicle);
        movedVehicle = gb.makeMove(new Pair(0, 'w'), gb.getState());
        gb.getState().setMovedVehicle(movedVehicle);
        gb.getState().printGameBoard();

        List<State> newStates = gb.getNextStates(gb.getState());

        for (State curState : newStates) {
            curState.printGameBoard();
            if (curState.getMoveMade() != null) {
                curState.getMoveMade().printPair();
            }

            System.out.println("\n\n");
        }
    }

}