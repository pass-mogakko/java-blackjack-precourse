package controller;

import view.InputView;

import java.util.List;

public class GameController {

    private final InputView inputView = new InputView();

    public void startGame() {
        List<String> names = inputView.readPlayerNames();
    }
}
