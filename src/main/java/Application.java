import controller.GameController;
import domain.card.Deck;

public class Application {

    public static void main(String[] args) {
        new GameController().run(new Deck());
    }
}
