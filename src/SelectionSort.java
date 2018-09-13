import java.util.ArrayList;

public class SelectionSort implements Runnable{


    private int[] arrToSort;

    public SelectionSort(int[] arr) {
        this.arrToSort = arr;
    }

    public void sort(int[] arr) {
        int arrLength = arr.length;

        for(int i = 0; i < arrLength - 1; i++) {
            int min = i;
            for(int j = i + 1; j < arrLength; j++) {
                if(arr[j] < arr[min]) {
                    min = j;
                }
                int smallerNumber = arr[i];
                arr[i] = arr[min];
                arr[min] = smallerNumber;
            }
        }
        arrToSort = arr;
    }

    public void sort2(int[] input) {
        int inputLength = input.length;

        for (int i = 0; i < inputLength - 1; i++) {

            int min = i;

            for (int j = i + 1; j < inputLength; j++) {
                if (input[j] < input[min]) {
                    min = j;
                }
            }

            int temp = input[i];
            input[i] = input[min];
            input[min] = temp;

        }

    }

    public static int[] doSelectionSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[index])
                    index = j;

            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
        return arr;
    }

    public int[] getArray() {
        return arrToSort;
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " run():");
        sort2(arrToSort);
    }
}
