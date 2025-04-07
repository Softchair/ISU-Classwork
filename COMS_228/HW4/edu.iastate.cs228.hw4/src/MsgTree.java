/**
 * @author Camden Fergen
 * A tree that contains the information to decode a binary message
 */
public class MsgTree {

    /**
     * The character the node contains
     */
    public char payloadChar;

    /**
     * The left child of this node
     */
    public MsgTree left;

    /**
     * The right child of this node
     */
    public MsgTree right;

    /* Can use a static char idx to the tree string for recursive
    solution, but it is not strictly necessary */
    private static int staticCharIdx = 0;


    //Used for the Stats extra credit
    /**
     * Length of the message, used to print stats
     */
    private static int msgLength = 0;

    /**
     * Total number of bits of all characters added together, used to be divided by how many characters.
     */
    private static int numBits = 0;

    /**
     * Total number of characters in the tree. Used to find average bits per char
     */
    private static int numChars = 0;

    /**
     * Builds a MsgTree from a string
     * @param encodingString the encoding string, must be in correct formatting (example: ^a^^!^dc^rb)
     */
    public MsgTree(String encodingString) {

        payloadChar = encodingString.charAt(staticCharIdx); //Sets the payload of this node to the current char

        left = buildMsgTree(encodingString); //Calls building node for the left child of this node
        right = buildMsgTree(encodingString); //Calls building node for the right child of this node
    }

    /**
     * Recursively generates a MsgTree binary tree using itself and the MsgTree constructor(s)
     * @return a MsgTree node
     */
    private MsgTree buildMsgTree(String encodingString) {
        staticCharIdx += 1; //Progresses down the string by one

        char cur = encodingString.charAt(staticCharIdx); //Sets cur to the current char at index, for easy use

        if (cur != '^') {
            return new MsgTree(cur); //If the cur is a letter, set the payload to that and return
        } else {
            return new MsgTree(encodingString); //If it's not a letter, recall the MsgTree constructor till you run into a letter
        }
    }

    /**
     * Generates a node with no children with given char
     * @param payloadChar The value of the node
     */
    public MsgTree(char payloadChar) {
        this.payloadChar = payloadChar;
    }

    //method to print characters and their binary codes
    public static void printCodes(MsgTree root, String code) {
        if (root.payloadChar != '^') {
            if (root.payloadChar == '\n') {
                System.out.println("   " + "\\n" + "         " + code); //Special case for newline
            } else {
                System.out.println("    " + root.payloadChar + "         " + code); //Base case, prints out the char and also the binary code
            }
            numBits += code.length();
            numChars++;
        } else {
            printCodes(root.left, code += "0"); //Explores the left subtree, adds 0 to the code
            code = code.substring(0, code.length() - 1); //Goes back up a layer, remove the last char
            printCodes(root.right, code += "1"); //Explores the right subtree, adds 1 to the code
        }
    }

    /**
     * Gets the length of the message as an int
     */
    public int getMsgLength() {
        return msgLength;
    }

    /**
     * Returns the average number of bits per character
     */
    public double getAvgBits() {
        return numBits /numChars;
    }

    //Decodes a tree and prints it to console
    public void decode(MsgTree codes, String msg) {
        staticCharIdx = 0;

        while (staticCharIdx < msg.length()) {
            decodeRec(codes, msg);
        }
    }

    private void decodeRec(MsgTree node, String msg) {

        if (node.payloadChar != '^') {
            System.out.print(node.payloadChar + "");
            msgLength++;
        } else {
            if (msg.charAt(staticCharIdx) == '0') {
                staticCharIdx++;
                decodeRec(node.left, msg);
            } else {
                staticCharIdx++;
                decodeRec(node.right, msg);
            }
        }
    }
}
