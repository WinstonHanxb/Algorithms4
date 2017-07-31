package chatpter1.section1;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by 韩宪斌 on 2017/7/20.
 */
    public class DiceRoller {
        public static double[] accurateProbability(int sides){
            double[] dist= new double[2*sides+1];
            for(int i=1;i<=sides;i++){
                for (int j = 1; j <= sides; j++) {
                    dist[i+j]+=1.0;
                }
            }
            for (int k = 2; k <= 2*sides; k++) {//因为投两个骰子不可能出现结果为1的情况
                dist[k]/=(sides*sides) ;
            }
            return dist;
        }
        
        public static int tossDice(int sides){
            return (new Random().nextInt(sides)) +1;
        }
        
        public static double[] tossNProbability(int N,int sides){
            int[] count=new int[2*sides+1];
            double[] result=new double[2*sides+1];
            for (int i = 1; i <=N ; i++) {
                count[tossDice(sides)+tossDice(sides)]++;
            }
            for (int j = 2; j <= 2*sides ; j++) {//因为投两个骰子不可能出现结果为1的情况
                result[j]=(double)count[j]/(double)N;
            }
            return result;
        }
        
        public static boolean checkEqual(double[] aResult,double[] bResult,int sides){
            DecimalFormat df = new DecimalFormat( "0.000 ");
            for (int i = 2; i <= 2*sides ; i++) {
                if(!df.format(aResult[i]).equals(df.format(bResult[i]))){
                    return false;
                }
            }
            return true;
        }
    
    public static void main(String[] args) {
        while(true){
            int N=0;
            int sides=6;
            while(!checkEqual(accurateProbability(sides),tossNProbability(N,sides),sides)){
                N=N+10000;
            }
            System.out.println("Right N:" +N);
        }
    }
        
        
}
