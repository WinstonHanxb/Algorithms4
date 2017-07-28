package chatpter1.sector3;

import java.util.Iterator;

/**
 * Created by 韩宪斌 on 2017/7/24.
 * 可迭代队列，链表实现
 */
public class Queue<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
    }
    
    private int N;
    private Node first;
    private Node last;
    
    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        N++;
    }
    
    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;
    }
    
    
    public Item peek(){
        return first.item;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return N;
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }
    
    private class QueueIterator implements Iterator<Item> {
        private Node firstPointer = first;
        
        @Override
        public boolean hasNext() {
            return firstPointer != null;
        }
        
        @Override
        public Item next() {
            Item item = firstPointer.item;
            firstPointer = firstPointer.next;
            return item;
        }
    }
    
    /**
     * 特殊构造函数，根据指定的一个队列q，创建一个q的副本r
     * q和它的r副本操作互相独立互不影响；
     *
     * @param q
     */
    public Queue(Queue<Item> q) {
        for (int i = 0; i < q.size(); i++) {
            Item item = q.dequeue();
            q.enqueue(item);
            this.enqueue(item);
        }
    }
    
    public Queue() {
    }
}
