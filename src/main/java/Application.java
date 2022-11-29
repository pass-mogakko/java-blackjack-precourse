import config.BlackJackContainer;
import controller.BlackJackController;

public class Application {
    public static void main(String[] args) {
        BlackJackContainer container = BlackJackContainer.getInstance();

        try {
            BlackJackController controller = container.blackJackController();
            controller.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
