package chatpter1.section3;

import com.algs4.stdlib.StdIn;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by 韩宪斌 on 2017/7/27.
 * 实现一个随机队列
 */
public class RandomQueue<Item> implements Iterable<Item> {
    private Item[] items = (Item[]) new Object[1];
    private int N;
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public void enqueue(Item item) {
        items[N++] = item;
        if (N == items.length) resize(2 * items.length);//装满的时候调整数组大小
    }
    
    /**
     * 删除并随机返回一个元素(取样且不放回)
     *
     * @return
     */
    public Item dequeue() {
        int i = new Random().nextInt(N);
        Item item = items[i];
        items[i] = items[--N];
        //避免对象游离
        items[N] = null;
        if (N > 0 && N == items.length / 4) resize(items.length / 2);
        return item;
    }
    
    /**
     * 随机返回一个元素但不删除它(取样且放回)
     *
     * @return
     */
    public Item sample() {
        return items[new Random().nextInt(N)];
    }
    
    
    @Override
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }
    
    private class RandomQueueIterator implements Iterator<Item> {
        private int count = N;
        
        @Override
        public boolean hasNext() {
            return count > 0;
        }
        
        @Override
        public Item next() {
            int i = new Random().nextInt(count);
            //交换i和count-1的元素的位置，并且减少count数
            Item item = items[i];
            items[i] = items[--count];
            items[count] = item;
            
            return item;
        }
    }
    
    private void resize(int length) {
        Item[] newArr = (Item[]) new Object[length];
        for (int i = 0; i < N; i++) {
            newArr[i] = items[i];
        }
        items = newArr;
    }
    
    public static void main(String[] args) {
        System.out.print("Please input the string u want to input:");
        char[] chars = StdIn.readLine().toCharArray();
        RandomQueue<Character> rqueue = new RandomQueue<Character>();
        for (char c : chars) {
            rqueue.enqueue(c);
        }
        System.out.print("random samples: ");
        for (char c : rqueue) {
            System.out.print(c + " ");
        }
        System.out.println();
        System.out.print("random deque: ");
        while (!rqueue.isEmpty()) {
            System.out.print(rqueue.dequeue() + " ");
        }
    }
    
}
