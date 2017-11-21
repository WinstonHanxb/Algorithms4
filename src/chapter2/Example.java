package chapter2;

import com.algs4.stdlib.In;
import com.algs4.stdlib.StdOut;

/**
 * 排序算法模板
 * 模板的默认函数按照升序排列
 */
public interface Example {

    static boolean less(Comparable first, Comparable next){
        return first.compareTo(next)  < 0;
    }

    static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a [j];
        a[j] = t;
    }

    static void show(Comparable[] a){
        //打印数组
        for (int i=0; i<a.length; i++ ){
            StdOut.print(a[i]+" ");
        }
        StdOut.println();
    }

    static boolean isSorted(Comparable[] a){
        for (int i = 1; i < a.length; i++) {
            if(less(a[i],a[i-1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = In.readStrings();
        assert isSorted(a);
        show(a);
    }
}
