package chapter1.section3;

import java.util.Iterator;

/**
 * Created by 韩宪斌 on 2017/7/25.
 * 环形链表实现队列
 * 环形链表的特点是没有任何结点的连接为空，且只要链表非空则last.next的值为first
 */
public class CircleLinkQueue<Item> implements Iterable<Item> {
    /**
     * 环形链表只有一个指针，始终指向链表的尾部
     * last.next == first
     */
    Node<Item> last;
    int N;
    
    
    public void enqueue(Item item) {
        Node<Item> node = new Node<Item>();
        node.item = item;
        if (isEmpty()) {
            last = node;
            last.next = last;
        } else {
            node.next = last.next;//last.next指向first赋值给新的node.next
            last.next = node;//插入新的node
            last = node;//修改last指针
        }
        N++;
    }
    
    public Item dequeue() {
        if (isEmpty()) {
            return null;
        } else {
            Node<Item> node=last.next;
            last.next=last.next.next;
            N--;
            return node.item;
        }
    }
    
    boolean isEmpty() {
        return N == 0;
    }
    
    int size() {
        return N;
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new CircleLinkIterator();
    }
    
    private class CircleLinkIterator implements Iterator<Item> {
        private Node<Item> lastPointer = last;
        private int count = N;
        
        @Override
        public boolean hasNext() {
            return count != 0;
        }
        
        @Override
        public Item next() {
            Item item = lastPointer.next.item;
            lastPointer = lastPointer.next;
            count--;
            return item;
        }
    }
    
}
