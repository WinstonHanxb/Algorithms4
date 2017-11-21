package chapter1.section5;

/**
 * 使用路径压缩的加权quick-union算法
 */
public class WeightedQuickUnionWithPathCompression implements UnionFind {
    private int[] id;
    private int[] sz;
    private int count;

    public WeightedQuickUnionWithPathCompression(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        //跟随链接找到根节点
        int root = p;
        while (root != id[root]) root = id[root];
        //实现路径压缩，将所有经过的节点都直接连接到根节点上
        //注意路径压缩这个行为只有在find函数寻找根节点的时候才会执行，利用这个特点，可以实现生成任意高度的树的要求
        while (p != root) {
            int temp = id[p];
            id[p] = root;
            p = temp;
        }
        return p;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        //将比较小的树挂在比较大的树上
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

}
