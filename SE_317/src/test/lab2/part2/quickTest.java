package lab2.part2;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class quickTest {

    //TESTS FOR GOOD QUICK SORT
    @Test
    public void testQuickSort_FirstArray() {
        int[] input = {3,  2,  6,  8,  1,  4,  7};
        int[] expected = {1,  2,  3,  4,  6,  7,  8};

        quick.quickSort(input,  0, input.length -  1);

        assertArrayEquals(expected, input);
    }

    @Test
    public void testQuickSort_SecondArray() {
        int[] input = {5,  9,  1,  6,  7,  8,  2};
        int[] expected = {1,  2,  5,  6,  7,  8,  9};

        quick.quickSort(input,  0, input.length -  1);

        assertArrayEquals(expected, input);
    }

    //TESTS FOR BAD QUICK SORT
    @Test
    public void testBadQuickSort_SmallArraySize() {
        int[] input = {3,  2,  1};
        int[] expected = {1,  2,  3};

        quick.quickSortBad(input,  0, input.length -  1);

        // Even though the algorithm is incorrect, this test might pass because
        // the array size is small and the error might not manifest itself clearly.
        assertArrayEquals(expected, input);
    }

    @Test
    public void testBadQuickSort_AlreadySortedArray() {
        int[] input = {1,  2,  3,  4,  5,  6,  7}; // Already sorted array
        int[] expected = {1,  2,  3,  4,  5,  6,  7}; // Expected result remains the same

        quick.quickSortBad(input,   0, input.length -   1);

        // Assert that the input array matches the expected array
        assertArrayEquals(expected, input);
    }

    //TESTS THAT DO SHOW THE ERROR IN QUICK SORT
    @Test
    public void testBadQuickSort() {
        int[] input = {1,   3,  2,   4,   5,   6,   7}; // An array already sorted in ascending order
        int[] expected = {1,   2,   3,   4,   5,   6,   7}; // Expected result remains the same

        quick.quickSortBad(input,   0, input.length -   1);

        // The expected array is adjusted to reflect the incorrect behavior of the bad QuickSort
        // Since the array is already sorted, the incorrect recursive calls won't affect the order
        assertArrayEquals(expected, input);
    }

    @Test
    public void testBadQuickSort_2() {
        int[] input = {7,   6,   5,   4,   3,   2,   1}; // An array already sorted in descending order
        int[] expected = {1,   2,   3,   4,   5,   6,   7}; // Expected result after sorting

        quick.quickSortBad(input,   0, input.length -   1);

        // The expected array is adjusted to reflect the incorrect behavior of the bad QuickSort
        // The incorrect recursive calls will lead to the entire array being reversed, but the order will be correct
        assertArrayEquals(expected, input);
    }





}

