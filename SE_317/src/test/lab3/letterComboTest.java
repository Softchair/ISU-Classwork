package lab3;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;
import lab3.letterCombos;

public class letterComboTest {

    /* 0 Letter combinations tests */
    @Test
    public void testGenerateCombinations0() {
        List<String> combinations = letterCombos.generateCombinations(0);

        assertEquals("The number of combinations should be  0", 0, combinations.size());
    }

    /* 1 Letter combinations tests */
    @Test
    public void testGenerateCombinations1() {
        List<String> combinations = letterCombos.generateCombinations(1);

        assertEquals("The number of combinations should be  26", 26, combinations.size());
        assertTrue("The combinations should include 'a'", combinations.contains("a"));
        assertTrue("The combinations should include 'z'", combinations.contains("z"));
    }

    /* 2 Letter combinations tests */
    @Test
    public void testGenerateCombinations2() {
        List<String> combinations = letterCombos.generateCombinations(2);

        assertEquals("The number of combinations should be  676", 676, combinations.size());
        assertTrue("The combinations should include 'aa'", combinations.contains("aa"));
        assertTrue("The combinations should include 'zz'", combinations.contains("zz"));
    }

    /* 3 Letter combinations tests */
    @Test
    public void testGenerateCombinations3() {
        List<String> combinations = letterCombos.generateCombinations(3);

        // Adjusted size to 17,576 for 3-letter combinations
        assertEquals("The number of combinations should be 17,576", 17576, combinations.size());

        // Example checks for specific combinations
        assertTrue("The combinations should include 'aaa'", combinations.contains("aaa"));
        assertTrue("The combinations should include 'zzz'", combinations.contains("zzz"));
    }

    @Test
    public void testGenerateCombinations5() {
        List<String> combinations = letterCombos.generateCombinations(5);

        // Adjusted size to 17,576 for 3-letter combinations
        assertEquals("The number of combinations should be 11881376", 11881376, combinations.size());

        // Example checks for specific combinations
        assertTrue("The combinations should include 'aaa'", combinations.contains("aaaaa"));
        assertTrue("The combinations should include 'zzz'", combinations.contains("zzzzz"));
    }
}
