package chapter2.section4;

import com.algs4.stdlib.In;

public class MaxPQ<Key extends Comparable<Key>> {
    //为了计算方便，pq数组计数从1开始
    private Key[] pq;
    private int size;

    public MaxPQ() {
        pq = (Key[]) new Comparable[101];
        pq[0] = null;
        size = 0;
    }

    public MaxPQ(int max) {
        pq = (Key[]) new Comparable[max+1];
        pq[0] = null;
        size = 0;
    }

    public MaxPQ(Key[] a) {
        pq = (Key[]) new Comparable[a.length+1];
        size = 0;
        pq[0] = null;
        for (Key c : a) {//数组从1开始储存
            Insert(c);
        }
    }

    public void Insert(Key v) {
        pq[++size] = v;
        swim(size);
    }

    public Key max() {
        return pq[1];
    }

    public Key delMax() {
        Key max = max();
        pq[1] = pq[size];
        pq[size--] = null;
        sink(1);
        return max;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    //上浮操作
    private void swim(int k) {
        while (k / 2 != 0) {
            int j = k / 2;
            if (less(j, k)) {
                exch(j, k);
            }else{
                break;
            }
            k = j;
        }
    }

    //下潜操作
    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j + 1 <= size && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    public static void main(String[] args) {
        String[] a = In.readStrings("resources/words3.txt");
        MaxPQ<String> pq = new MaxPQ<>(a);
        while(!pq.isEmpty()){
            System.out.print(pq.delMax()+" ");
        }
        System.out.println();
    }
}
