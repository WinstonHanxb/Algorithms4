package chatpter1.section3;

import java.util.Iterator;
import java.util.Random;

/**
 * 随机背包，迭代器可以随便访问背包中的所有元素
 */
public class RandomBag<Item> implements Iterable<Item> {
    private Item[] items = (Item[]) new Object[1];
    private int N;

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void add(Item item){
        items[N++] = item;
        if (N == items.length) resize(2 * items.length);//装满的时候调整数组大小
    }

    private void resize(int length) {
        Item[] newArr = (Item[]) new Object[length];
        for (int i = 0; i < N; i++) {
            newArr[i] = items[i];
        }
        items = newArr;
    }




    @Override
    public Iterator<Item> iterator() {
        return new RandomBagIterator();
    }

    private class RandomBagIterator implements Iterator<Item>{
        private int count = N;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public Item next() {
            int i = new Random().nextInt(count);
            //交换i和count-1的元素的位置，并且减少count数
            Item item = items[i];
            items[i] = items[--count];
            items[count] = item;

            return item;
        }
    }
}
