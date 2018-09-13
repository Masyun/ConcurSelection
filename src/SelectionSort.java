import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SelectionSort implements Runnable{


    private int[] arrToSort;
    private boolean flag;


    public SelectionSort(int[] arr) {
        this.arrToSort = arr;
        flag = true;
    }

    public SelectionSort(int[] arr, boolean flag){
        this.arrToSort = arr;
        this.flag = flag;
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
        int[] arr1;
        int[] arr2;

        if(arr.length > Main.THRESHHOLD && flag) {
            if (arr.length%2 == 0){ // even
                arr1 = new int[arr.length / 2];
            }else{
                arr1 = new int[(arr.length / 2)+1];
            }

            arr2 = new int[arr.length / 2];
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

            arrToSort = ListGenerator.mergeArrays(ss1.getArray(), ss2.getArray());

        } else {
            sort(arr);
        }
    }

    public int[] getArray() {
        return arrToSort;
    }

    public void setArrToSort(int[] arrToSort) {
        this.arrToSort = arrToSort;
    }

    public void printCurrentArray(){
        for (int i = 0; i < arrToSort.length; i++) {
            System.out.print(arrToSort[i] + ", ");
        }
    }

    private int[] getNewList(int size){

        int[] arrayList = new int[size];
        for (int i = 0; i < arrayList.length; i++){
            int x = new Random().nextInt(100) + 1;
            arrayList[i] = x;
        }

        return arrayList;
    }

    public void resetArray(){
        setArrToSort(getNewList(Main.SIZE));
    }

    @Override
    public void run() {
        recursiveSort(arrToSort);
    }
}
