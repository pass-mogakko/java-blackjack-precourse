package controller;

public class BlackjackController {

    private static final BlackjackController blackjackController = new BlackjackController();

    private BlackjackController() {
    }

    public static BlackjackController getInstance() {
        return blackjackController;
    }

    public void run() {

    }
}
