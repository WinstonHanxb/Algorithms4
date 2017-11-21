package chapter2;

import chapter2.section1.*;
import chapter2.section2.Merge;
import chapter2.section2.NaturalMerge;
import chapter2.section2.OptimisedMerge;
import chapter2.section3.BentlyMcllroyQuickSort;
import com.algs4.stdlib.StdOut;
import com.algs4.stdlib.StdRandom;
import com.algs4.stdlib.Stopwatch;

public class SortCompare {
    public static double time(String alg, Double[] a){
        Stopwatch timer = new Stopwatch();
        switch (alg){
            case "Insertion":
                Insertion.sort(a);
                return timer.elapsedTime();
            case "Selection":
                Selection.sort(a);
                return timer.elapsedTime();
            case "Shell":
                Shell.sort(a);
                return timer.elapsedTime();
            case "InsertionWithSentinel":
                InsertionWithSentinel.sort(a);
                return timer.elapsedTime();
            case "Shellv2":
                Shellv2.sort(a);
                return timer.elapsedTime();
            case "Merge":
                Merge.sort(a);
                return timer.elapsedTime();
            case "OptimisedMerge":
                OptimisedMerge.sort(a);
                return timer.elapsedTime();
            case "BentlyMcllroyQuickSort":
                BentlyMcllroyQuickSort.sort(a);
                return timer.elapsedTime();
            case "NaturalMerge":
                NaturalMerge.sort(a);
                return timer.elapsedTime();
            default:
                return timer.elapsedTime();
        }
    }
    public static double timeRandomInput(String alg, int N, int T){
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            //进行一次测试（生成一个数组并排序）
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform();
            }
            total += time(alg,a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = "BentlyMcllroyQuickSort";
        String alg2 = "Shellv2";
        double t1 = timeRandomInput(alg1,100000,10);
        double t2 = timeRandomInput(alg2,100000,10);
        StdOut.printf("For %d random Doubles\n%s is ",100000,alg1);
        StdOut.printf("%.2f times faster than %s\n",t2/t1,alg2);
    }
}
