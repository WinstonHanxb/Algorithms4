package chatpter1.section3;

/**
 * Created by 韩宪斌 on 2017/7/27.
 * 以栈为目标的队列，链表实现
 */
public class Steque<Item> {
    /**
     * 使用一个专有的next信息指向链表头部的指针比直接记录一个链表头操作起来更方便
     */
    private Node head=new Node();//一个指向头部的指针，本身内容为空
    
    private class Node{
        public Item item;
        public Node next;
    }
    
    /**
     * 入队操作，进入队尾
     * 此函数操作时间和链表的长度有关，可以进一步优化
     * @param item
     */
    public void enqueue(Item item){
        Node newTail = new Node();
        newTail.item = item;
        Node pointer = head;
        while(pointer.next != null){
            pointer = pointer.next;
        }
        pointer.next = newTail;
    }
    
    /**
     * 入栈操作，加入队头
     * @param item
     */
    public void push(Item item){
        Node newHead = new Node();
        newHead.item = item;
        newHead.next = head.next;
        head.next = newHead;
    }
    
    /**
     * 弹出队头
     * @return
     */
    public Item pop(){
        Item item = head.next.item;
        head.next = head.next.next;
        return item;
    }
    
    
   
}
