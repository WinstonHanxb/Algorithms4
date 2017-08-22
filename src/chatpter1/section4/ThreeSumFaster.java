package chatpter1.section4;

import com.algs4.stdlib.StdOut;
import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class ThreeSumFaster {

    public static int count(int[] a) {
        Arrays.sort(a);//NlogN
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int lo = i + 1;
            int hi = N - 1;
            while (lo < hi) {
                if (a[i] + a[lo] + a[hi] < 0) {
                    lo++;
                    continue;
                }
                if (a[i] + a[lo] + a[hi] == 0) {
                    cnt++;
                    lo++;
                    hi--;
                    continue;
                }
                if (a[i] + a[lo] + a[hi] > 0) {
                    hi--;
                    continue;
                }
            }
        }
        return cnt;
    }



    public static void main(String[] args) {
        int[] a = In.readInts("E:\\研究生\\GitHub\\Algorithms4\\resources\\8Kints.txt");
        Arrays.sort(a);

        StdOut.println(count(a));
    }


}
