package extras.GameOfLife;

import edu.princeton.cs.algs4.StdDraw;

/**
 * GameOfLife的view层
 * 单纯用来画图
 */
public class GameOfLifeViewDrawer implements GameOfLifeView {
    private final int width;
    private final int height;

    public GameOfLifeViewDrawer(final int width, final int height) {
        this.width = width / 3;
        this.height = height / 3;
//        StdDraw.setCanvasSize(width*2, height*3);
        StdDraw.setCanvasSize(width, height);
        StdDraw.setYscale(0, height);
        StdDraw.setXscale(0, width);
        StdDraw.setPenColor(StdDraw.GRAY);
    }

    @Override
    public void drawGeneration(final boolean[][] generation) {
        StdDraw.show(0);
        StdDraw.clear();
        for (int row = 0; row < generation.length; row++) {
            for (int col = 0; col < generation[row].length; col++) {
                if (generation[row][col] == true) {
                    StdDraw.filledRectangle(3 * col + 1.5, 3 * row + 1.5, 1.5, 1.5);
//                    StdDraw.point(col,row);
                }
            }
        }
        StdDraw.show(0);
    }

    @Override
    public int getHeight() {
        return width;
    }

    @Override
    public int getWidth() {
        return height;
    }


}
