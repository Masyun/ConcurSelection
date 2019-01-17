public class Main {
    /**
     * Config variables - to manipulate the behaviour of the program
     */
    private static final SortType TYPE = SortType.MULTI; // sort type single, double, multi
    private static final int RUNCOUNT = 5; // amount of times to run the sort - for testing purposes
    public static final int SIZE = 50000; // array size
    public static final int THRESHHOLD = 10000; // thread threshhold
    public static final boolean ADD_INFO = false; // sets whether the program should output the array contents in between runs
    public static final int RAND_BOUND = 10000; // the bound of the possible value returned by the Random object in ListGenerator

    private int[] arr = ListGenerator.getNewList(SIZE);
    private SelectionSort se = new SelectionSort(arr);

    public static void main(String[] args) {
        new Main().run();
    }

    /**
     * Helper method for testing the different sorting methods.
     *
     * @param type of test.
     */
    private void testSort(SortType type) {
        for (int i = 0; i < RUNCOUNT; i++) {
            long lastTime = System.nanoTime();

            switch (type) {
                case SINGLE:
                    System.out.println("Single sort:");
                    SingleThreadSort(se);
                    break;
                case DOUBLE:
                    System.out.println("Two-thread sort");
                    TwoThreadSort(se);
                    break;
                case MULTI:
                    System.out.println("Multi-thread sort");
                    multiThreadedSort(se);
                    break;
                default:
                    System.out.println("No type selected!");
                    break;
            }
            long newTime = System.nanoTime() - lastTime;
            se.printCurrentArray(i, newTime);

            se.resetArray();
        }
    }

    private void run() {
        System.out.println("Starting sorting procedure...\n");
        testSort(TYPE);
    }

    /**
     * Calls the sort method on the main thread.
     *
     * @param selectionSort instance of this class.
     */
    private void SingleThreadSort(SelectionSort selectionSort) {
        int[] arr;
        arr = selectionSort.getArray();
        selectionSort.sort(arr);
    }

    /**
     * Creates two threads, splits up the array, sorts the arrays and merges them again.
     *
     * @param selectionSort instance of this class.
     */
    private void TwoThreadSort(SelectionSort selectionSort) {
        int[] arr;
        arr = selectionSort.getArray();

        int length = arr.length / 2;

        int[] arrFirstHalf = new int[length];
        int[] arrSecondHalf = new int[length];

        for (int i = 0; i < length; i++) {
            arrFirstHalf[i] = arr[i];
        }

        for (int i = 0; i < length; i++) {
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
        } catch (InterruptedException e) {
            System.out.println("Something went wrong when joining threads, stack below: " + '\n');
            e.printStackTrace();
        }finally {
            selectionSort.setArrToSort(ListGenerator.mergeArrays(firstArray.getArray(), secondArray.getArray()));
        }
    }

    /**
     * Calls the multtThreaded sort method for us from the selectionSort.
     *
     * @param selectionSort instance of this class.
     */
    private void multiThreadedSort(SelectionSort selectionSort) {
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
