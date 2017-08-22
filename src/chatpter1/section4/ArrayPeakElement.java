package chatpter1.section4;

import com.algs4.stdlib.StdOut;
import edu.princeton.cs.algs4.In;

/**
 * 给定一个含有N个不同整数的数组，找到一个局部最小的元素
 * <p>
 * <p>
 * 1.已知数组a[n]中最小的值一定是满足条件的 O(n)
 * 2.假设a[0] = a[n+1] = +∞，则可以认为a[1] < a[0]，且a[n] < a[n+1]
 * 由1,2得出结论：设a[n]的一个子数组，a[x...y]若满足 a[x-1] > a[x] 和 a[y+1] > a[y]
 * 则a[x...y]中必然存在一个局部最小值
 */
public class ArrayPeakElement {
    public static int findPeak(int[] a) {
        return a[peakIndex(a, 0, a.length - 1)];
    }

    public static int peakIndex(int[] a, int lo, int hi) {
        int mid = (lo + hi) / 2;
        if (lo + 1 == hi) {
            if (a[lo] > a[hi]) return hi;
            if (a[lo] < a[hi]) return lo;
        }
        if (a[mid] < a[mid + 1] && a[mid] < a[mid - 1]) {
            return mid;
        }
        if (a[mid] < a[mid + 1]) {
            return peakIndex(a, lo, mid);
        }
        if (a[mid] > a[mid + 1]) {
            return peakIndex(a, mid + 1, hi);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = In.readInts("E:\\研究生\\GitHub\\Algorithms4\\resources\\3ints.txt");
        StdOut.println(findPeak(a));
    }
}
