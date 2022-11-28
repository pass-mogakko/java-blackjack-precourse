package config;

import controller.BlackJackController;
import service.BlackJackGame;
import view.InputView;
import view.OutputView;

public class BlackJackContainer {
    private static BlackJackContainer blackJackContainer;

    public static BlackJackContainer getInstance() {
        if (blackJackContainer == null) {
            blackJackContainer = new BlackJackContainer();
        }
        return blackJackContainer;
    }

    public BlackJackController blackJackController () {
        return new BlackJackController(inputView(), outputView(), blackJackGame());
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }

    private BlackJackGame blackJackGame () {
        return new BlackJackGame();
    }

}
