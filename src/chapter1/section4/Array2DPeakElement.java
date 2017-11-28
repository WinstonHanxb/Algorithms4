package chapter1.section4;

/**
 * 给定一个2D的由不同整数组成的N*N矩阵，找出其中的局部最小项，要求时间复杂度与N成正比
 * 注：只实现了算法的思想，并没有经过实际的用例测试
 */
public class Array2DPeakElement {
    //矩阵的项类型
    private class Entry {
        public int x;//x坐标
        public int y;//y坐标
        public int value;//坐标值
        boolean isCorner = false;

        public Entry() {
        }

        public Entry(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    public int findLocalMinimumInitial(int[][] a, int N) {
        Entry x = findCircumferenceMinimum(a, 0, N, 0, N);
        Entry v = null;
        if (x.isCorner) {//如果在角落，必然是最小项
            return x.value;
        }

        //不是角落项则必然只满足下面四个条件其中一个
        if (x.x == 0) {
            v = new Entry(1, x.y, a[1][x.y]);
        }
        if (x.x == N - 1) {
            v = new Entry(N - 2, x.y, a[N - 2][x.y]);
        }
        if (x.y == 0) {
            v = new Entry(x.x, 1, a[x.x][1]);
        }
        if (x.y == N - 1) {
            v = new Entry(x.x, N - 2, a[x.x][N - 2]);
        }


        if (v.value > x.value) {
            return x.value;
        } else return this.findLocalMinimumGeneral(a, v, 0, N, 0, N);

    }

    public int findLocalMinimumGeneral(int[][] a, Entry vortex, int x_start, int x_length, int y_start, int y_length) {
        Entry result = new Entry(0, 0, Integer.MAX_VALUE);


        if (x_length <= 3 || y_length <= 3) {
            for (int x = x_start; x < x_length; x++) {
                if (result.value > a[x][vortex.y]) {
                    result = new Entry(x, vortex.y, a[x][vortex.y]);
                }
            }
            for (int y = y_start; y < y_length; y++) {
                if (result.value > a[vortex.x][y]) {
                    result = new Entry(vortex.x, y, a[vortex.x][y]);
                }
            }
            return result.value;
        }

        Entry minimumX = getMinimumX(a, x_start, x_length, y_start, y_length);

        Entry vSlash = vortex.value < minimumX.value ? vortex : minimumX;

        for (int i = 0; i < 9; i++) {
            if (this.isInside(vSlash, getX_start(x_start, x_length, i), getX_length(x_length, i), getY_start
                    (y_start, y_length, i), getY_length(y_length, i))) {

                //v’是这个子矩阵范围的项
                if (this.isInterior(a, vSlash, getX_start(x_start, x_length, i), getX_length(x_length, i),
                        getY_start(y_start, y_length, i), getY_length(y_length, i))) {
                    return this.findLocalMinimumGeneral(a, vSlash, getX_start(x_start, x_length, i), getX_length
                            (x_length, i), getY_start(y_start, y_length, i), getY_length(y_length, i));
                } else {
                    //v’不是这个子矩阵的内部项，则取所有包含v'子矩阵组成一个新的子矩阵，v'作为内部漩涡项进行新一轮计算
                    int minX = Integer.MAX_VALUE;
                    int maxX = Integer.MIN_VALUE;
                    int minY = Integer.MAX_VALUE;
                    int maxY = Integer.MIN_VALUE;
                    for (int j = 0; j < 9; j++) {
                        if (this.isInside(vSlash, getX_start(x_start, x_length, i), getX_length(x_length, i), getY_start
                                (y_start, y_length, i), getY_length(y_length, i))) {
                            minX = minX < getX_start(x_start, x_length, i) ? minX : getX_start(x_start, x_length, i);
                            maxX = maxX > getX_start(x_start, x_length, i) + getX_length(x_length, i) - 1 ?
                                    maxX : getX_start(x_start, x_length, i) + getX_length(x_length, i) - 1;
                            minY = minY < getY_start(y_start, y_length, i) ? minY : getY_start(y_start, y_length, i);
                            maxY = maxY > getY_start(y_start, y_length, i) + getY_length(y_length, i) - 1 ?
                                    maxY : getY_start(y_start, y_length, i) + getY_length(y_length, i) - 1;
                        }
                    }
                    return this.findLocalMinimumGeneral(a, vSlash, minX, maxX - minX + 1, minY, maxY - minY + 1);
                }
            }
        }
        return result.value;
    }

    private int getY_start(int y_start, int y_length, int i) {
        return y_start + y_length * (i / 3) / 3;
    }

    private int getY_length(int y_length, int i) {
        return (i / 3) == 2 ? y_length - 2 / 3 * y_length : y_length / 3;
    }

    private int getX_length(int x_length, int i) {
        return (i % 3) == 2 ? x_length - 2 / 3 * x_length : x_length / 3;
    }

    private int getX_start(int x_start, int x_length, int i) {
        return x_start + x_length * (i % 3) / 3;
    }

    /**
     * 将指定范围的子矩阵9等分，找出所有子矩阵的边项中最小的项
     */
    private Entry getMinimumX(int[][] a, int x_start, int x_length, int y_start, int y_length) {
        Entry minimumX = new Entry(0, 0, Integer.MAX_VALUE);
        for (int i = 0; i < 9; i++) {
            Entry entryI = this.findCircumferenceMinimum(a,
                    getX_start(x_start, x_length, i),
                    getX_length(x_length, i),
                    getY_start(y_start, y_length, i),
                    getY_length(y_length, i));
            if (minimumX.value > entryI.value) {
                minimumX = entryI;
            }
        }
        return minimumX;
    }


    /**
     * 检查目标项是否在指定子矩阵范围中（包括边）
     */
    private boolean isInside(Entry target, int x_start, int x_length, int y_start, int y_length) {
        return x_start <= target.x && target.x <= x_start + x_length - 1
                && y_start <= target.y && target.y <= y_start + y_length - 1;
    }

    /**
     * 检查目标是否在指定子矩阵范围的内部（不包括边）
     */
    private boolean isInterior(int[][] a, Entry target, int x_start, int x_length, int y_start, int y_length) {
        for (int x = x_start; x < x_length; x++) {//横着的边
            if (target.value == a[x][y_start] || target.value == a[x][y_start + y_length - 1]) {
                return false;
            }
        }
        for (int y = y_start; y < y_length; y++) {//竖着的边
            if (target.value == a[x_start][y] || target.value == a[x_start + x_length - 1][y]) {
                return false;
            }
        }
        return true;
    }


    /**
     * 给定子矩阵的范围，找出这个子矩阵范围内边上的最小项
     */
    private Entry findCircumferenceMinimum(int[][] a, int x_start, int x_length, int y_start, int y_length) {
        Entry minimumEntry = new Entry(0, 0, Integer.MAX_VALUE);
        for (int x = x_start; x < x_length; x++) {//横着的边
            if (minimumEntry.value > a[x][y_start]) {
                minimumEntry = new Entry(x, y_start, a[x][y_start]);
            }
            if (minimumEntry.value > a[x][y_start + y_length - 1]) {
                minimumEntry = new Entry(x, y_start + y_length - 1, a[x][y_start + y_length - 1]);
            }
        }
        for (int y = y_start; y < y_length; y++) {//竖着的边
            if (minimumEntry.value > a[x_start][y]) {
                minimumEntry = new Entry(x_start, y, a[x_start][y]);
            }
            if (minimumEntry.value > a[x_start + x_length - 1][y]) {
                minimumEntry = new Entry(x_start + x_length - 1, y, a[x_start + x_length - 1][y]);
            }
        }
        minimumEntry.isCorner = (minimumEntry.x == x_start && minimumEntry.y == y_start)
                || (minimumEntry.x == x_start && minimumEntry.y == y_start + y_length - 1)
                || (minimumEntry.x == x_start + x_length - 1 && minimumEntry.y == y_start)
                || (minimumEntry.x == x_start + x_length - 1 && minimumEntry.y == y_start + y_length - 1);
        return minimumEntry;
    }





}
