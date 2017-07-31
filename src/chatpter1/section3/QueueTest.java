package chatpter1.section3;

import com.algs4.stdlib.StdIn;

/**
 * Created by 韩宪斌 on 2017/7/26.
 */
public class QueueTest {
    public static void main(String[] args) {
        while(true){
            QueueFromStacks<Character> cQueue =  new QueueFromStacks<Character>();
            System.out.print("Please input the String:");
            char[] chars = StdIn.readLine().toCharArray();
            for (char c:chars){
                cQueue.enqueue(c);
            }
            
            System.out.println("Dequeue the rQueue now...."+cQueue.dequeue()+" "+cQueue.dequeue());
            
        }}
}
