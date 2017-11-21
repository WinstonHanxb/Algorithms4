package chapter1.section3;

import com.algs4.stdlib.StdIn;

/**
 * Created by 韩宪斌 on 2017/7/27.
 * 环形队列，利用回环数组实现
 */
public class RingBuffer<Item> {
    private int start;
    private int end;
    private int capacity;
    private Item[] buffers;
    
    public RingBuffer(int N) {
        this.capacity = N;
        buffers = (Item[]) new Object[N];
    }
    
    public boolean isFull() {
        return end - start == capacity;
    }
    
    
    public Item dequeue() {
        if(start != end){
            return buffers[start++ % capacity ];
        }
        return null;
    }
    
    public void enqueue(Item item) {
        if (!isFull()) {
            buffers[end++ % capacity] = item;
        }
    }
    
    
    public static void main(String[] args) {
        System.out.println("The buffer size is 10;");
        RingBuffer<Character> rBuffer = new RingBuffer<Character>(10);
        while(true){
            while(!rBuffer.isFull()){
                System.out.print("Type in:");
                char c = StdIn.readLine().charAt(0);
                rBuffer.enqueue(c);
            }
            while(rBuffer.isFull()){
                System.out.println("Dequeue now...:"+rBuffer.dequeue());
            }
        }
    }
    
}
