package controller;

import domain.constant.Constant;
import dto.BettingMoneyDto;
import dto.CardsDto;
import dto.IsPossibleDrawCardDto;
import dto.PlayerHasCardDto;
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
        drawCards(playersName);
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
        CardsDto cardsDto = blackJackService.initCards();
        outputView.printDrawTwoCard(playersName, cardsDto);
    }

    private void drawCards(List<String> playersName) {
        playersName.forEach(this::drawCard);
    }

    private void drawCard(String playerName) {
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
        PlayerHasCardDto playerHasCard = blackJackService.drawCard(playerName);
        outputView.printPlayerHasCard(playerHasCard.getPlayersHasCard(), playerName);
        IsPossibleDrawCardDto possibleDrawCard = blackJackService.isPossibleDrawCard(playerName);
        return possibleDrawCard.isPossibleDrawCard();
    }

    private boolean noDrawCard(String playerName) {
        PlayerHasCardDto playerHasCard = blackJackService.findPlayerHasCard(playerName);
        outputView.printPlayerHasCard(playerHasCard.getPlayersHasCard(), playerName);
        return false;
    }

}
