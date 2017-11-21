package chapter1.section2;

import com.algs4.stdlib.StdDraw;
import com.algs4.stdlib.StdIn;

import java.awt.geom.Point2D;

/**
 * Created by 韩宪斌 on 2017/7/21.
 */
public class Point2DTester {
    private Point2D[] points;
    
    public static void main(String[] args) {
        while(true){
            System.out.print("Please input the number N:");
            int N= StdIn.readInt();
            Point2DTester pt=new Point2DTester(N);
            pt.draw();
            System.out.printf("The shortest distance is : %1.3f \n",pt.getShortestDistacne());
            pt.getLongestDistance();
        }
    }
    
    public Point2DTester(int N) {
        points=new Point2D.Double[N];
        for (int i = 0; i < N; i++) {
            points[i]=new Point2D.Double(100*Math.random(),100*Math.random());
        }
    }
    
    /**
     * 画出所有随机生成的点
     */
    public void draw(){
        StdDraw.setScale(0,100);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setPenRadius(0.005D);
        for(Point2D p:points){
            StdDraw.point(p.getX(),p.getY());
        }
    }
    
    public double getShortestDistacne(){
        Point2D a=points[0];
        Point2D b=points[1];
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                if(points[i].distance(points[j])<a.distance(b)){
                    a=points[i];
                    b=points[j];
                }
            }
        }
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.line(a.getX(),a.getY(),b.getX(),b.getY());
        return a.distance(b);
    }
    
    public double getLongestDistance(){
        Point2D a=points[0];
        Point2D b=points[1];
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                if(points[i].distance(points[j])>a.distance(b)){
                    a=points[i];
                    b=points[j];
                }
            }
        }
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.line(a.getX(),a.getY(),b.getX(),b.getY());
        return a.distance(b);
    }
    
}
