package lab3;

import java.util.ArrayList;
import java.util.List;

public class letterCombos {

    /* 2 Letter combinations
    public static List<String> generateCombinations() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        List<String> combinations = new ArrayList<>();
        for (int i =  0; i < alphabet.length(); i++) {
            for (int j =  0; j < alphabet.length(); j++) {
                combinations.add(alphabet.charAt(i) + "" + alphabet.charAt(j));
            }
        }
        return combinations;
    }
     */

    /* 3 Letter combinations
    public static List<String> generateCombinations() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        List<String> combinations = new ArrayList<>();
        for (int i = 0; i < alphabet.length(); i++) {
            for (int j = 0; j < alphabet.length(); j++) {
                for (int k = 0; k < alphabet.length(); k++) {
                    combinations.add(alphabet.charAt(i) + "" + alphabet.charAt(j) + "" + alphabet.charAt(k));
                }
            }
        }
        return combinations;
    }
     */

    /* General letter combinations */
    public static List<String> generateCombinations(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        List<String> combinations = new ArrayList<>();
        if (length == 0) {
            return combinations;
        }
        generateCombinationsRecursive("", alphabet, length, combinations);
        return combinations;
    }

    private static void generateCombinationsRecursive(String current, String alphabet, int length, List<String> combinations) {
        if (length == 0) {
            combinations.add(current);
            return;
        }

        for (int i = 0; i < alphabet.length(); i++) {
            generateCombinationsRecursive(current + alphabet.charAt(i), alphabet, length - 1, combinations);
        }
    }
}
