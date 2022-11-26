package controller;

import domain.constant.Constant;
import java.util.List;
import service.DealerService;
import service.GameInitService;
import service.PlayersService;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private static final BlackjackController blackjackController = new BlackjackController();
    private static final GameInitService gameInitService = GameInitService.getInstance();
    private static final PlayersService playersService = PlayersService.getInstance();
    private static final DealerService dealerService = DealerService.getInstance();
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
        drawCards(playersName);
    }

    private List<String> createPlayers() {
        String playersName = inputView.requestPlayerName();
        List<String> parsedPlayersName = gameInitService.parsePlayersName(playersName);
        parsedPlayersName.forEach(this::createPlayer);
        return parsedPlayersName;
    }

    private void createPlayer(String name) {
        String bettingMoney = inputView.requestPlayerBettingMoney(name);
        int parsedBettingMoney = gameInitService.parsePlayerBettingMoney(bettingMoney);
        playersService.createPlayer(name, parsedBettingMoney);
    }

    private void initCards(List<String> playersName) {
        List<List<String>> playersHasCard = playersService.initCards();
        List<String> dealerHasCard = dealerService.initCards();
        outputView.printDrawTwoCard(playersName, playersHasCard, dealerHasCard);
    }

    private void drawCards(List<String> playersName) {
        playersName.forEach(this::playerDrawCard);
        dealerDrawCard();
    }

    private void playerDrawCard(String playerName) {
        boolean isPossibleDrawCard = true;
        while (isPossibleDrawCard) {
            String requestDrawCard = inputView.requestDrawCard(playerName);
            isPossibleDrawCard = requestDrawCardBranch(requestDrawCard, playerName);
        }
    }

    private boolean requestDrawCardBranch(String requestDrawCard, String playerName) {
        if (requestDrawCard.equals(Constant.YES)) {
            return yesDrawCard(playerName);
        }
        return noDrawCard(playerName);
    }

    private boolean yesDrawCard(String playerName) {
        List<String> playerHasCard = playersService.drawCard(playerName);
        outputView.printPlayerHasCard(playerHasCard, playerName);
        return playersService.isPossibleDrawCard(playerName);
    }

    private boolean noDrawCard(String playerName) {
        List<String> playerHasCard = playersService.findPlayerHasCard(playerName);
        outputView.printPlayerHasCard(playerHasCard, playerName);
        return false;
    }

    private void dealerDrawCard() {
        boolean isPossibleDrawCard = dealerService.isPossibleDrawCard();
        while (isPossibleDrawCard) {
            dealerService.drawCard();
            outputView.printDealerDrawCard();
            isPossibleDrawCard = dealerService.isPossibleDrawCard();
        }
    }

}
