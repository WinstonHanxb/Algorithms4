package chatpter1.section4;
/**
 * 斐波那契查找，实现仅通过加减法来进行查找行为
 * <p>
 * 1.针对一个N个升序int值组成的数组a
 * 2.找到一个斐波那契数Fk，大于等于N，并将数组大小扩充为Fk
 * 3.已知Fk，和Fk-1，Fk-2 = Fk - Fk-1，判断a[Fk-2]和目标数key：
 * 1.key > a[Fk-2] 在a[Fk-2 + 1 ... Fk]的范围内继续查找
 * 2.key = a[Fk-2] 找到目标
 * 3.key < a[Fk-2] 在a[0 ... Fk-2 -1]的范围内继续查找
 */
public class FibonacciSearch {
    private static class fibonacciInfo {
        static int Fk;
        static int Fk_1;

        static void init(int n) {
            int Fk_2 = 0;
            Fk_1 = 1;
            Fk = 1;
            while (Fk < n) {
                Fk_2 = Fk_1;
                Fk_1 = Fk;
                Fk = Fk_2 + Fk_1;
            }
        }
    }

    public static int fibonacciSearch(int[] a, int key) {
        return search(transfer(a), key, fibonacciInfo.Fk, fibonacciInfo.Fk_1, 0);
    }


    public static int search(int[] fibonacciArray, int key, int Fk, int Fk_1, int offset) {
        int Fk_2 = Fk - Fk_1;
        if (Fk_1 == 0) return -1;
        if (fibonacciArray[offset + Fk_2 - 1] > key) return search(fibonacciArray, key, Fk_1, Fk_2, offset);
        if (fibonacciArray[offset + Fk_2 - 1] == key) return offset + Fk_2 - 1;
        if (fibonacciArray[offset + Fk_2 - 1] < key) return search(fibonacciArray, key, Fk_1, Fk_2, offset+Fk_2);
        return -1;
    }

    /**
     * 将给定的数组转换为一个斐波那契数长度的数组
     */
    public static int[] transfer(int[] a) {
        int n = a.length;
        fibonacciInfo.init(n);
        int[] fibonacciArray = new int[fibonacciInfo.Fk];
        for (int i = 0; i < fibonacciArray.length; i++) {
            fibonacciArray[i] = i < n ? a[i] : Integer.MAX_VALUE;//用最大的整数来填充空余
        }
        return fibonacciArray;
    }



}
