package chapter1.section5;

/**
 * 阿克曼函数实现，并统计总共递归调用了多少次
 * https://zh.wikipedia.org/wiki/%E9%98%BF%E5%85%8B%E6%9B%BC%E5%87%BD%E6%95%B8
 *
 * 第一阶段：递归实现
 * 第二阶段: 非递归实现
 */
public class Ackermann {
    public static int count;

    public static int ack(int m, int n) {
        count++;
        while (m > 0) {
            if (n == 0) {
                n = 1;
            } else {
                n = ack(m, n - 1);
            }
            m = m - 1;
        }
        return n + 1;
    }


    public static void main(String[] args) {
        Ackermann.count = 0;
        System.out.println(Ackermann.ack(3,4)+" "+Ackermann.count);
    }
}
