package extras.EventListener;

public class Client {
    public static void main(String[] args) {
        ConcreteMouseListener listener = new ConcreteMouseListener();
        Button button = new Button();
        button.addMouseListener(listener);
        button.doClick();
    }
}
