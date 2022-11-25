package controller;

import dto.BettingMoneyDto;
import dto.PlayersNameDto;
import java.util.List;
import service.BlackjackService;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private static final BlackjackController blackjackController = new BlackjackController();
    private static final BlackjackService blackJackService = BlackjackService.getInstance();
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    private BlackjackController() {
    }

    public static BlackjackController getInstance() {
        return blackjackController;
    }

    public void run() {
        List<String> playersName = createPlayers();
        initCards(playersName);
    }

    private List<String> createPlayers() {
        String playersName = inputView.requestPlayerName();
        PlayersNameDto parsedPlayersName = blackJackService.parsePlayersName(playersName);
        List<String> names = parsedPlayersName.get();
        names.forEach(this::createPlayer);
        return names;
    }

    private void createPlayer(String name) {
        String bettingMoney = inputView.requestPlayerBettingMoney(name);
        BettingMoneyDto parsedBettingMoney = blackJackService.parsePlayerBettingMoney(bettingMoney);
        blackJackService.createPlayer(name, parsedBettingMoney.get());
    }

    private void initCards(List<String> playersName) {
        String dealerHasFirstCard = blackJackService.initCards();
        outputView.printDrawTwoCardsEveryone(playersName);

        outputView.printDealerHasFirstCard(dealerHasFirstCard);

    }
}
