package chatpter1.section4;

import java.util.Random;

/**
 * 丢鸡蛋问题
 * 假设有一栋N层的大楼和许多鸡蛋，假设将鸡蛋从F层或者更高的地方扔下才会摔碎，找出这个值F
 * <p>
 * Google的丢鸡蛋问题：
 * 假设你只有两个鸡蛋可以试错
 * 在这个前提下用最低的成本找出这个值F
 * <p>
 * 更一般的情况：
 * 假设有一栋N层的大楼，有m个鸡蛋，我们要找到最坏情况下最小的尝试次数
 * <p>
 */
public class EggDroppingPuzzle {
    int height;//最高能有多高
    int eggs;//最多能有多少个鸡蛋
    int[][] f;//缓存计算过的结果，优化计算速度

    public EggDroppingPuzzle(int height, int eggs) {
        this.height = height;
        this.eggs = eggs;
        f = new int[height + 1][eggs + 1];
    }

    //m表示鸡蛋数量，n表示楼层数量
    private int findF(int n, int m) {
        int min = n + 1;
        if (m == 1) {//只有一个鸡蛋
            return n;
        }
        if (n == 1){
            return 1;
        }

        for (int i = 1; i <= n; i++) {//找到其中次数最小的
            if (f[i - 1][m - 1] == 0) {
                f[i - 1][m - 1] = findF(i - 1, m - 1);
            }
            if (f[n - i][m] == 0) {
                f[n - i][m] = findF(n - i, m);
            }
            int temp = f[i - 1][m - 1] > f[n - i][m] ? (1 + f[i - 1][m - 1]) :
                    (1 + f[n - i][m]);
            min = min > temp ? temp : min;
        }
        return min;
    }

    public static void main(String[] args) {
        EggDroppingPuzzle puzzle = new EggDroppingPuzzle(1000, 100);
        System.out.println(puzzle.findF(1000, 100));

    }
}
