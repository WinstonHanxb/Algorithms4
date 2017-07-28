package chatpter1.sector3;

import com.algs4.stdlib.StdDraw;
import com.algs4.stdlib.StdIn;

/**
 * Created by 韩宪斌 on 2017/7/26.
 * 使用双向节点的双向链表
 */
public class DoubleNodeLink<Item> {
    private DoubleNode<Item> head;
    
    private class DoubleNode<Item> {
        private double x;
        private double y;
        public Item item;
        public DoubleNode<Item> previous;
        public DoubleNode<Item> next;
        
        
        /**
         * 以（x, y）为中心绘制节点
         */
        public void draw() {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(x, y, item + "");
            StdDraw.rectangle(x, y, 0.45, 0.45);
            StdDraw.line(x - 0.45, y - 0.15, x + 0.45, y - 0.15);
            StdDraw.line(x - 0.45, y + 0.15, x + 0.45, y + 0.15);
        }
        
        /**
         * 指定DoubleNode的中心节点
         *
         * @param x
         * @param y
         */
        public void setCenter(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        
    }
    
    
    public void insertHead(DoubleNode<Item> node) {
        if (null == head) {
            head = node;
            head.next = head;
            head.previous = head;
        }
        
        node.next = head;
        node.previous = head.previous;
        head.previous.next = node;
        head.previous = node;
        
        head = node;
    }
    
    public void insertTail(DoubleNode<Item> node) {
        if (null == head) {
            head = node;
            head.next = head;
            head.previous = head;
        }
        
        
        node.next = head;
        node.previous = head.previous;
        head.previous.next = node;
        head.previous = node;
        
    }
    
    //删除的情况下只有一个节点的情况要特别注意
    public void deleteHead() {
        if (head.next == head && head.previous == head) {
            //消除自我引用 避免垃圾回收？存疑
            head.previous = null;
            head.next = null;
            
            head = null;
        } else if (null != head) {
            head.next.previous = head.previous;
            head.previous.next = head.next;
            head = head.next;
        }
    }
    
    //删除的情况下只有一个节点的情况要特别注意
    public void deleteTail() {
        if (head.next == head && head.previous == head) {
            //消除自我引用 避免垃圾回收？存疑
            head.previous = null;
            head.next = null;
            
            head = null;
        } else if (null != head) {
            head.previous.previous.next = head;
            head.previous = head.previous.previous;
        }
    }
    
    
    public void insertBefore(DoubleNode<Item> insertBefore, DoubleNode<Item> node) {
        if (insertBefore == head) {
            this.insertHead(node);
        } else {
            node.previous = insertBefore.previous;
            node.next = insertBefore;
            
            //下面两句顺序不能反
            insertBefore.previous.next = node;
            insertBefore.previous = node;
        }
    }
    
    public void insertAfter(DoubleNode<Item> insertAfter, DoubleNode<Item> node) {
        if (insertAfter == head.previous) {
            this.insertTail(node);
        } else {
            node.previous = insertAfter;
            node.next = insertAfter.next;
            
            //下面两句顺序不能反
            insertAfter.next.previous = node;
            insertAfter.next = node;
        }
    }
    
    public void delete(DoubleNode<Item> node) {
        if (head.next == head && head.previous == head && node == head) {
            //消除自我引用 避免垃圾回收？存疑
            head.previous = null;
            head.next = null;
            
            head = null;
        } else {
            node.previous.next = node.next;
            node.next.previous = node.previous;
        }
    }
    
    /**
     * 接受一个item，返回找得到的第一个节点，否则返回null
     *
     * @param item
     * @return
     */
    public DoubleNode<Item> firstFind(Item item) {
        if (null != head) {
            if (head.item.equals(item)) {
                return head;
            }
            DoubleNode<Item> pointer = head.next;
            while (pointer != head) {
                if (pointer.item.equals(item)) {
                    return pointer;
                }
                pointer = pointer.next;
            }
        }
        return null;
    }
    
