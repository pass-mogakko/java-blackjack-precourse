import config.BlackJackContainer;
import controller.BlackJackController;

public class Application {
    public static void main(String[] args) {
        BlackJackContainer container = BlackJackContainer.getInstance();
        BlackJackController controller = container.blackJackController();

        controller.run();
    }
}
