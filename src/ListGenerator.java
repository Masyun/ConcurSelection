import java.util.Random;

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

        int bound = arr1.length;
        for (int i1 = 0; i1 < bound; i1++) {
            mergedArray[i1] = arr1[i1];
        }

        int bound1 = arr2.length;
        for (int i = 0; i < bound1; i++) {
            mergedArray[arr1.length + i] = arr2[i];
        }

        new SelectionSort(mergedArray).sort(mergedArray);
        return mergedArray;
    }
}
