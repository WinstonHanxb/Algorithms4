package chapter1.section1;

import com.algs4.stdlib.StdDraw;
import com.algs4.stdlib.StdIn;

/**
 * Created by 韩宪斌 on 2017/7/19.
 */
public class RandomLink {
    private static class Dot {
        public double X;
        public double Y;
        private double radius = 0.5;
        
        public Dot(double x, double y) {
            X = x;
            Y = y;
        }
    }
    
    public static void drawDots(int N, double p) {
        StdDraw.setScale(-10, 10);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        Dot[] dots = new Dot[N];
        for (int i = 0; i < N; i++) {
            double degree = 2 * Math.PI / N * i;
            dots[i] = new Dot(Math.cos(degree) * 8, Math.sin(degree) * 8);
            StdDraw.filledCircle(dots[i].X, dots[i].Y, 0.05);
        }
        randomLink(dots, p);
    }
    
    public static void randomLink(Dot[] dots, double p) {
        for (int i = 0; i < dots.length; i++) {
            for (int j = i + 1; j < dots.length; j++) {
                if(Math.random()<=p){
                    StdDraw.line(dots[i].X,dots[i].Y,dots[j].X,dots[j].Y);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        while (true) {
            System.out.print("Please input the number N: ");
            int N = StdIn.readInt();
            System.out.print("Please input the probability p: ");
            double p = StdIn.readDouble();
            drawDots(N, p);
        }
        
    }
}


