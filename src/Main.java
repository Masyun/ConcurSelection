import java.util.List;

public class Main{

    private int[] arr = ListGenerator.getNewList(100);
    private SelectionSort se = new SelectionSort(arr);


    public static void main(String[] args) {
        new Main().run();

    }

    public void run() {
        ListGenerator.printArray(se.getArray());
//      SingleThreadSort(se.getArray());


        int[] sortedArr = TwoThreadSort(se.getArray());

        ListGenerator.printArray(sortedArr);

        if (ListGenerator.checkIfSorted(sortedArr)){
            System.out.println();
            System.out.println("THE ARRAY IS FUCKING SORTED G");
        }
    }

    public void SingleThreadSort(int[] arrToSort) {
        System.out.println("\nSORTING....");
        int[] arr;
        arr = arrToSort;
        se.sort2(arr);
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

        se.sort2(mergedArray);
        return mergedArray;
    }

    public int[] TwoThreadSort(int[] arrToSort) {
        System.out.println("\nSORTING....");
        int[] arr;
        arr = arrToSort;

        int length = arr.length / 2;

        int[] arrFirstHalf = new int[length];
        int[] arrSecondHalf = new int[length];

        for(int i = 0; i < length; i++) {
            arrFirstHalf[i] = arr[i];
        }

        for(int i = 0; i < length; i++) {
            arrSecondHalf[i] = arr[length + i];
        }

        SelectionSort firstArray = new SelectionSort(arrFirstHalf);
        SelectionSort secondArray = new SelectionSort(arrSecondHalf);

        Thread t1 = new Thread(firstArray);
        Thread t2 = new Thread(secondArray);

        t1.run();
        t2.run();

        try {
            t1.join();
            t2.join();
        } catch(InterruptedException e) {
            System.out.println("Something went wrong when joining threads, stack below: " + '\n');
            e.printStackTrace();
        }

        return mergeArrays(firstArray.getArray(), secondArray.getArray());
    }

}
