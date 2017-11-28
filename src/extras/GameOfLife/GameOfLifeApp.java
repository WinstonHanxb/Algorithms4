package extras.GameOfLife;

public class GameOfLifeApp {

    public GameOfLifeApp() {
        new GameOfLifePresenter(new GameOfLifeViewDrawer(1080,900)).start();
    }

    public static void main(String... args) {
        new GameOfLifeApp();

    }

}
