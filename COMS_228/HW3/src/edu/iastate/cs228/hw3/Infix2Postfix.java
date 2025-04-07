package edu.iastate.cs228.hw3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Camden Fergen
 * Takes a(n) infix equation(s) in the file "input.txt" and converts them to postfix expression outputted in "output.txt"
 * Can handle multiple infix equations in the same text document
 * infix -> postfix
 */
public class Infix2Postfix {


    /**
     * Takes a(n) infix equation(s) in the file "input.txt" and converts them to postfix expression outputted in "output.txt"
     */
    public static void main(String[] args) throws IOException {
        File input = new File("input.txt");
        File output = new File("output.txt");

        Scanner scanLine = new Scanner(input); //Sets up a scanner on the input file

        String outputString = "";

        while (scanLine.hasNextLine()) {
            String curLine = scanLine.nextLine(); //Updates the current line to do the next equation

            Scanner scan = new Scanner(curLine); //Creates a new scanner to scan over the current line
            outputString += infixToPostfix(scan) + "\n";

            scan.close();
        }
        scanLine.close();

        FileWriter writer = new FileWriter(output);
        writer.write(outputString); //Writes the output to the file
        writer.close();

        System.exit(0);
    }

    /**
     * Scans through a given file and converts it to a postfix expression
     * @param scan Scanner of the file
     */
    private static String infixToPostfix(Scanner scan) {
        String output = "";
        int rank = 0;
        Stack<String> opStack = new Stack<>();

        while (scan.hasNext()) {
            String cur = scan.next();

            //Rank checking
            if (isOp(cur) && !cur.equals("(")) { //If operator, drop rank by one
                rank -= 1;
            } else if (!cur.equals(")") && !isOp(cur)) { //If operand, add one to rank
                rank += 1;
            }

            if (rank > 1) {
                output = "Error: too many operands (" + cur + ")";
                return output;
            } else if (rank < 0) {
                if (!opStack.isEmpty()) {
                    output = "Error: too many operators (" + opStack.pop() + ")";
                } else {
                    output = "Error: too many operators (" + cur + ")";
                }
                return output;
            }
            //End rank checking

            //Adding operators to stack
            if (!isOp(cur) && !cur.equals(")")) {
                output += cur + " ";

            } else if (isOp(cur) || cur.equals(")")) {

                //When you hit an end parenthesis
                if (cur.equals(")")) {

                    if (opStack.isEmpty()) {
                        output = "Error: no opening parenthesis detected";
                        return output;
                    }

                    //Create a temp current string
                    String tempCur = opStack.pop();

                    //If there are no operators between the parenthesis, throw error
                    if (tempCur.equals("(")) {
                        output = "Error: no subexpression detected ()";
                        return output;
                    }

                    //Pop the stack and add the operators to the output till a opening parenthesis
                    while (!opStack.isEmpty() && !tempCur.equals("("))  {
                        output += tempCur + " ";
                        tempCur = opStack.pop();
                    }


                //Add operators to output/stack
                } else {
                    while (!opStack.isEmpty() && (precInput(cur) <= precStack(opStack.peek()))) {
                        output += opStack.pop() + " ";
                    }
                    opStack.push(cur);
                }
            }
            //End adding operators to stack

            //END
        }

        //Add all operators to output
        while (!opStack.isEmpty()) {
            String temp = opStack.pop();
            if (temp.equals("(")) {
                //todo doesn't work
                output = "Error: no closing parenthesis detected";
                return output;
            } else {
                output += temp + " ";
            }
        }
        //End

        /*
        todo Infix to Postfix
        Check rank after entering each symbol
         - if > 0
           - Too many operators - first operator on stack
         - if < 1
           - Too many operands - Add current operand

        Add operand to string

        If operator
         - Compare input precedence (precInput(cur)) of cur to top of stack precStack(opStack.peek())

        If )
         - Pop all operators till ( including (
           - If no (, - error no opening parenthesis

        End of infix
         - Pop all operators and add to string
           - If run into ( - error no closing parenthesis
        */

        return output;

    }

    /**
     * Returns the stack precedence of an operator
     */
    private static int precStack (String s) {
        return switch (s) {
            case "(" -> -1;
            case "^" -> 3;
            case "*", "%", "/" -> 2;
            case "+", "-" -> 1;
            default -> 0;
        };
    }

    /**
     * Returns the input precedence of an operator
     */
    private static int precInput (String s) {
        return switch (s) {
            case "(" -> 5;
            case "^" -> 4;
            case "*", "%", "/" -> 2;
            case "+", "-" -> 1;
            default -> 0;
        };

    }

    /**
     * Checks to see if the current string is an operator or not
     * @param s String to be checked
     * @return True if its an operator, False if its a number
     */
    private static boolean isOp(String s) {

        return switch (s) { //Checks if current character is an operator
            case "+", "-", "*", "/", "%", "^", "(" -> true;
            default -> false; //Returns false if it's a num
        };

    }

}
