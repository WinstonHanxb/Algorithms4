package chatpter1.section2;

import com.algs4.stdlib.StdIn;
import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;



/**
 * Created by 韩宪斌 on 2017/7/21.
 */
public class Interval2DTester {
    private Interval2D[] intervals;
    private double min;
    private double max;
    public Interval2DTester(int N,double min,double max){
        intervals=new Interval2D[N];
        this.min=min;
        this.max=max;
        StdDraw.setScale(min,max);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        for (int i = 0; i < N ; i++) {
            intervals[i]=new Interval2D(this.random1D(),this.random1D());
            intervals[i].draw();
        }
    }
    
    private Interval1D random1D(){
        double num1=StdRandom.uniform(min, max);
        double num2=StdRandom.uniform(min, max);
        return num1<num2?new Interval1D(num1,num2):new Interval1D(num2,num1);
    }
    
    public boolean checkIntersect(Interval2D a,Interval2D b){
        if(a.intersects(b)){
            return true;
        }
        return false;
    }
    
    public int getIntersectNumber(){
        int count=0;
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i+1; j < intervals.length; j++) {
                if(checkIntersect(intervals[i],intervals[j])){
                    count++;
                }
            }
        }
        return count;
    }
    
    
    
    public static void main(String[] args) {
        while(true){
            System.out.print("Please input the N,min,max:");
            Interval2DTester i2D=new Interval2DTester(StdIn.readInt(),StdIn.readDouble(),StdIn.readDouble());
            System.out.printf("The intersect counts are:%d \n", i2D.getIntersectNumber());
        }
    }
    
}
