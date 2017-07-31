package chatpter1.section3;

import com.algs4.stdlib.StdDraw;
import com.algs4.stdlib.StdIn;

/**
 * Created by 韩宪斌 on 2017/7/25.
 * 针对链表的专门练习I
 */
public class LinkPractices<Item> {
    private Node<Item> headNode;//一个next指向首个Node的头指针
    private int N;
    
    public void setHeadNode(Node<Item> headNode) {
        this.headNode = headNode;
    }
    
    
    /**
     * 删除尾部节点
     */
    public void delete() {
        if (!isEmpty() && N >= 2) {
            Node<Item> pointer = headNode;
            while (pointer.next.next != null) {//寻找倒数第二个链表点
                pointer = pointer.next;
            }
            pointer.next = null;//将最后一个链表节点脱钩
        } else {
            headNode = null;
        }
        N--;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int length() {
        return N;
    }
    
    /**
     * 接受一个链表节点作为参数并删除该节点的后续节点
     * 注意参数节点或参数节点的后续节点为空则什么也不做
     *
     * @param itemNode
     */
    public void removeAfter(Node<Item> itemNode) {
        Node<Item> pointer = headNode;
        //参数节点不可为空，参数节点的后续节点也不可为空
        final Boolean ITEMNODE_NOT_NULL = itemNode.next != null && itemNode.item != null;
        if (!isEmpty() && ITEMNODE_NOT_NULL) {
            while (pointer.next != null && !pointer.equals(itemNode)) {//要么找到对应节点要么pointer指向尾节点
                pointer = pointer.next;
            }
            pointer.next = null;//将找到节点的后续节点断链
        }
        pointer = headNode;
        N = 0;
        while (null != pointer) {
            N++;
            pointer = pointer.next;
        }
    }
    
    /**
     * 前移编码，从输入中读取数据，
     * 当遇到从未见过的item时，将它插入表头，
     * 当读取了重复的item时，将它从链表中删除并重新插入链表头
     * <p>
     * 此函数实现了前移编码策略，这种策略假设最近访问过的元素很可能会被再次访问，因此可以用于缓存、数据压缩等场景
     *
     * @param item
     */
    public void moveToFront(Item item) {
        
        remove(item);
        Node<Item> findNode = new Node<Item>(item);
        
        
        findNode.next = headNode;
        headNode = findNode;
        N++;
    }
    
    
    /**
     * 接受一个item，删除链表中所有item域为key的节点
     *
     * @param key
     */
    public void remove(Item key) {
        if (!isEmpty() && null != key) {
            while (!isEmpty() && headNode.item.equals(key)  ) {
                headNode = headNode.next;
                N--;
            }
            Node<Item> pointer = headNode;
            while (!isEmpty() && pointer.next != null) {
                if (pointer.next.item.equals(key)) {
                    pointer.next = pointer.next.next;
                    N--;
                    continue;
                }
                pointer = pointer.next;
            }
            
        }
    }
    
    /**
     * 接受两个链表节点作为参数，将第二个节点插入链表并使之成为第一个节点的后续节点（如果两个参数为空则什么也不做）
     *
     * @param firstNode
     * @param secondNode
     */
    public void insertAfter(Node<Item> firstNode, Node<Item> secondNode) {
        final Boolean FIRSTNODE_NOT_NULL = firstNode != null && firstNode.item != null;
        final Boolean SECONDNODE_NOT_NULL = secondNode != null && secondNode.item != null;
        if (!isEmpty() && FIRSTNODE_NOT_NULL && SECONDNODE_NOT_NULL) {
            secondNode.next = firstNode.next;
            firstNode.next = secondNode;
            N++;
        }
    }
    
    /**
     * 删除第k个节点
     *
     * @param k
     */
    public void delete(int k) {
        if (k == 1) {
            headNode = headNode.next;
        }
        if (k <= length() && k > 1) {
            Node<Item> pointer = headNode;
            for (int i = 1; i <= k - 2; i++) {
                pointer = pointer.next;
            }
            pointer.next = pointer.next.next;
        }
        N--;
    }
    
    /**
     * 尾插入
     *
     * @param item
     */
    public void add(Item item) {
        if (isEmpty()) {//空链表初始化
            headNode = new Node<Item>();
            headNode.item = item;
        } else {
            Node<Item> pointer = headNode;
            while (pointer.next != null) {//寻找尾节点
                pointer = pointer.next;
            }
            pointer.next = new Node<Item>();
            pointer.next.item = item;
        }
        N++;
    }
    
    
    /**
     * 接受一个item，如果找得到就返回TRUE，否则返回FALSE
     *
     * @param item
     */
    public boolean find(Item item) {
        if (!isEmpty()) {
            Node<Item> pointer = headNode;
            while (pointer != null) {
                if (pointer.item.equals(item)) {
                    return true;
                }
                pointer = pointer.next;
            }
        }
        return false;
    }
    
    /**
     * 接受一个item，返回找得到的第一个节点，否则返回null
     *
     * @param item
     * @return
     */
    public Node<Item> firstFind(Item item) {
        if (!isEmpty()) {
            Node<Item> pointer = headNode;
            while (pointer != null) {
                if (pointer.item.equals(item)) {
                    return pointer;
                }
                pointer = pointer.next;
            }
        }
        return null;
    }
    
    /**
     * 翻转链表
     * 接受一个首结点，（破坏性地）将链表反转并返回结果链表的首结点
     *
     * @param first
     */
    public Node<Item> reverseLink(Node<Item> first) {
        if (first.next == null || null == first) { //递归跳出条件
            return first;
        }
        
        Node<Item> newFirst = reverseLink(first.next);
        Node<Item> newLast = first.next;
        newLast.next = first;//将first插入逆序链表的结尾
        first.next = null;//first断链
        return newFirst;
        
    }
    
    
    /**
     * 给定一个起始坐标x和纵坐标y来画出整个链表
     *
     * @param intx
     * @param inty
     */
    public void drawLink(double intx, double inty) {
        StdDraw.setPenColor(StdDraw.WHITE);
        if (!isEmpty()) {
            Node<Item> pointer = headNode;
            for (int i = 1; i <= length(); i++) {//初始化全部节点中心坐标
                pointer.setCenter(intx + i - 0.5, 0.5 + inty);
                pointer.draw();
                pointer = pointer.next;
            }
            pointer = headNode;
            while (pointer.next != null) {//画图,注意这种画图方式不能用在环状链表上，用在环状链表上会陷入死循环
                Node.fromTo(pointer, pointer.next);
                pointer = pointer.next;
            }
        }
    }
    
    public static void main(String[] args) {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setScale(0, 6);
        
        
        LinkPractices<Character> lp1 = new LinkPractices<Character>();
        System.out.print("Please input a string(less than 5 characters): ");
        char[] chars1 = StdIn.readLine().toCharArray();
        for (char c : chars1) {
            lp1.moveToFront(c);
        }
        lp1.drawLink(0, 5);
        System.out.println("Finished...");
//
//        lp1.setHeadNode(lp1.reverseLink(lp1.firstFind(chars1[0])));
//        lp1.drawLink(0, 4);
//        System.out.println("The reverse result has been drawn.");

//        System.out.print("Please input the char you want to remove:");
//        char c1 = StdIn.readLine().toCharArray()[0];
//        lp1.remove(c1);
//        lp1.drawLink(0, 4);
//        System.out.println("The result has been drawn.");


//        LinkPractices<Character> lp2 = new LinkPractices<Character>();
//        System.out.print("Please input the second string(less than 5 characters): ");
//        char[] chars2 = StdIn.readLine().toCharArray();
//        for (char c : chars2) {
//            lp2.add(c);
//        }
//        lp2.drawLink(0, 4);
//        System.out.println("The result has been drawn.");


//        System.out.println();
//        System.out.print("Please input the char(from the second link) you want to insert:");
//        char c1 = StdIn.readLine().toCharArray()[0];
//        System.out.print("Please input the char(from the first link) you want to insert after:");
//        char c2 = StdIn.readLine().toCharArray()[0];
//        lp1.insertAfter(lp1.firstFind(c2), lp2.firstFind(c1));
//        lp1.drawLink(0, 3);
//        System.out.println("The result has been drawn.");

//        System.out.println();
//        System.out.print("Please input the number k: ");
//        int k = Integer.parseInt(StdIn.readLine());
//        lp.delete(k);
//        System.out.println("The result has been drawn.");
//        lp.drawLink(0, chars.length - 2);
//
//        System.out.println();
//        System.out.print("Please input the char you want to find: ");
//        char c = StdIn.readChar();
//        System.out.println("Result:" + lp.find(c));

//        System.out.println();
//        System.out.print("Please input the char you want to removeafter: ");
//        char c = StdIn.readChar();
//        lp.removeAfter(lp.firstFind(c));
//        lp.drawLink(0, chars.length - 2);
//        System.out.println("The result has been drawn.");
    }
}
