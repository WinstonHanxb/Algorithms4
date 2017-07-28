package chatpter1.sector3;

import com.algs4.stdlib.StdIn;

/**
 * Created by 韩宪斌 on 2017/7/27.
 */
public class JosephusProblem {
    /**
     * Josephus问题，N个人围坐一圈 位置记为0~(N-1)
     * 从第一个人开始报数，报到M的人会被杀死，直到最后一个人活下来。
     * 注意在此问题中报数为一个持续的行为，到M就死一人
     * @param N
     * @param M
     */
    public static void Josephus(int N, int M) {
        Queue<Integer> queue = new Queue<Integer>();
        for (int i = 0; i < N; i++) {
            queue.enqueue(i);
        }
        int count=1;
        while (queue.size() != 1) {
            if(count != M){
                queue.enqueue(queue.dequeue());
                count++;
            }else{
                System.out.print(queue.dequeue()+" ");
                count=1;//数值到达M，重新报数
            }
        }
        System.out.printf("\nFinal survivor position:%d \n", queue.dequeue());
    }
    
    public static void main(String[] args) {
        System.out.print("N & M:");
        int N = StdIn.readInt();
        int M = StdIn.readInt();
        JosephusProblem.Josephus(N, M);
    }
    
}
