package lab2.part3;

public class merge {
    // Function to print the array
    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    //GOOD MERGE SORT FUNCTIONS ---
    public static int[] sort(int[] array) {
        mergeSort(array,  0, array.length -  1);
        return array;
    }

    // Merge sort algorithm GOOD
    public static void mergeSort(int[] array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int middleIndex = (leftIndex + rightIndex) /  2;
            mergeSort(array, leftIndex, middleIndex); //Calling the merge sort on the two halfs of array
            mergeSort(array, middleIndex +  1, rightIndex);
            merge(array, leftIndex, middleIndex, rightIndex); //Merging the array at the end
        }
    }

    // Merge function to combine two sorted subarrays
    public static void merge(int[] array, int leftIndex, int middleIndex, int rightIndex) {
        int n1 = middleIndex - leftIndex +  1;
        int n2 = rightIndex - middleIndex;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i =  0; i < n1; ++i) {
            leftArray[i] = array[leftIndex + i];
        }
        for (int j =  0; j < n2; ++j) {
            rightArray[j] = array[middleIndex +  1 + j];
        }

        int i =  0, j =  0;
        int k = leftIndex;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
    //END GOOD MERGE SORT FUNCTIONS

    //BAD MERGE SORT FUNCTIONS
//    public static int[] sortBad(int[] array) {
//        mergeSortBad(array,  0, array.length -  1);
//        return array;
//    }
//
//    public static void mergeSortBad(int[] array, int leftIndex, int rightIndex) {
//        if (leftIndex < rightIndex) {
//            int middleIndex = (leftIndex + rightIndex) /  2;
//            mergeSortBad(array, leftIndex, middleIndex); //Calling the merge sort on the two halfs of array
//            mergeSortBad(array, middleIndex +  1, rightIndex);
//            mergeBad(array, leftIndex, middleIndex, rightIndex); //Merging the array at the end
//        }
//    }
//
//    // Merge function to combine two sorted subarrays
//    public static void mergeBad(int[] array, int leftIndex, int middleIndex, int rightIndex) {
//        int n1 = middleIndex - leftIndex +  1;
//        int n2 = rightIndex - middleIndex;
//
//        int[] leftArray = new int[n1];
//        int[] rightArray = new int[n2];
//
//        for (int i =  0; i < n1; ++i) {
//            leftArray[i] = array[leftIndex + i];
//        }
//        for (int j =  0; j < n2; ++j) {
//            rightArray[j] = array[middleIndex +  1 + j];
//        }
//
//        int i =  0, j =  1;
//        int k = leftIndex;
//
//        while (i < n1 && j < n2) {
//            if (leftArray[i] <= rightArray[j]) {
//                array[k] = leftArray[i];
//                i++;
//            } else {
//                array[k] = rightArray[j];
//                j++;
//            }
//            k++;
//        }
//
//        while (i < n1) {
//            array[k] = leftArray[i];
//            i++;
//            k++;
//        }
//
//        while (j < n2) {
//            array[k] = rightArray[j];
//            j++;
//            k++;
//        }
//    }

    //BAD MERGE SORT FUNCTIONS BUT ITS WORKING NOW
    public static int[] sortBad(int[] array) {
        mergeSortBad(array,  0, array.length -  1);
        return array;
    }

    public static void mergeSortBad(int[] array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int middleIndex = (leftIndex + rightIndex) /  2;
            mergeSortBad(array, leftIndex, middleIndex); //Calling the merge sort on the two halfs of array
            mergeSortBad(array, middleIndex +  1, rightIndex);
            mergeBad(array, leftIndex, middleIndex, rightIndex); //Merging the array at the end
        }
    }

    // Merge function to combine two sorted subarrays
    public static void mergeBad(int[] array, int leftIndex, int middleIndex, int rightIndex) {
        int n1 = middleIndex - leftIndex +  1;
        int n2 = rightIndex - middleIndex;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i =  0; i < n1; ++i) {
            leftArray[i] = array[leftIndex + i];
        }
        for (int j =  0; j < n2; ++j) {
            rightArray[j] = array[middleIndex +  1 + j];
        }

        int i =  0, j =  0;
        int k = leftIndex;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
