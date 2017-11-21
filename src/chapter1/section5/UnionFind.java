package chapter1.section5;

public interface UnionFind {
    //在P和q之间添加一条连接
    void union(int p,int q);
    //p（0到N-1）所在的分量的标识符
    int find(int p);
    //如果p和q存在于同一个分量中则返回true
    boolean connected(int p, int q);
    //连通分量的数量
    int count();
}
