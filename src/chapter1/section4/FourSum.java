package chapter1.section4;

import chapter1.section1.BinarySearch;

import java.util.Arrays;

public class FourSum {

    /**
     * 统计输入矩阵中有多少和为零的四个整数对
     * @param a
     * @return
     */
    public static int count(int[] a) {
        int N = a.length;
        int cnt = 0;

        Arrays.sort(a);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    int temp = BinarySearch.rank(-(a[i] + a[j] + a[k]),a);
                    if(temp > k){
                        cnt++;
                    }
                }
            }
        }

        return cnt;
    }



}
