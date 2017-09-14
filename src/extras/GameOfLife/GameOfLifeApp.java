package extras.GameOfLife;

public class GameOfLifeApp {

    public GameOfLifeApp() {
        new GameOfLifePresenter(new GameOfLifeViewDrawer(1000,500)).start();
    }

    public static void main(String... args) {
        new GameOfLifeApp();

    }

}
