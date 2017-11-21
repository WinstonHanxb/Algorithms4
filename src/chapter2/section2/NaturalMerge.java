package chapter2.section2;

import chapter2.Example;
import com.algs4.stdlib.In;

import java.util.ArrayList;
import java.util.List;

/**
 * 自然归并排序：
 * 自然归并利用的是数组中已经有序的数组逐步归并成比较大的有序数组
 * 每一次归并之前，
 */
public class NaturalMerge implements Example {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        List<Integer> indexList = indexList(a);
        while(indexList.size()>2){
            for (int i = 0; i < indexList.size(); i+=2) {
                if(i+2<indexList.size()){
                    merge(a,indexList.get(i),indexList.get(i+1),indexList.get(i+2)-1);
                }
            }
            indexList = indexList(a);
        }
    }

    

    //原地归并
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        //将a[lo..mid] 和 a[mid+1..hi] 归并
        int i = lo, j = mid;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {a[k] = aux[j++];}
            else if (j > hi){a[k] = aux[i++];}
            else if (Example.less(aux[j], aux[i])){a[k] = aux[j++];}
            else{a[k] = aux[i++];}
        }
    }
  

    /**
     * 遍历整个数组，将数组中增量序列的起点下标组成的数组返回
     * @param a
     * @return
     */
    private static List<Integer> indexList(Comparable[] a){
        List<Integer> tempInts = new ArrayList<Integer>();
        tempInts.add(0);//首下标要加入
        for (int i = 0; i < a.length-1; i++) {
            //当a[i+1]>a[i]时很显然这不是一个有序的数组
            if(Example.less(a[i+1],a[i])) tempInts.add(i+1);
        }
        tempInts.add(a.length);//加入一个类似哨兵的标记位
        return tempInts;
    }
    public static void main(String[] args) {
        String[] a = In.readStrings("resources/words3.txt");
        sort(a);
        if (Example.isSorted(a)) {
            Example.show(a);
        } else {
            System.out.println("wrong");
        }
    }
}
