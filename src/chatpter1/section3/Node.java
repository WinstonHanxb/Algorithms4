package chatpter1.section3;

import com.algs4.stdlib.StdDraw;

/**
 * Created by 韩宪斌 on 2017/7/25.
 * 链表的节点类型，目的是要能实现画图功能
 */
public class Node<Item>{
    private double x;
    private double y;
    public Item item;
    public Node<Item> next;
    
    public Node() {
    }
    
    public Node(Item item) {
        this.item = item;
    }
    
    /**
     * 以（x, y）为中心绘制节点
     */
    public void draw() {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(x,y+0.2,item+"");
        StdDraw.square(x,y,0.4);
        StdDraw.line(x-0.4,y,x+0.4,y);
    }
    
    /**
     * 给定两个节点，画出一条指向另一条的线
     * @param from
     * @param to
     */
    public static void fromTo(Node from,Node to){
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.line(from.x,from.y-0.2,to.x-0.4,to.y+0.2);
        
        StdDraw.filledSquare(to.x-0.4,to.y+0.2,0.03);
    }
    
    
    public void setCenter(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        
        Node<?> node = (Node<?>) o;
        
        if (!item.equals(node.item)) return false;
        return next != null ? next.equals(node.next) : node.next == null;
    }
    
    @Override
    public int hashCode() {
        return 0;
    }
    
    public static void main(String[] args) {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setScale(0, 10);
        Node<String> test=new Node<String>();
        test.item="test";
        test.setCenter(0.5,0.5);
        test.draw();
        Node<String> test2=new Node<String>();
        test2.item="test2";
        test2.setCenter(1.5,0.5);
        test2.draw();
        Node.fromTo(test,test2);
    }
    
    
}
