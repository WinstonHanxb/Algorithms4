package chapter2.section2;

import chapter2.Example;
import com.algs4.stdlib.In;

/**
 * 归并排序
 */
public class Merge implements Example {
    private static Comparable[] aux;

    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a, 0 , a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a , lo , mid);
        sort(a, mid+1, hi);
        merge(a,lo,mid,hi);
    }


    //原地归并
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        //将a[lo..mid] 和 a[mid+1..hi] 归并
        aux = new Comparable[a.length];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            //左半边归并完了
            if      (i > mid)                     a[k] = aux[j++];
            //右半边归并完了
            else if (j > hi)                       a[k] = aux[i++];
            //两边均未归并完但右半边比左半边小
            else if (Example.less(aux[j], aux[i])) a[k] = aux[j++];
            //两边均未归并完但左半边比右半边小
            else                                   a[k] = aux[i++];
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
