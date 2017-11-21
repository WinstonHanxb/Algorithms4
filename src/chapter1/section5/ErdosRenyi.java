package chapter1.section5;

import com.algs4.stdlib.StdIn;

import java.util.Random;

/**
 * Erdős–Rényi model ER随机图模型
 * 英文Wiki:https://en.wikipedia.org/wiki/Erd%C5%91s%E2%80%93R%C3%A9nyi_model
 * 随机图:https://zh.wikipedia.org/wiki/%E9%9A%8F%E6%9C%BA%E5%9B%BE
 */
public class ErdosRenyi {
    public static int count(int N, UnionFind unionFind) {
        int count = 0;
        while (unionFind.count() > 1) {
            Random random = new Random();
            int p = random.nextInt(N);
            int q = random.nextInt(N);
            if (!unionFind.connected(p, q)) {
                unionFind.union(p, q);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        WeightedQuickUnionWithPathCompression weightedQuickUnionWithPathCompression = new
                WeightedQuickUnionWithPathCompression(N);
        System.out.println(count(N, weightedQuickUnionWithPathCompression));
    }
}
