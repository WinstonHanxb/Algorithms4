package chapter2.section1;

import chapter2.Example;
import com.algs4.stdlib.In;

/**
 * 使用另一个序列的希尔排序
 */
public class Shellv2 implements Example {
    static final int[] sequence = {1, 5, 19, 41, 109, 209, 505, 929, 2161, 3905, 8929, 16001, 36289, 64769,
            146305,
            260609};

    public static void sort(Comparable[] a) {
        int sIndex = findSequenceIndex(a.length);
        while(sIndex>0){
            for (int i = sIndex; i < a.length ; i++) {
                //针对每一个序列，使用插入排序
                Comparable temp = a[i];
                int j = i;
                for (; j >= sIndex && a[j-sIndex].compareTo(temp)>=0; ) {
                    //交换两个数组的行为需要两次写入操作，直接将比较大向右移动可以减少一次写入数组的操作
                    a[j] = a[j-sIndex];
                    j-=sIndex;
                }
                a[j]=temp;
            }
            sIndex--;
        }
    }

    private static int findSequenceIndex(int length) {
        int i = 0;
        while (sequence[i] < length / 3) {
            i++;
        }
        return i;
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
