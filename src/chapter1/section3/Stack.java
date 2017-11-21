package chapter1.section3;

import com.algs4.stdlib.StdIn;

import java.util.Iterator;

/**
 * Created by 韩宪斌 on 2017/7/24.
 * 可迭代栈，链表实现
 */
public class Stack<Item> implements Iterable<Item> {
    private Node first;
    private int N;
    
    private class ReverseIterator implements Iterator<Item> {
        private Node pointer = first;
        
        @Override
        public boolean hasNext() {
            return pointer != null;
        }
        
        @Override
        public Item next() {
            Item item = pointer.item;
            pointer = pointer.next;
            return item;
        }
        
    }
    
    public Stack(Node first) {
        this.first = first;
    }
    
    public Stack() {
    }
    
    private class Node {
        Item item;
        Node next;
    }
    
    
    @Override
    public Iterator<Item> iterator() {
        return new ReverseIterator();
    }
    
    public Item peek() {
        return null == first ? null : first.item;
    }
    
    public  Stack<Item> shallowCopy(){
        return new Stack<Item>(this.first);
    }
    
    
    /**
     * 静态copy方法，接受一个字符串栈，返回一个字符串栈的副本
     * <p>
     * Java中的字符串引用非常复杂，有点像值类型和引用类型的混合，要根据具体情况具体分析
     * e.g:
     * {
     * String a = "123";
     * String b = "123";
     * return a == b;//结果是true
     * }
     * 这种情况下String对象分配在堆中，但在常量池中保存了指向String对象的指针，而a为String型指针，
     * 指针的内容和常量之中“123”对应的指针相同。具体来说，执行过程如下：
     * * 首先，常量池中查找“123”的指针
     * * 如果在常量池中未能找到“123”的指针，则在堆中分配“123”的内存空间，把地址保存到常量池中，
     * 并把这个地址赋值给String型指针a
     * * 如果在常量池中找到“123”的指针，说明堆中已经存在“123”的实体，因为常量表示一个不可变的对象，
     * 所以，没有必要再创建新的实例，直接把常量池中的指针内容赋值给String型指针a
     * 而后者，实际上涉及到两个String实体，“123”在堆中存在一个实体，并且在常量池中存在一个指向“123”的指针，
     * 而new String()会在堆中创建一个新的String实体，并深度拷贝“123”的内容，并返回新的String实体的地址，赋值给指针a
     *
     * @param input
     * @return
     */
    public static Stack<String> copy(Stack<String> input) {
        Stack<String> copyReverse = new Stack<String>();
        Stack<String> copy = new Stack<String>();
        for (String s : input) {
            /**Java中String类型为引用类型
             * 单纯的压栈只是将字符串s的引用压栈
             * 并不是真正的复制行为
             * 因此必须要使用new String()在内存中生成新的字符引用
             * */
            copyReverse.push(new String(s));
        }
        for (String s : copyReverse) {
            copy.push(s);
        }
        return copy;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return N;
    }
    
    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }
    
    public Item pop() {
        if(null == first) return null;
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    
    public static void main(String[] args) {
        while (true) {
            System.out.print("Please input the number N:");
            Stack<Integer> stack = new Stack<Integer>();
            int N = StdIn.readInt();
            while (N > 0) {
                stack.push(N % 2);
                N = N / 2;
            }
            for (int d : stack) System.out.print(d);
            System.out.println();
        }
        
    }
}
