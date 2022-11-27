package controller;

import domain.BlackjackGame;
import domain.card.Card;
import domain.card.CardFactory;
import domain.dto.*;
import domain.user.Dealer;
import domain.user.Player;
import util.Calculator;
import util.Converter;
import util.DtoBuilder;
import util.Validator;
import view.InputView;
import view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final Converter converter = new Converter();
    private final Validator validator = new Validator();
    private final Calculator calculator = new Calculator();
    private final DtoBuilder dtoBuilder = new DtoBuilder();

    private final List<Player> players = createPlayers();
    private final Dealer dealer = new Dealer();
    private final List<Card> cardDeck = new ArrayList<>(CardFactory.create());
    private final BlackjackGame blackjackGame = new BlackjackGame(players, dealer, cardDeck);


    public void startGame() {
        blackjackGame.start();
        printFirstResult(players, dealer);
    }

    public void playGame() {
        if (isBlackjackAndFinish()) return;

        List<Player> affordablePlayers = blackjackGame.findAffordablePlayers();
        if (affordablePlayers.size() > 0)
            askAboutNewCard(affordablePlayers);

        updateDealerCards();
        finishGame();
    }

    public void finishGame() {
        GameResultDto gameResultDto = dtoBuilder.buildGameResult(players, dealer);
        printFinalResult(gameResultDto, players, dealer);

        GameProfitDto gameProfitDto = dtoBuilder.buildGameProfit(gameResultDto, players, dealer);
        printFinalProfit(gameProfitDto, players);
    }

    private List<Player> createPlayers() {
        List<String> names = getPlayerNames();
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            double bettingMoney = getBettingMoney(name);
            Player player = new Player(name, bettingMoney);
            players.add(player);
        }
        return players;
    }

    private boolean isBlackjackAndFinish() {
        if (blackjackGame.isBlackjack()) {
            BlackjackResultDto gameResult = blackjackGame.buildBlackjackResult();
            printFinalResult(gameResult, players, dealer);
            return true;
        }
        return false;
    }

    private void askAboutNewCard(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            while (calculator.addAllCardScore(player.getCards()) <= 21) {
                if (!getPlayerCommand(player))
                    break;
                dealer.giveOneCardToPlayer(player, cardDeck);
            }
            outputView.printPlayerCardValue(player.getName(), dtoBuilder.buildCardValueInfo(players, dealer), i);
        }
    }

    private void updateDealerCards() {
        if (dealer.getAdditionalCard(cardDeck)) {
            outputView.printDealerGotCard();
            return;
        }
        outputView.printDealerDidNotGetCard();
    }

    private List<String> getPlayerNames() {
        while (true) {
            try {
                return getInputPlayerNames();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private List<String> getInputPlayerNames() {
        Object input = inputView.readPlayerNames();
        List<String> names = converter.convertToNames(input);
        validator.validatePlayerNames(names);
        return names;
    }

    private double getBettingMoney(String name) {
        while (true) {
            try {
                return getInputBettingMoney(name);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private double getInputBettingMoney(String name) {
        Object input = inputView.readBettingPrice(name);
        double bettingMoney = converter.convertToDouble(input);
        validator.validateBettingPrice(bettingMoney);
        return bettingMoney;
    }

    private boolean getPlayerCommand(Player player) {
        while (true) {
            try {
                return getInputPlayerCommand(player);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private boolean getInputPlayerCommand(Player player) {
        Object input = inputView.readPlayerCommand(player.getName());
        return converter.convertToBoolean(String.valueOf(input));
    }

    private void printFirstResult(List<Player> players, Dealer dealer) {
        PlayerNameDto playerInfo = dtoBuilder.buildPlayerInfo(players);
        CardValueDto firstResult = dtoBuilder.buildCardValueInfo(players, dealer);
        outputView.printFirstCards(playerInfo, firstResult);
    }

    private void printFinalResult(Object resultDto, List<Player> players, Dealer dealer) {
        if (resultDto.getClass() == BlackjackResultDto.class) {
            outputView.printBlackjackMessage();
        }
        PlayerNameDto playerNameDto = dtoBuilder.buildPlayerInfo(players);
        CardValueDto cardValueDto = dtoBuilder.buildCardValueInfo(players, dealer);
        GameScoreDto gameScoreDto = dtoBuilder.buildGameScore(players, dealer);
        outputView.printGameResult(playerNameDto, cardValueDto, gameScoreDto);
    }

    private void printFinalProfit(GameProfitDto gameProfitDto, List<Player> players) {
        PlayerNameDto playerNameDto = dtoBuilder.buildPlayerInfo(players);
        outputView.printGameProfit(gameProfitDto, playerNameDto);
    }

}