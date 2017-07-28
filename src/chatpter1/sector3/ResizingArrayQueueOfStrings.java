package chatpter1.sector3;

import com.algs4.stdlib.StdIn;

import java.util.Iterator;

/**
 * Created by 韩宪斌 on 2017/7/25.
 * 队列，数组实现
 * 数组能自动调整大小
 */
public class ResizingArrayQueueOfStrings<Item> implements Iterable<Item> {
    private Item[] array = (Item[]) new Object[1];
    private int N;
    
    public void enqueue(Item item) {
        if (N == array.length) resize(2 * array.length);
        array[N++] = item;
    }
    
    public Item dequeue() {
        Item item = array[0];
        for (int i = 0; i < N - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--N] = null;
        if (N > 0 && N == array.length / 4) resize(array.length / 2);
        return item;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    private void resize(int length) {
        Item[] newArr = (Item[]) new Object[length];
        for (int i = 0; i < N; i++) {
            newArr[i] = array[i];
        }
        array = newArr;
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new ArrayQueueIterator();
    }
    
    private class ArrayQueueIterator implements Iterator<Item> {
        private int i = 0;
        
        @Override
        public boolean hasNext() {
            return i < N;
        }
        
        @Override
        public Item next() {
            return array[i++];
        }
    }
    
}
