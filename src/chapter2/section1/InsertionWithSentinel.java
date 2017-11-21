package chapter2.section1;


import chapter2.Example;
import chapter2.section3.BentlyMcllroyQuickSort;
import com.algs4.stdlib.In;

/**
 * 插入排序的一开始先找出最小的元素并且将其置于数组的最左边
 * 排序过程中的j>0因此可以避免
 * 减少了大量的比较过程可以提高性能
 */
public class InsertionWithSentinel implements Example {
    public static void sort(Comparable[] a) {
        sort(a,0,a.length-1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        setSentinel(a, lo, hi);
        for (int i = lo + 1; i <= hi; i++) {
            Comparable temp = a[i];
            int j=i;
            for (; a[j-1].compareTo(temp)>=0;) {
                //交换两个数组的行为需要两次写入操作，直接将比较大向右移动可以减少一次写入数组的操作
                a[j] = a[j - 1];
                j--;
            }
            a[j] = temp;
        }
    }

    private static void setSentinel(Comparable[] a, int lo, int hi) {
        int min = lo;
        for (int i = lo; i <= hi; i++) {
            if (Example.less(a[i], a[min])) min = i;
        }
        Example.exch(a, lo, min);
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
