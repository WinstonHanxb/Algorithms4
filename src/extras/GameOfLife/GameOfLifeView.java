package extras.GameOfLife;

public interface GameOfLifeView {
    void drawGeneration(final boolean[][] generation);
    int getHeight();
    int getWidth();
}
