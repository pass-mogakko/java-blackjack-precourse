import controller.BlackjackController;

public class Application {

    public static void main(String[] args) {
        BlackjackController blackjackController = BlackjackController.getInstance();
        blackjackController.run();
    }
}
