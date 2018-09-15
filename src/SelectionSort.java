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
        int[] arr1 = leftHalf(arr);
        int[] arr2 = rightHalf(arr);

        if(arr.length > Main.THRESHHOLD && flag) {

//            System.arraycopy(arr, 0, arr1, 0, arr1.length);
//            System.arraycopy(arr, arr1.length, arr2, 0, arr2.length);
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

    private int[] leftHalf(int[] array) {
        int size1 = array.length / 2;
        int[] left = new int[size1];
        for (int i = 0; i < size1; i++) {
            left[i] = array[i];
        }
        return left;
    }

    private int[] rightHalf(int[] array) {
        int size1 = array.length / 2;
        int size2 = array.length - size1;
        int[] right = new int[size2];
        for (int i = 0; i < size2; i++) {
            right[i] = array[i + size1];
        }
        return right;
    }

    public void setArrToSort(int[] arrToSort) {
        this.arrToSort = arrToSort;
    }

    public void printCurrentArray(){
        System.out.println("Size: " + getArray().length);
        for (int i = 0; i < arrToSort.length; i++) {
            System.out.print(arrToSort[i] + ", ");
        }

        if (ListGenerator.checkIfSorted(getArray())){
            System.out.println("\n> The array is sorted!");
        }else{
            System.out.println("\n> The array is NOT sorted!");
        }
    }

    public void resetArray(){
        setArrToSort(ListGenerator.getNewList(Main.SIZE));
    }

    @Override
    public void run() {
        recursiveSort(arrToSort);
    }
}
