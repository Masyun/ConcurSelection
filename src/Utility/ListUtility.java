package Utility;

import java.util.Random;

public class ListUtility {

    /**
     * Generates a new array according to the specified size.
     *
     * @param size of the array.
     * @return the newly generated array.
     */
    public static int[] getNewList(int size, int bound) {
        int[] arrayList = new int[size];
        for (int i = 0; i < arrayList.length; i++) {
            int x = new Random().nextInt(bound) + 1; // zero exclusive
            arrayList[i] = x;
        }

        return arrayList;
    }

    /**
     * Checks if the array is sorted.
     *
     * @param array the array to be checked.
     * @return a boolean value representing the order state of the array.
     */
    public static boolean checkIfSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int currentIndex = array[i - 1];
            if (currentIndex > array[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Merges two arrays together.
     * First allocates enough space for both arrays to fit in, in a third array
     * Where both arrays get concatenated in this array, followed by an in-place merge-sort
     *
     * @param arr1 first half of the initial array.
     * @param arr2 second half of the second array.
     * @return a new sorted and array containing all elements of arr1 & arr2.
     */
    public static int[] mergeArrays(int[] arr1, int[] arr2) {
        int arrSize = (arr1.length + arr2.length);
        int[] mergedArray = new int[arrSize];

        System.arraycopy(arr1, 0, mergedArray, 0, arr1.length);
        System.arraycopy(arr2, 0, mergedArray, arr1.length, arr2.length);

        return Mergesort.mergesort(mergedArray);
    }
}
