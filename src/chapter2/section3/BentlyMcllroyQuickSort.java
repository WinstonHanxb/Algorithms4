package chapter2.section3;


import chapter2.Example;
import com.algs4.stdlib.In;

/**
 * dijkstra对快速排序面对重复数据改进后的再改进版本
 * 1.采用三取样切分：针对整个数组，取数组的开头、结尾和中间三个元素，取这三个元素的中间值划分作为切分元素
 * 2.采用Bently-Mcllroy对dijkstra算法的改进：
 * 本质上是将dijkstra与传统快速排序算法相结合，采用四个指针，i,j,p,q
 * a[lo...p-1]和a[q+1...hi]的元素都和v相等（v为切分元素）
 * a[p...i-1]小于v
 * a[j+1...q]大于v
 * 当i和j相遇的时候，交换左右两端到中间
 */
public class BentlyMcllroyQuickSort {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi - lo <= 0) {
            //递归终止
            return;
        }
        setV(a, lo, hi);
        int i = lo;
        int j = hi;
        int p = lo;
        int q = hi;
        Comparable v = a[(lo + hi) / 2];
        for (; ; ) {
            for (; a[i].compareTo(v) <= 0 && i<hi; ) {
                if (a[i].compareTo(v) == 0) {
                    exch(a, i++, p++);
                } else {
                    i++;
                }
            }
            for (; a[j].compareTo(v) >= 0 && j>lo; ) {
                if (a[j].compareTo(v) == 0) {
                    exch(a, j--, q--);
                } else {
                    j--;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        for (int k = lo; k <= hi; k++) {
            //将相等的移到中间去
            if (k < p) {
                exch(a, k, j--);
            }
            if (k > q) {
                exch(a, k, i++);
            }
        }
        sort(a, lo, j);
        sort(a, i, hi);
    }

    //计算切分元素并将切分元素放到hi-1的位置上
    private static void setV(Comparable[] a, int lo, int hi) {
        compareExch(a, lo, (lo + hi) / 2);
        compareExch(a, lo, hi);
        compareExch(a, (lo + hi) / 2, hi);
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void compareExch(Comparable[] a, int i, int j) {
        if (a[i].compareTo(a[j]) >= 0) {
            exch(a, i, j);
        }
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
