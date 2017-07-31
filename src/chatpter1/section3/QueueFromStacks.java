package chatpter1.section3;

/**
 * Created by 韩宪斌 on 2017/7/31.
 * 使用六个栈来实现一个队列，其中队列的所有操作在最坏情况下也为O(1)
 * 算法的原理参见
 * https://ecommons.cornell.edu/bitstream/handle/1813/6273/80-433.pdf?sequence=1&isAllowed=y
 */
public class QueueFromStacks<Item> {
    private boolean isCopying;
    private int needCopy;
    
    private Stack<Item> h;
    private Stack<Item> TT;
    private Stack<Item> H;
    private Stack<Item> T;
    private Stack<Item> Hr;
    private Stack<Item> HH;
    
    public QueueFromStacks() {
        h = new Stack<Item>();
        TT = new Stack<Item>();
        H = new Stack<Item>();
        T = new Stack<Item>();
        Hr = new Stack<Item>();
        HH = new Stack<Item>();
    }
    
    public void enqueue(Item item) {
        if (isCopying) {
            TT.push(item);
            oneStep();
            oneStep();
            return;
        } else {
            if (H.size() > T.size()) {
                T.push(item);
                return;
            } else {
                T.push(item);
                isCopying = true;
                h = H.shallowCopy();
                oneStep();
                oneStep();
                return;
            }
        }
    }
    
    public Item dequeue() {
        Item item;
        if (isCopying) {
            item = h.pop();
            needCopy--;
            oneStep();
            oneStep();
        } else {
            if (H.size() > T.size()) {
                item = H.pop();
            } else {
                item = H.pop();
                isCopying = true;
                h = H.shallowCopy();
                oneStep();
                oneStep();
            }
        }
        return item;
    }
    
    private void oneStep() {
        if (!isCopying) return;
        if (!H.isEmpty() && !T.isEmpty()) {
            Hr.push(H.pop());
            HH.push(T.pop());
            needCopy++;
            return;
        }
        if (H.isEmpty() && !T.isEmpty()) {
            HH.push(T.pop());
            return;
        }
        if (H.isEmpty() && T.isEmpty()) {
            if (needCopy > 1) HH.push(Hr.pop());
            if (needCopy == 1) {
                HH.push(Hr.pop());
                needCopy--;
            }
            if (needCopy == 0) {
                Stack<Item> temp = HH;
                HH = H;
                H = temp;
                temp = TT;
                TT = T;
                T = temp;
                isCopying = false;
                Hr = new Stack<Item>();
                h = new Stack<Item>();
            }
        }
    }
    
  
    
    
}
