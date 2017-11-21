package chapter2.section1;

import chapter2.Example;
import com.algs4.stdlib.In;

/**
 * 插入排序
 * 在一般情况下，需要排序的序列通常都为部分有序的序列（也就是序列的倒置相对完全无序的序列更少）
 * 插入排序的面对部分有序的序列能提供更好的性能
 */
public class Insertion implements Example {
    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            Comparable temp = a[i];
            int j = i ;
            for (;j > 0 && a[j-1].compareTo(temp)>=0; ) {
                //交换两个数组的行为需要两次写入操作，直接将比较大向右移动可以减少一次写入数组的操作
                a[j] = a[j-1];
                j--;
            }
            a[j] = temp;
        }
    }

    public static void main(String[] args) {
        String[] a = In.readStrings("resources/words3.txt");
        sort(a);
        if (!Example.isSorted(a)) {
            System.out.println("wrong");
        }
        Example.show(a);
    }
}
