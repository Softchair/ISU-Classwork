import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Camden Fergen
 * Main method for creating and decoding a MsgTree
 */
public class main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner userIn = new Scanner(System.in); //Creates a new scanner for the user input

        System.out.println("Please enter filename to decode: ");

        String fileName = userIn.next();
        File decodeFile = new File(fileName);

        Scanner fileScanner = new Scanner(decodeFile); //Creates a scanner to get the encoding string
        String encodingString = ""; //The string containing all the encoding
        String next = fileScanner.nextLine(); //String containing the next line
        int lineCount = 0; //Number of all lines (if greater than 1, that means a \n is used)

        while (!next.startsWith("1") && !next.startsWith("0")) {
            if (lineCount > 0) { //If there is another line, then a \n is used, add it to the string
                encodingString += "\n";
            }
            encodingString += next;
            lineCount += 1;
            next = fileScanner.nextLine();
        }

        MsgTree T = new MsgTree(encodingString); //Creates a new MsgTree with the encoding string

        System.out.println("character" + "    " + "code" + "\n-------------------------"); //Prints the codes out
        MsgTree.printCodes(T, "");

        System.out.println("MESSAGE:");

        T.decode(T, next); //Prints out the message from the tree

        printStats(T, next);
    }

    private static void printStats(MsgTree T, String binary) {
        System.out.println("STATISTICS:");

        System.out.println("Avg bits/char: " + T.getAvgBits());

        System.out.println("Total characters: " + T.getMsgLength());

        double spaceSaving = (1 - (T.getAvgBits()/16)) * 100; //I think this is the right way to calculate it? I'm not super sure
        System.out.printf("Space savings: %.1f", spaceSaving);
        System.out.print("%");
    }

}
