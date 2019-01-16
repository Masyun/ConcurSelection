public class Main{

    public static final int THRESHHOLD = 100; // threshhold
    public static final int SIZE = 1000; // array size

    private static final int TYPE = 3; // sort type 1 = single, 2 = double, 3 = multi
    private static final int RUNCOUNT = 1; // amount of times to run the sort - for testing purposes

    private int[] arr = ListGenerator.getNewList(SIZE);
    private SelectionSort se = new SelectionSort(arr);


    public static void main(String[] args) {
        new Main().run();
    }

    /**
     * Helper method for testing the different sorting methods.
     * @param type of test.
     */
    private void testSort(int type) {
        for (int i = 0; i < RUNCOUNT; i++) {
            long lastTime = System.nanoTime();
            System.out.println();
            switch(type){
                case 1:
                    System.out.println("Single sort:");
                    SingleThreadSort(se);
                    break;
                case 2:
                    System.out.println("Two-thread sort");
                    TwoThreadSort(se);
                    break;
                case 3:
                    System.out.println("Multi-thread sort");
                    multiThreadedSort(se);
                    break;
                default:
                    System.out.println("No type selected!");
            }
            long newTime = System.nanoTime() - lastTime;
            System.out.print(i + 1 + ". Time: " + newTime / 1000000 + " MS\n");

//            se.printCurrentArray();

            se.resetArray();
        }
    }

    private void run() {
        se.printCurrentArray();
        System.out.println("\nStarting sorting procedure...");
        testSort(TYPE);
    }

    /**
     * Calls the sort method on the main thread.
     * @param selectionSort instance of this class.
     */
    private void SingleThreadSort(SelectionSort selectionSort) {
        int[] arr;
        arr = selectionSort.getArray();
        selectionSort.sort(arr);
    }

    /**
     * Creates two threads, splits up the array, sorts the arrays and merges them again.
     * @param selectionSort instance of this class.
     */
    private void TwoThreadSort(SelectionSort selectionSort) {
        int[] arr;
        arr = selectionSort.getArray();

        int length = arr.length / 2;

        int[] arrFirstHalf = new int[length];
        int[] arrSecondHalf = new int[length];

        for(int i = 0; i < length; i++) {
            arrFirstHalf[i] = arr[i];
        }

        for(int i = 0; i < length; i++) {
            arrSecondHalf[i] = arr[length + i];
        }

        SelectionSort firstArray = new SelectionSort(arrFirstHalf, false);
        SelectionSort secondArray = new SelectionSort(arrSecondHalf, false);

        Thread t1 = new Thread(firstArray);
        Thread t2 = new Thread(secondArray);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch(InterruptedException e) {
            System.out.println("Something went wrong when joining threads, stack below: " + '\n');
            e.printStackTrace();
        }

        selectionSort.setArrToSort(ListGenerator.mergeArrays(firstArray.getArray(), secondArray.getArray()));
    }

    /**
     * Calls the multtThreaded sort method for us from the selectionSort.
     * @param selectionSort instance of this class.
     */
    private void multiThreadedSort(SelectionSort selectionSort){
        System.out.println("SORTING...");
        Thread t = new Thread(selectionSort);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
