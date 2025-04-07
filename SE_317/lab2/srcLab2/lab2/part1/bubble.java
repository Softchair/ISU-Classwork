package lab2.part1;

public class bubble {

    // Method to perform Bubble Sort
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i =  0; i < n -  1; i++) {
            for (int j =  0; j < n - i -  1; j++) {
                if (array[j] > array[j +  1]) {
                    // Swap array[j] and array[j+1]
                    int temp = array[j];
                    array[j] = array[j +  1];
                    array[j +  1] = temp;
                }
            }
        }
    }

    // Method to perform Bubble Sort
//    public static void bubbleSortBroke(int[] array) {
//        int n = array.length;
//        for (int i =  0; i < n -  1; i++) {
//            for (int j =  0; j < n - i -  2; j++) { //Fault on this line
//                if (array[j] > array[j +  1]) {
//                    // Swap array[j] and array[j+1]
//                    int temp = array[j];
//                    array[j] = array[j +  1];
//                    array[j +  1] = temp;
//                }
//            }
//        }
//    }

    public static void bubbleSortBroke(int[] array) {
        int n = array.length;
        for (int i =  0; i < n -  1; i++) {
            for (int j =  0; j < n - i -  1; j++) { //Changed -2 to -1 to get the whole array
                if (array[j] > array[j +  1]) {
                    // Swap array[j] and array[j+1]
                    int temp = array[j];
                    array[j] = array[j +  1];
                    array[j +  1] = temp;
                }
            }
        }
    }

    // Method to print the array
    public static void printArray(int[] array) {
        for (int i =  0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
