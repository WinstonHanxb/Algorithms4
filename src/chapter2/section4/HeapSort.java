package chapter2.section4;

import chapter2.Example;
import com.algs4.stdlib.In;

public class HeapSort {

    public static void sort(Comparable[] a) {
        int size = a.length;
        for (int i = size / 2; i > 0; i--) {
            sink(a, i, size);
        }
        while (size > 1) {
            exch(a, 1, size--);
            sink(a, 1, size);
        }
    }

    private static void sink(Comparable[] a, int k, int size) {
        while (2 * k  <= size ) {
            int j = 2 * k ;
            if (j + 1 <= size && less(a, j, j + 1)) {
                j++;
            }
            if (!less(a, k, j)) {
                break;
            }
            exch(a, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i-1].compareTo(a[j-1]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = t;
    }

    public static void main(String[] args) {
        String[] a = In.readStrings("resources/words3.txt");
        sort(a);
        if (Example.isSorted(a)) {
            Example.show(a);
        } else {
            System.out.println("wrong");
        }

    }
}
