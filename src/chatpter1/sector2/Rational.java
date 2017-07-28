package chatpter1.sector2;

/**
 * Created by 韩宪斌 on 2017/7/22.
 * 有理数类型
 */
public class Rational {
    private int numerator;//分子
    private int denominator;//分母
    
    public Rational(int numerator, int denominator) {
        assert denominator!=0;
        int r = euclideanAlgorithm(numerator,denominator);
        this.numerator = numerator/r;
        this.denominator = denominator/r;
    }
    
    public Rational plus(Rational b){
        return new Rational(this.numerator*b.denominator+b.numerator*this.denominator,this.denominator*b
                .denominator);
    }
    
    public Rational minus(Rational b){
        return new Rational(this.numerator*b.denominator-b.numerator*this.denominator,this.denominator*b.denominator);
    }
    public Rational times(Rational b){
        return new Rational(this.numerator*b.numerator,this.denominator*b.denominator);
    }
    public Rational divides(Rational b){
        return new Rational(this.numerator*b.denominator,this.denominator*b.numerator);
    }
    
    public boolean equals(Rational that) {
        if(this.numerator==that.numerator&&this.denominator==that.denominator){
            return true;
        }
        return false;
    }
    
    private int euclideanAlgorithm(int m,int n)
    {
        if (m % n == 0) {
            return n;
        }
        else {
            return euclideanAlgorithm(n,m % n);
        }
    }
    
    @Override
    public String toString() {
        return this.numerator+"/"+this.denominator;
    }
    
    public static void main(String[] args) {
        Rational a=new Rational(1,2);
        Rational b=new Rational(1,3);
        System.out.println(a.plus(b));
        System.out.println(a.minus(b));
        System.out.println(a.times(b));
        System.out.println(a.divides(b));
    }
}
