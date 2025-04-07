package lab2.part2;

import java.util.Arrays;

public class quick {

    //GOOD QUICK SORT
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high); //Define pivot point

            quickSort(array, low, pivotIndex -  1); //sort the bottom half
            quickSort(array, pivotIndex +  1, high); //sort the upper half
        }
    }

    //Cutting the array in half to find the rotation point
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low -  1;

        for (int j = low; j < high; j++) { //find the pivot point
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i +  1, high); //Swap
        return i +  1;
    }

    //Simple function to swap two points
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

//    //BAD QUICK SORT
//    public static void quickSortBad(int[] array, int low, int high) {
//        if (low < high) {
//            int pivotIndex = partition(array, low, high); //Define pivot point
//
//            quickSort(array, high, pivotIndex -  1); //sort the bottom half
//            quickSort(array, pivotIndex +  1, low); //sort the upper half
//        }
//    }

    //BAD but correct QUICK SORT
    public static void quickSortBad(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high); //Define pivot point

            quickSort(array, low, pivotIndex -  1); //sort the bottom half
            quickSort(array, pivotIndex +  1, high); //sort the upper half
        }
    }
}
