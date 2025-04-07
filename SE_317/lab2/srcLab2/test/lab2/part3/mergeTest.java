package lab2.part3;

// MergeSortTest.java
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class mergeTest {
    private merge mergeSort;


    //GOOD MERGE SORT TEST CASES
    @Before
    public void setUp() {
        mergeSort = new merge();
    }

    @Test
    public void testSortEmptyArray() {
        int[] input = {};
        int[] expected = {};
        Assert.assertArrayEquals(expected, mergeSort.sort(input));
    }

    @Test
    public void testSortSingleElementArray() {
        int[] input = {5};
        int[] expected = {5};
        Assert.assertArrayEquals(expected, mergeSort.sort(input));
    }

    @Test
    public void testSortMultipleElementsArray() {
        int[] input = {3,  1,  4,  1,  5,  9,  2};
        int[] expected = {1,  1,  2,  3,  4,  5,  9};
        Assert.assertArrayEquals(expected, mergeSort.sort(input));
    }

    //BAD MERGE SORT TEST CASES THAT PASS

    @Test
    public void testSortWithIgnoreFirstRightElement() {
        int[] input = {2,  2,  2,  2,  2,  2,  3};
        int[] expected = {2,  2,  2,  2,  2,  2,  3};
        Assert.assertArrayEquals(expected, mergeSort.sortBad(input));
    }

    //BAD MERGE TESTS THAT DONT PASS
    @Test
    public void testSortWithRandomNumbers() {
        int[] input = {7,  3,  5,  1,  8,  2,  6};
        int[] expected = {1,  2,  3,  5,  6,  7,  8};
        Assert.assertArrayEquals(expected, mergeSort.sortBad(input));
    }

    @Test
    public void testSortWithDescendingOrder() {
        int[] input = {8,  7,  6,  5,  4,  3,  2,  1};
        int[] expected = {1,  2,  3,  4,  5,  6,  7,  8};
        Assert.assertArrayEquals(expected, mergeSort.sortBad(input));
    }
}

