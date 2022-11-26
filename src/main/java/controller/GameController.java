package controller;

import domain.BlackjackGame;
import domain.card.Card;
import domain.card.CardFactory;
import domain.dto.BlackjackResultDto;
import domain.dto.CardValueDto;
import domain.dto.GameScoreDto;
import domain.dto.PlayerNameDto;
import domain.user.Dealer;
import domain.user.Player;
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
    private final DtoBuilder dtoBuilder = new DtoBuilder();
    private final BlackjackGame blackjackGame = new BlackjackGame();

    private final List<Player> players = createPlayers();
    private final Dealer dealer = new Dealer();
    private final List<Card> cardDeck = new ArrayList<>(CardFactory.create());

    public void run() {
        blackjackGame.start(players, dealer, cardDeck);
        printFirstResult(players, dealer);

//        Object result = blackjackGame.play(players, dealer);
//        printFinalResult(result, players, dealer);
//    }
//
//    public void playGame() {
        Object result = blackjackGame.play(players, dealer);
        if (result.getClass() == BlackjackResultDto.class) {
            printFinalResult(result, players, dealer);
            return;
        }
        if (result.getClass() != null) {
            askAboutNewCard((List<Player>) result);
        }
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

    private void doProperAction() {

    }

    private void askAboutNewCard(List<Player> players) {
        for (Player player : players) {
            while (true) {
                boolean wantNewCard = getPlayerCommand(player);
                if (!wantNewCard)
                    break;
                dealer.giveOneCardToPlayer(player, cardDeck);
            }
        }
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

}