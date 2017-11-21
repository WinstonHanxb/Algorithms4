package chapter2.section1;

import chapter2.Example;
import com.algs4.stdlib.In;

/**
 * 选择排序
 *
 */
public class Selection implements Example {
    public static void sort(Comparable[] a){
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i; j < a.length; j++) {
                if(Example.less(a[j],a[min])) min=j;
            }
            Example.exch(a,i,min);
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
