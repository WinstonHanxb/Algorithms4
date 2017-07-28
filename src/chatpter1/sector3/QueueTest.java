package chatpter1.sector3;

import com.algs4.stdlib.StdIn;

/**
 * Created by 韩宪斌 on 2017/7/26.
 */
public class QueueTest {
    public static void main(String[] args) {
        while(true){
            Queue<Character> cQueue =  new Queue<Character>();
            System.out.print("Please input the String:");
            char[] chars = StdIn.readLine().toCharArray();
            for (char c:chars){
                cQueue.enqueue(c);
            }
            Queue<Character> rQueue = new Queue<Character>(cQueue);
            System.out.println("Dequeue the rQueue now...."+rQueue.dequeue()+" "+rQueue.dequeue());
            System.out.println("Print the cQueue...");
            for(Character c:cQueue){
                System.out.print(c);
            }
            System.out.printf("\nFinished...\n");
        }}
}
