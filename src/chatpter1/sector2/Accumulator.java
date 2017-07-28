package chatpter1.sector2;

/**
 * Created by 韩宪斌 on 2017/7/22.
 */
public class Accumulator {
    private double m;
    private double s;
    private int N;
    
    public void addDataValue(double x) {
        N++;
        s = s + 1.0 * (N - 1) / N * (x - m) * (x - m);
        m = m + (x - m) / N;
    }
    public double mean(){
        return m;
    }
    public double var(){
        return s / (N - 1);
    }
    public double stddev(){
        return Math.sqrt(this.var());
    }
    
    
    public static void main(String[] args) {
        Accumulator acc=new Accumulator();
        for (int i = 0; i < 10; i++) {
            acc.addDataValue(Math.random()*100);
            System.out.println("方差: "+acc.var());
            System.out.println("标准差: "+acc.stddev());
        }
    }
}
