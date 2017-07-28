package chatpter1.sector2;

import com.algs4.stdlib.StdIn;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Created by 韩宪斌 on 2017/7/21.
 */
public class VisualCounter {
    private int N;
    private int max;
    private int count;
    private int times;
    public VisualCounter(int N,int max) {
        this.max=max;
        this.N=N;
        this.count=1;
        this.times=0;
        StdDraw.setScale(0,max);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
    }
    
    public void increment(){
        this.times++;
        if(this.count<this.max){
            this.count++;
        }
        this.draw();
    }
    
    public void decrement(){
        this.times++;
        if(this.count>0){
            this.count--;
        }
        this.draw();
    }
    
    public int tally(){
        return this.count;
    }
    
    private void draw(){
        double x=this.max*(double)times/N;
        double y=count/2.0;
        double hw=0.5*this.max/N;
        double hh=count/2.0;
        StdDraw.filledRectangle(x,y,hw,hh);
    }
    
    
    public static void main(String[] args) {
        while(true){
            System.out.println("Please input N and max");
            int N=StdIn.readInt();
            VisualCounter vc=new VisualCounter(N,StdIn.readInt());
            int i=0;
            while(i<N){
                i++;
                if(Math.random()>0.5){
                    vc.increment();
                }else{
                    vc.decrement();
                }
            }
        }
       
        
        
    }
}
