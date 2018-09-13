import java.util.ArrayList;

public class SelectionSort implements Runnable{


    private int[] arrToSort;

    public SelectionSort(int[] arr) {
        this.arrToSort = arr;
    }

    public void sort(int[] input) {
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

    public void recursiveSort(int[] arr) {

        if(arr.length > 1000) {

            int[] arr1 = new int[arr.length / 2];
            int[] arr2 = new int[arr.length / 2];

            System.arraycopy(arr, 0, arr1, 0, arr1.length);
            System.arraycopy(arr, arr1.length, arr2, 0, arr2.length);

            SelectionSort ss1 = new SelectionSort(arr1);
            SelectionSort ss2 = new SelectionSort(arr2);

            Thread t1 = new Thread(ss1);
            Thread t2 = new Thread(ss2);

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread() + " -- Something went wrong when joining thread. Stack below :");
                e.printStackTrace();
            }

            arrToSort = mergeArrays(ss1.getArray(), ss2.getArray());

        } else {
            sort(arr);
        }
    }

    public int[] mergeArrays(int[] arr1, int[] arr2) {
        int arrSize = (arr1.length +  arr2.length);
        int[] mergedArray = new int[arrSize];

        for(int i = 0; i < arr1.length; i++) {
            mergedArray[i] = arr1[i];
        }

        for(int i = 0; i < arr2.length; i++) {
            mergedArray[arr2.length + i] = arr2[i];
        }

        sort(mergedArray);
        return mergedArray;
    }

    public int[] getArray() {
        return arrToSort;
    }


    @Override
    public void run() {
        System.out.println("Thread: " + Thread.currentThread().getName());
        recursiveSort(arrToSort);
    }
}
