import java.io.IOException;
import java.util.ArrayList;

public class HW3Main {

    public static void main(String[] args) throws IOException {

        GameBoard gb = new GameBoard();


        gb.readInput("HW3/Sources/testFiles/1.txt");
        //gb.readInput("HW3/Sources/tests/in/input2.txt");
        gb.findAllPaths();
        ArrayList<Pair> plan = gb.getPlan();

        for (Pair curPair : plan) {
            curPair.printPair();
        }

        System.out.println("Moves made: " + plan.size());

        System.out.println(gb.getNumOfPaths());
    }
}
