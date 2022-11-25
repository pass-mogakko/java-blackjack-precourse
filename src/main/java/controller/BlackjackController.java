package controller;

import dto.BettingMoneyDto;
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
        initPlayers();
    }

    private void initPlayers() {
        String playersName = inputView.requestPlayerName();
        PlayersNameDto parsedPlayersName = blackJackService.parsePlayersName(playersName);
        List<String> names = parsedPlayersName.get();
        names.forEach(this::requestPlayerBettingMoney);
    }

    private void requestPlayerBettingMoney(String name) {
        String bettingMoney = inputView.requestPlayerBettingMoney(name);
        BettingMoneyDto parsedBettingMoney = blackJackService.parsePlayerBettingMoney(bettingMoney);
        blackJackService.createPlayer(name, parsedBettingMoney.get());
    }
}
