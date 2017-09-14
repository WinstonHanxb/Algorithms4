package extras.GameOfLife;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Presenter层
 * 用来计算每一代的新的结构
 */
public class GameOfLifePresenter {
    private final GameOfLifeViewDrawer view;
    private boolean[][] currentGeneration;

    public GameOfLifePresenter(final GameOfLifeViewDrawer view) {
        this.view = view;
        currentGeneration = initGeneration();
    }

    private TimerTask task;

    // ...

    public void start() {
        task = new TimerTask() {
            @SuppressWarnings("synthetic-access")
            @Override
            public void run() {
                currentGeneration = progressGeneration(currentGeneration);
                view.drawGeneration(currentGeneration);
            }
        };
        new Timer().schedule(task, 0, 50);
    }

    public void stop() {
        if (task == null) {
            return;
        }
        task.cancel();
    }

    private boolean[][] initGeneration(){
        boolean[][] firstGeneration = new boolean[view.getWidth()][view.getHeight()];
        for (int row = 0; row < firstGeneration.length; ++row) {
            for (int col = 0; col < firstGeneration[row].length; ++col) {
                if(Math.random()>0.5) firstGeneration[row][col] = true;
            }
        }
        return firstGeneration;
    }

    private boolean[][] progressGeneration(final boolean[][] generation) {
        final boolean[][] nextGeneration = cloneGeneration(generation);
        for (int row = 0; row < generation.length; ++row) {
            for (int col = 0; col < generation[row].length; ++col) {
                final int numNeighbors = countNeighbors(generation, row, col);
                if ((numNeighbors < 2) || (numNeighbors > 3)) {
                    nextGeneration[row][col] = false;
                }
                if (numNeighbors == 2) {
                    nextGeneration[row][col] = generation[row][col];
                }
                if (numNeighbors == 3) {
                    nextGeneration[row][col] = true;
                }
            }
        }

        return nextGeneration;
    }

    private boolean[][] cloneGeneration(final boolean originalGeneration[][]) {
        final boolean[][] newGeneration = new boolean[originalGeneration.length][];
        for (int row = 0; row < originalGeneration.length; ++row) {
            newGeneration[row] = Arrays.copyOf(originalGeneration[row], originalGeneration[row].length);
        }
        return newGeneration;
    }

    private int countNeighbors(final boolean[][] generation, final int row, final int col) {
        int numNeighbors = 0;

        // Look 左上角
        if ((row - 1 >= 0) && (col - 1 >= 0)) {
            numNeighbors = generation[row - 1][col - 1] ? numNeighbors + 1 : numNeighbors;
        }
        // Look 左边
        if ((row >= 0) && (col - 1 >= 0)) {
            numNeighbors = generation[row][col - 1] ? numNeighbors + 1 : numNeighbors;
        }
        // Look 左下角
        if ((row + 1 < generation.length) && (col - 1 >= 0)) {
            numNeighbors = generation[row + 1][col - 1] ? numNeighbors + 1 : numNeighbors;
        }
        // Look 下面
        if ((row + 1 < generation.length) && (col < generation[0].length)) {
            numNeighbors = generation[row + 1][col] ? numNeighbors + 1 : numNeighbors;
        }
        // Look 右下角
        if ((row + 1 < generation.length) && (col + 1 < generation[0].length)) {
            numNeighbors = generation[row + 1][col + 1] ? numNeighbors + 1 : numNeighbors;
        }
        // Look 右边
        if ((row < generation.length) && (col + 1 < generation[0].length)) {
            numNeighbors = generation[row][col + 1] ? numNeighbors + 1 : numNeighbors;
        }
        // Look 右上角
        if ((row - 1 >= 0) && (col + 1 < generation[0].length)) {
            numNeighbors = generation[row - 1][col + 1] ? numNeighbors + 1 : numNeighbors;
        }
        // Look 上面
        if ((row - 1 >= 0) && (col < generation[0].length)) {
            numNeighbors = generation[row - 1][col] ? numNeighbors + 1 : numNeighbors;
        }

        return numNeighbors;
    }

}
