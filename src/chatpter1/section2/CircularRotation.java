package chatpter1.section2;

import com.algs4.stdlib.StdIn;

/**
 * Created by 韩宪斌 on 2017/7/21.
 */
public class CircularRotation {
    public static boolean checkCircularRotation(String s1,String s2){
        s2=s2+s2;
        return (s2.indexOf(s1)>=0)&&(s2.indexOf(s1)<s1.length());
    }
    
    
    public static void main(String[] args) {
        while(true){
            System.out.println("Please input the two strings:");
            if(CircularRotation.checkCircularRotation(StdIn.readString(),StdIn.readString())){
                System.out.println("The input strings are circular rotation.");
            }else{
                System.out.println("The input strings are not circular rotation.");
            }
        }
    }
}
