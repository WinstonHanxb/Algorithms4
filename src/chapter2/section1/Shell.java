package chapter2.section1;

import chapter2.Example;
import com.algs4.stdlib.In;

/**
 * 希尔排序
 * 希尔排序权衡了子数组的规模和有序性
 * 排序之初，虽然各个子数组的顺序很乱，但是各个数组很短
 * 逐渐增加子数长度的过程中，各个数组均是一个部分有序的数组，因此使用插入排序的效率很高
 */
public class Shell implements Example {
    public static void sort(Comparable[] a){
        int h = findMaxH(a.length);
        while( h >= 1){
            for (int i = h; i < a.length; i++) {
                //针对每一个序列，使用插入排序
                Comparable temp = a[i];
                int j = i;
                for (; j >= h && a[j-h].compareTo(temp)>=0; ) {
                    //交换两个数组的行为需要两次写入操作，直接将比较大向右移动可以减少一次写入数组的操作
                    a[j] = a[j-h];
                    j-=h;
                }
                a[j]=temp;
            }

            h /= 3;
        }

    }

    private static int findMaxH(int length){
        int h = 1;
        while(h < length/3) h = h*3 + 1 ;
        return h;
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
