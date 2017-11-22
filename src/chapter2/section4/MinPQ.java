package chapter2.section4;

import com.algs4.stdlib.In;

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int size;

    public MinPQ() {
        pq = (Key[]) new Comparable[1010];
        pq[0] = null;
        size = 0;
    }

    public MinPQ(int max) {
        pq = (Key[]) new Comparable[max + 1];
        size = 0;
    }

    public MinPQ(Key[] a) {
        pq = (Key[]) new Comparable[a.length + 1];
        size = 0;
        for (Key c : a) {
            Insert(c);
        }
    }



    public void Insert(Key v) {
        pq[++size] = v;
        swim(size);
    }

    public Key min() {
        return pq[1];
    }

    public Key delMin() {
        Key min = min();
        pq[1] = pq[size];
        pq[size--] = null;
        sink(1);
        return min;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
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

    private boolean more(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    public static void main(String[] args) {
        String[] a = In.readStrings("resources/words3.txt");
        MinPQ<String> pq = new MinPQ<>(a);
        while (!pq.isEmpty()) {
            System.out.print(pq.delMin() + " ");
        }
        System.out.println();
    }
}
