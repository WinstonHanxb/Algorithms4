package chapter1.section4;


import com.algs4.stdlib.StdOut;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;

/**
 * 线性级别的统计两整数和为零的算法
 */
public class TowSumFaster {

    //数组是已经排序好的数组
    public static int count(int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        int cnt = 0;

        while (lo <= hi){
            if (a[lo] + a[hi] < 0) {
                lo++;
                continue;
            }
            if (a[lo] + a[hi] == 0) {
                cnt++;
                lo++;
                continue;
            }
            if (a[lo] + a[hi] >0){
                hi--;
                continue;
            }
        }

        return cnt;
    }

    public static int countRe(int[] a){
        int cnt = 0;
        for (int i = 0; i < a.length-1; i++) {
            for (int j = i + 1; j < a.length-1; j++) {
                if(a[i] + a[j] == 0){
                    cnt ++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] a= In.readInts("E:\\研究生\\GitHub\\Algorithms4\\resources\\8Kints.txt");
        Arrays.sort(a);
        StdOut.println(countRe(a));
        StdOut.println(count(a));
    }
}
