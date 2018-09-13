import java.util.Random;
import java.util.stream.IntStream;

public class ListGenerator {

    public static int[] getNewList(int size){

        int[] arrayList = new int[size];
        for (int i = 0; i < arrayList.length; i++){
            int x = new Random().nextInt(100) + 1;
            arrayList[i] = x;
        }

        return arrayList;
    }

    public static boolean checkIfSorted(int[] array) {

        for (int i = 1; i < array.length; i++) {
            int currentIndex = array[i-1];
            if (currentIndex > array[i]){
                return false;
            }
        }
        return true;
    }

    public static int[] mergeArrays(int[] arr1, int[] arr2) {
        int arrSize = (arr1.length +  arr2.length);
        int[] mergedArray = new int[arrSize];

        IntStream.range(0, arr1.length).forEachOrdered(i -> mergedArray[i] = arr1[i]);

        IntStream.range(0, arr2.length).forEachOrdered(i -> mergedArray[arr2.length + i] = arr2[i]);

        new SelectionSort(mergedArray).sort(mergedArray);
        return mergedArray;
    }
}
