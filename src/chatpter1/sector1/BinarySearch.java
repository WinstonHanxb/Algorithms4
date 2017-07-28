package chatpter1.sector1;


import com.algs4.stdlib.StdRandom;
import edu.princeton.cs.algs4.Counter;

import java.util.Arrays;

/**
 * 第一节1.1.39习题
 */
public class BinarySearch {
    
    public static int[] randomGenerator(int N){
        int[] matrix=new int[N];
        for (int i = 0; i < N; i++) {
            matrix[i]= StdRandom.uniform(1000000);//6位随机正整数
        }
        return matrix;
    }
    
    public static boolean binarySearch(int key,int[] a,int lo,int hi,Counter counter){
        counter.increment();
        if(lo>hi){
            return false;
        }
        int mid=lo+(hi-lo)/2;
        if(key<a[mid]) {return binarySearch(key,a,lo,mid-1,counter);}
        if(key>a[mid]) {return binarySearch(key,a,mid+1,hi,counter);}
        else return true;
    }
    
    public static int matching(int[] a,int[] b){
        Arrays.sort(a);
        Arrays.sort(b);
        int count=0;
        for (int i = 0; i < a.length; i++) {
            while((i<a.length-1)&&(a[i+1]==a[i])){//重复数字只匹配一次
                i++;
            }
            Counter c1=new Counter("");
            if(binarySearch(a[i],b,0,b.length-1,c1)){
                count++;
            }
            System.out.printf("The key %6s matches %d times.\n",a[i],c1.tally());
        }
        System.out.println();
        return count;
    }

    public static void randomMatching(int T){
//        int[] N={1000,10000,100000,1000000};
        int[] N={1000000};
        for(int n:N){
            int result=0;
            for (int i = 1; i <= T; i++) {
                result+=matching(randomGenerator(n),randomGenerator(n));
            }
            System.out.printf("N=%7d,T=%3d result=%d \n",n,T,result/T);
        }
    }
    
    public static void main(String[] args) {
            randomMatching(1);
    }
}