    public void drawLink(double intx, double inty) {
        StdDraw.setPenColor(StdDraw.BLACK);
        if (null != head) {
            DoubleNode<Item> pointer = head;
            int i = 1;
            while (pointer.next != head) {//初始化除尾节点外所有中心节点的坐标并画一部分线
                pointer.setCenter(intx + (i++) - 0.5, 0.5 + inty);
                pointer.draw();//画出自身
    
                pointer = pointer.next;
                
            
            }
            //初始化尾节点坐标
            pointer.setCenter(intx + (i++) - 0.5, 0.5 + inty);
            pointer.draw();
            
            StdDraw.setPenColor(StdDraw.GREEN);
            
            StdDraw.filledSquare(pointer.x, pointer.y - 0.3, 0.03);
            StdDraw.line(pointer.x, pointer.y - 0.3, pointer.x, pointer.y - 0.6);
            StdDraw.line(pointer.x, pointer.y - 0.6, pointer.next.x - 0.6, pointer.next.y - 0.6);
            StdDraw.line(pointer.next.x - 0.6, pointer.next.y - 0.6, pointer.next.x - 0.6, pointer.next.y);
            StdDraw.line(pointer.next.x - 0.6, pointer.next.y, pointer.next.x, pointer.next.y);
            StdDraw.filledSquare(pointer.next.x, pointer.next.y, 0.03);
            
            StdDraw.filledSquare(pointer.next.x, pointer.next.y + 0.3, 0.03);
            StdDraw.line(pointer.next.x, pointer.next.y + 0.3, pointer.next.x, pointer.next.y + 0.6);
            StdDraw.line(pointer.next.x, pointer.next.y + 0.6, pointer.x + 0.6, pointer.y + 0.6);
            StdDraw.line(pointer.x + 0.6, pointer.y + 0.6, pointer.x + 0.6, pointer.y);
            StdDraw.line(pointer.x + 0.6, pointer.y, pointer.x, pointer.y);
            StdDraw.filledSquare(pointer.x, pointer.y, 0.03);
    
    
            pointer = pointer.next.next;
            while (pointer != head) {
                StdDraw.filledSquare(pointer.x, pointer.y + 0.3, 0.03);
                StdDraw.filledSquare(pointer.previous.x, pointer.previous.y, 0.03);
                StdDraw.line(pointer.x, pointer.y + 0.3, pointer.previous.x, pointer.previous.y);
    
                StdDraw.line(pointer.previous.x, pointer.previous.y - 0.3, pointer.x, pointer.y);
                StdDraw.filledSquare(pointer.previous.x, pointer.previous.y - 0.3, 0.03);
                StdDraw.filledSquare(pointer.x, pointer.y, 0.03);
                pointer = pointer.next;
            }
            
            StdDraw.setPenColor(StdDraw.BLACK);
        }
    }
    
    public DoubleNode<Item> createNode(Item item) {
        DoubleNode<Item> node = new DoubleNode<Item>();
        node.item = item;
        return node;
    }
    
    public static void main(String[] args) {
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setScale(0, 8);
        
        DoubleNodeLink<Character> dnLink = new DoubleNodeLink<Character>();
        System.out.printf("Please input a string(less than 8 characters,each character should be different!),\n" +
                "notice that the first two characters will use insertHead,\n" +
                "the second two characters will use insertTail,\n" +
                "the third two characters will use insertBefore(before the 2th character u type in),\n" +
                "the rest characters will use insertAfter(after the 1st character u type in):");
        char[] chars = StdIn.readLine().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i < 2) {
                dnLink.insertHead(dnLink.createNode(chars[i]));
            } else if (i < 4) {
                dnLink.insertTail(dnLink.createNode(chars[i]));
            } else if (i < 6) {
                dnLink.insertBefore(dnLink.firstFind(chars[1]), dnLink.createNode(chars[i]));
            } else {
                dnLink.insertAfter(dnLink.firstFind(chars[0]), dnLink.createNode(chars[i]));
            }
        }
        dnLink.drawLink(0, 7);
        System.out.println("finished...");
        
        
        System.out.println("Trying to delete the head...");
        dnLink.deleteHead();
        dnLink.drawLink(0, 5.5);
        System.out.println("finished...");
        
        System.out.println("Trying to delete the tail...");
        dnLink.deleteTail();
        dnLink.drawLink(0, 4);
        System.out.println("finished...");
        
        
        System.out.print("Please input the node u want to delete:");
        char c = StdIn.readLine().toCharArray()[0];
        dnLink.delete(dnLink.firstFind(c));
        dnLink.drawLink(0, 2.5);
        System.out.println("finished...");
        
        
    }
    
}
