package chapter1.section5;

import java.util.HashMap;

//使用HashMap实现了一个并查集
//注意这种并查集的实现不允许重复的内容
public class UnionFindWIthMap<Item> {
    private HashMap<Item, Item> unionMap;
    private HashMap<Item, Integer> sizeMap;
    private int count;

    public UnionFindWIthMap() {
        this.count = 0;
        unionMap = new HashMap<>();
        sizeMap = new HashMap<>();
    }

    //返回item所在连通分量，用根来表示
    //单独的分量的根为自身
    public Item find(Item item) {
        if (null == unionMap.get(item)) throw new NullPointerException();
        Item rootItem = item;
        while (unionMap.get(rootItem) != null && unionMap.get(rootItem) != rootItem) {
            rootItem = unionMap.get(rootItem);
        }
        //路径压缩
        while (unionMap.get(item) != null && unionMap.get(item) != item) {
            item = unionMap.put(item, rootItem);
        }
        return item;
    }

    public void put(Item item) {
        if (unionMap.get(item) != null) throw new IllegalArgumentException();
        unionMap.put(item, item);
        sizeMap.put(item, 1);
        count++;
    }


    public int count() {
        return count;
    }

    public void union(Item p, Item q) {
        if (connected(p, q)) return;
        Item pId = find(p);
        Item qId = find(q);
        int sizeP = sizeMap.get(pId);//p所在连通分量大小
        int sizeQ = sizeMap.get(qId);//q所在连通分量大小
        if (sizeP > sizeQ) {
            unionMap.put(q, pId);
            sizeMap.put(pId, sizeP + sizeQ);
            count--;
        } else {
            unionMap.put(p, qId);
            sizeMap.put(qId, sizeP + sizeQ);
            count--;
        }
    }

    public boolean connected(Item p, Item q) {
        return find(p) == find(q);
    }

}
