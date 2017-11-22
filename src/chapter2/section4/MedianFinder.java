package chapter2.section4;

/**
 * 利用最大最小堆实现常数时间内找出中位数
 * 中位数：奇数个数中间那个数，或偶数个数中间两个数的平均值
 */
public class MedianFinder {
    private MaxPQ<Integer> maxPQ;
    private MinPQ<Integer> minPQ;

    public MedianFinder() {
        this.maxPQ = new MaxPQ<>(1000);
        this.minPQ = new MinPQ<>(1000);
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        MedianFinder finder = new MedianFinder();
        for (int num : ints) {
            finder.insert(num);
            System.out.println("median: " + finder.getMedian());
        }
    }

    public void insert(int num) {
        if (minPQ.isEmpty() && maxPQ.isEmpty()) {
            minPQ.Insert(num);
            return;
        }
        if (num >= minPQ.min()) {
            minPQ.Insert(num);
        } else {
            maxPQ.Insert(num);
        }
        evenPQ();
    }

    //调整两个队列使得始终两个队列的数量差不超过1
    private void evenPQ() {
        while (Math.abs(maxPQ.size() - minPQ.size()) > 1) {
            if (maxPQ.size() > minPQ.size()) {
                minPQ.Insert(maxPQ.delMax());
            } else {
                maxPQ.Insert(minPQ.delMin());
            }
        }
    }

    public int getMedian() {
        if (maxPQ.size() - minPQ.size() != 0) {
            return minPQ.min();
        } else {
            return (maxPQ.max() + minPQ.min()) / 2;
        }
    }
}
