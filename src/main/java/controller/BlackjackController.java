package controller;

import domain.constant.Constant;
import domain.dto.CardsToStringDto;
import domain.dto.GameResultDto;
import domain.dto.PlayerCardsToStringDto;
import java.util.List;
import service.BlackjackService;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private static final BlackjackController blackjackController = new BlackjackController();
    private static final BlackjackService blackjackService = BlackjackService.getInstance();
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    private BlackjackController() {
    }

    public static BlackjackController getInstance() {
        return blackjackController;
    }


    public void run() {
        List<String> playersName = createPlayers();
        initCards();
        drawCards(playersName);
        printResult();
    }


    private List<String> createPlayers() {
        String playersName = inputView.requestPlayerName();
        try {
            List<String> parsedPlayersName = blackjackService.parsePlayersName(playersName);
            parsedPlayersName.forEach(this::createPlayer);
            return parsedPlayersName;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return createPlayers();
        }
    }

    private void createPlayer(String name) {
        String bettingMoney = inputView.requestPlayerBettingMoney(name);
        try {
            blackjackService.createPlayer(name, bettingMoney);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            createPlayer(name);
        }
    }


    private void initCards() {
        CardsToStringDto cardsToStringDto = blackjackService.initCards();
        outputView.printDrawTwoCard(cardsToStringDto);
    }


    private void drawCards(List<String> playersName) {
        playersName.forEach(this::playerDrawCard);
        int drawCount = blackjackService.dealerDrawCard();
        outputView.printDealerDrawCard(drawCount);
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
        PlayerCardsToStringDto playerCardsToStringDto = blackjackService.drawPlayerCard(playerName);
        outputView.printPlayerHasCards(playerCardsToStringDto);
        return playerCardsToStringDto.isPossibleDrawCard();
    }

    private boolean noDrawCard(String playerName) {
        PlayerCardsToStringDto playerCardsToStringDto = blackjackService.findPlayerHasCards(playerName);
        outputView.printPlayerHasCards(playerCardsToStringDto);
        return false;
    }


    private void printResult() {
        GameResultDto gameResultDto = blackjackService.computeGameResult();
        outputView.printGameResult(gameResultDto);
    }
}
