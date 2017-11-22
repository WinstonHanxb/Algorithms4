package chapter2.section4;

import chapter2.Example;
import com.algs4.stdlib.In;

public class HeapSort {

    public static void sort(Comparable[] a) {
        int size = a.length;
        //堆排序建堆的过程
        //虽然也可以从左往右遍历数组，但是简便的把整个数组理解成为一个没排序的堆
        //因此从右往左保证一个个子树是有序的，最终一定能保证整个数组是有序的
        //由二叉树的性质，size/2往后的一定全部都是叶节点，或者说是只有一个节点的子数，必然是有序的
        //因此从size/2往左排序，将所有的不只一个节点的子树整理有序即完成了建堆
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
