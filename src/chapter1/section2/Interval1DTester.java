package chapter1.section2;

import com.algs4.stdlib.StdIn;
import edu.princeton.cs.algs4.Interval1D;

/**
 * Created by 韩宪斌 on 2017/7/21.
 */
public class Interval1DTester {
    private Interval1D[] interval1DS;
    
    public Interval1DTester(int N) {
        interval1DS=new Interval1D[N];
        System.out.printf("Please input %d pairs of interval numbers: \n",N);
        for (int i = 0; i < N; i++) {
            interval1DS[i]=new Interval1D(StdIn.readDouble(),StdIn.readDouble());
        }
    }
    
    public void checkIntersects(){
        for (int i = 0; i < interval1DS.length; i++) {
            for (int j = i+1; j < interval1DS.length; j++) {
                if(interval1DS[i].intersects(interval1DS[j])){
                    System.out.println("These two intervals are intersect");
                    System.out.println("1."+interval1DS[i].toString());
                    System.out.println("2."+interval1DS[j].toString());
                }
            }
        }
    }
    
    
    public static void main(String[] args) {
        while(true){
            System.out.print("Please input the number N: ");
            Interval1DTester it=new Interval1DTester(StdIn.readInt());
            it.checkIntersects();
        }
      
    }
}
