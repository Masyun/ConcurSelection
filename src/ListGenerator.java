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

    public static void printArray(int[] unsortedList){

        System.out.println("Array(" + unsortedList.length + ")" + " contents:");
        for(int i = 0; i < unsortedList.length; i++){
            System.out.print(unsortedList[i] + ", ");
        }
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
}
