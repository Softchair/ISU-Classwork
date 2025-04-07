package lab2.part1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class bubbleTest {

    private bubble bubbleSorter;

    @Before
    public void setUp() {
        bubbleSorter = new bubble();
    }

    // TESTS FOR NORMAL BUBBLE SORT --- NON BROKEN
    @Test
    public void testBubbleSort() {
        int[] unsortedArray = {90,  8,  7,  56,  123,  235,  9,  1,  653};
        int[] expectedSortedArray = {1,  7,  8,  9,  56,  90,  123,  235,  653};

        bubbleSorter.bubbleSort(unsortedArray);

        assertArrayEquals(expectedSortedArray, unsortedArray);
    }

    @Test
    public void testBubbleSortEmptyArray() {
        int[] emptyArray = {};

        bubbleSorter.bubbleSort(emptyArray);

        assertArrayEquals(new int[]{}, emptyArray);
    }

    @Test
    public void testBubbleSortSingleElementArray() {
        int[] singleElementArray = {1};

        bubbleSorter.bubbleSort(singleElementArray);

        assertArrayEquals(new int[]{1}, singleElementArray);
    }

    @Test
    public void testBubbleSortAlreadySortedArray() {
        int[] sortedArray = {1,  2,  3,  4,  5,  6,  7};

        bubbleSorter.bubbleSort(sortedArray);

        assertArrayEquals(new int[]{1,  2,  3,  4,  5,  6,  7}, sortedArray);
    }


    //TESTS FOR BROKEN BUBBLE SORT - THAT DO NOT SHOW ERROR
    @Test
    public void testEmptyArray() {
        int[] array = {};
        bubbleSorter.bubbleSortBroke(array);
        assertArrayEquals("Array should be empty after sorting", new int[]{}, array);
    }

    @Test
    public void testSingleElementArray() {
        int[] array = {5};
        bubbleSorter.bubbleSortBroke(array);
        assertArrayEquals("Array should remain unchanged after sorting", new int[]{5}, array);
    }

    @Test
    public void testAscendingOrder() {
        int[] array = {1,  2,  3};
        bubbleSorter.bubbleSortBroke(array);
        assertArrayEquals("Array should be sorted in ascending order", new int[]{1,  2,  3}, array);
    }

    //TESTS FOR BROKEN BUBBLE SORT THAT DO SHOW ERROR

    @Test
    public void testDuplicatesInArray() {
        int[] array = {5,  5,  3,  2,  1};
        bubbleSorter.bubbleSortBroke(array);
        assertArrayEquals("Array with duplicates should be sorted correctly", new int[]{1,  2,  3,  5,  5}, array);
    }

    @Test
    public void testArrayWithDuplicateValues() {
        int[] array = {5,  4,  3,  2,  1,  5,  4,  3,  2,  1};
        bubbleSorter.bubbleSortBroke(array);
        assertTrue("Array with duplicate values should be sorted correctly", isSorted(array));
    }

    //Function for checking tests
    private boolean isSorted(int[] array) {
        for (int i =  0; i < array.length -  1; i++) {
            if (array[i] > array[i +  1]) {
                return false;
            }
        }
        return true;
    }

}
