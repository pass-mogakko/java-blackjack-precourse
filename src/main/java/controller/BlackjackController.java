package controller;

import dto.PlayersNameDto;
import java.util.List;
import service.BlackjackService;
import view.InputView;

public class BlackjackController {

    private static final BlackjackController blackjackController = new BlackjackController();
    private static final BlackjackService blackJackService = BlackjackService.getInstance();
    private static final InputView inputView = new InputView();

    private BlackjackController() {
    }

    public static BlackjackController getInstance() {
        return blackjackController;
    }

    public void run() {
        String playersName = inputView.requestPlayerName();
        PlayersNameDto parsePlayersName = blackJackService.parsePlayersName(playersName);
        List<String> names = parsePlayersName.get();
        names.forEach(name -> {
            String playerBettingMoney = inputView.requestPlayerBettingMoney(name);
        });
    }
}
