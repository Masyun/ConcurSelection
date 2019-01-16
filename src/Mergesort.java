/**
 * Created by Yunus on 16-1-2019.
 */
public class Mergesort {

    private static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0;
        for (int k = 0; k < c.length; k++) {
            if      (i >= a.length) c[k] = b[j++];
            else if (j >= b.length) c[k] = a[i++];
            else if (a[i] <= b[j])  c[k] = a[i++];
            else                    c[k] = b[j++];
        }
        return c;
    }

    public static int[] mergesort(int[] input) {
        int N = input.length;
        if (N <= 1){
            return input;
        }
        int[] a = new int[N/2];
        int[] b = new int[N - N/2];

        System.arraycopy(input, 0, a, 0, a.length);
        System.arraycopy(input, N / 2, b, 0, b.length);


        return merge(mergesort(a), mergesort(b));
    }

}
