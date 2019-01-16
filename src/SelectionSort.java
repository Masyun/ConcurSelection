public class SelectionSort implements Runnable {

    private int[] arrToSort;
    private boolean flag;

    /**
     * Constuctor class, flag set to true.
     * When this constructor is called it will always fall through the recursive sort method.
     *
     * @param arr to be sorted.
     */
    public SelectionSort(int[] arr) {
        this.arrToSort = arr;
        flag = true;
    }

    /**
     * Overloaded constructor, has an option for flag.
     *
     * @param arr  to be sorted.
     * @param flag to help with setting recursive sorting method vs non-recursive.
     */
    public SelectionSort(int[] arr, boolean flag) {
        this.arrToSort = arr;
        this.flag = flag;
    }

    /**
     * Selection sort method.
     *
     * @param input to be sorted.
     */
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

    /**
     * Makes threads, splits the array, and merges the result at the end.
     *
     * @param arr to be sorted.
     */
    public void recursiveSort(int[] arr) {
        int[] arr1 = leftHalf(arr);
        int[] arr2 = rightHalf(arr);

        if (arr.length > Main.THRESHHOLD && flag) {

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
            } finally {
                setArrToSort(ListGenerator.mergeArrays(ss1.getArray(), ss2.getArray()));
            }
        } else {
            sort(arr);
        }
    }

    public int[] getArray() {
        return arrToSort;
    }

    /**
     * Helper method for splitting array.
     *
     * @param array to be split.
     * @return half an array.
     */
    private int[] leftHalf(int[] array) {
        int size1 = array.length / 2;
        int[] left = new int[size1];
        for (int i = 0; i < size1; i++) {
            left[i] = array[i];
        }
        return left;
    }

    /**
     * Helper method for splitting array.
     *
     * @param array to be split.
     * @return half an array.
     */
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

    /**
     * Helper method for printing an array with possible additional info about the array
     */
    public void printCurrentArray(int iter, long newTime) {
        System.out.println("Size: " + getArray().length);
        if (Main.ADD_INFO) {
            for (int anArrToSort : arrToSort) {
                System.out.print(anArrToSort + ", ");
            }
        }

        if (ListGenerator.checkIfSorted(getArray())) {
            System.out.println("\n> The array is sorted!");
        } else {
            System.out.println("\n> The array is NOT sorted!");
        }
        System.out.print(iter + 1 + ". Time: " + newTime / 1000000 + " MS\n");
    }

    public void printCurrentArray() {
        System.out.println("Size: " + getArray().length);
        for (int anArrToSort : arrToSort) {
            System.out.print(anArrToSort + ", ");
        }

        if (ListGenerator.checkIfSorted(getArray())) {
            System.out.println("\n> The array is sorted!");
        } else {
            System.out.println("\n> The array is NOT sorted!");
        }
    }

    /**
     * Helper method to clear the array.
     */
    public void resetArray() {
        setArrToSort(ListGenerator.getNewList(Main.SIZE));
    }

    @Override
    public void run() {
        recursiveSort(arrToSort);
    }
}
