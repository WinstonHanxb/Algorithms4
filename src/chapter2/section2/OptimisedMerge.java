package chapter2.section2;

import chapter2.Example;
import chapter2.section1.InsertionWithSentinel;
import chapter2.section1.Selection;
import com.algs4.stdlib.In;

/**
 * 优化的归并排序
 * 对一般的归并排序进行了性能优化：
 * 1.对长度小于15的数组直接使用插入排序
 * 2.测试要归并的两个数组是否本身已经有序：
 *   已知两个数组，本身是已经有序的，若前一个数组的尾元素比后一个数组的首元素小，则可以确定这两数组已经有序，不需要归并操作
 * 3.不将元素复制到辅助数组（未实现）
 */
public class OptimisedMerge implements Example {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        if (hi - lo <= 15) {
            InsertionWithSentinel.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        if (Example.less(a[mid + 1], a[mid])) {
            merge(a, lo, mid, hi);
        }
    }


    //原地归并
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        //将a[lo..mid] 和 a[mid+1..hi] 归并
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            //左半边归并完了
            if (i > mid) a[k] = aux[j++];
                //右半边归并完了
            else if (j > hi) a[k] = aux[i++];
                //两边均未归并完但右半边比左半边小
            else if (Example.less(aux[j], aux[i])) a[k] = aux[j++];
                //两边均未归并完但左半边比右半边小
            else a[k] = aux[i++];
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
