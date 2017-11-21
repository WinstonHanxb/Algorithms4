package chapter1.section1;

import com.algs4.stdlib.StdRandom;

/**
 * Created by 韩宪斌 on 2017/7/20.
 */
public class BadShuffle {
    
    public static void shuffleMatrix(double[] input) {
        badshuffle(input);
    }
    
    public static void badshuffle(double[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = StdRandom.uniform(N );
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
    
    public static double[] init(int M) {
        double[] matrix = new double[M];
        for (int i = 0; i < M; i++) {
            matrix[i] = i;
        }
        return matrix;
    }
    
    public static void suffleTest(int M, int N) {
        int[][] count = new int[M][M];
        for (int i = 0; i < N; i++) {
            double[] testMatrix = init(M);
            shuffleMatrix(testMatrix);
            for (int j = 0; j < M; j++) {
                count[j][(int) testMatrix[j]]++;
            }
        }
        System.out.println("N/M: " + N / M);
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%10d", count[i][j]);
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        suffleTest(10, 10000000);
    }
}
