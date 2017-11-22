package chapter2.section4;

import com.algs4.stdlib.In;

/**
 * 索引优先队列
 * 优先队列虽然性能很高，但是一旦插入，要修改队列中的某个元素就变得很困难，因此给队列加上索引便于修改队列
 * @param <Item>
 */
public class IndexMinPQ<Item extends Comparable<Item>> {
    private int size;//PQ中的元素数量
    private int[] pq;//索引二叉堆，由1开始,存储的是items中元素对应的索引值
    private int[] qp;//qp是pq的逆序：qp[pq[i]] = pq[qp[i]] = i
    private Item[] items;//有优先级之分的元素


    public IndexMinPQ(int maxN){
        items = (Item[]) new Comparable[maxN+1];
        pq = new int[maxN+1];
        qp = new int[maxN+1];
        size = 0;
        for (int i = 0;i<= maxN;i++){
            qp[i] = -1;
        }
    }

    public boolean contains(int k){
        return qp[k] != -1;
    }

    public void insert(int k, Item item){
        if(!contains(k)){
            items[k] = item;
            pq[++size] = k;
            qp[k] = size;
            swim(size);
        }else{
            change(k,item);
        }
    }
    public void change(int k, Item item){
        if(!contains(k)){
            insert(k,item);
        }else{
            if(item.compareTo(items[k])>0){
                items[k] = item;
                sink(qp[k]);
            }else{
                items[k] = item;
                swim(qp[k]);
            }
        }
    }
    public void delete(int k){
        if(contains(k)){
            int index = qp[k];
            exch(index, size--);
            swim(index);
            sink(index);
            items[k] = null;
            qp[k] = -1;
        }
    }
    public Item min(){
        return items[pq[1]];
    }
    public int minIndex(){
        return pq[1];
    }
    public int delMin(){
        delete(pq[1]);
        return pq[1];
    }

    private boolean more(int i, int j) {
        //比较函数比较的是pq所存储的索引指向的item元素大小
        return items[pq[i]].compareTo(items[pq[j]]) > 0;
    }
    private void exch(int i, int j) {
        //exch交换的是pq所存储的两个索引的位置
        //qp存储的是pq中值的所在位置，交换pq的时候，要先交换qp存储的内容
        int s = qp[pq[i]];
        qp[pq[i]] = qp[pq[j]];
        qp[pq[j]] = s;
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;

    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j + 1 <= size && more(j, j + 1)) {
                j++;
            }
            if (!more(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private void swim(int k) {
        while (k / 2 != 0) {
            int j = k / 2;
            if (more(j, k)) {
                exch(j, k);
            } else {
                break;
            }
            k = j;
        }
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        String[] a = In.readStrings("resources/words3.txt");
        IndexMinPQ<String> pq = new IndexMinPQ<>(a.length);
        for (int i = 0; i < a.length; i++) {
            pq.insert(i+1,a[i]);
        }
        while(!pq.isEmpty()){
            System.out.print(pq.min()+" ");
            pq.delMin();
        }
        System.out.println();
    }
}
