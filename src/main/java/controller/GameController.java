package controller;

import domain.BlackjackGame;
import domain.card.Card;
import domain.card.CardFactory;
import domain.dto.CardValueDto;
import domain.dto.PlayerNameDto;
import domain.user.Dealer;
import domain.user.Player;
import util.Converter;
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
    private final BlackjackGame blackjackGame = new BlackjackGame();

    public void startGame() {
        List<Player> players = createPlayers();
        Dealer dealer = new Dealer();
        List<Card> cardDeck = new ArrayList<>(CardFactory.create());

        blackjackGame.start(players, dealer, cardDeck);
        PlayerNameDto playerInfo = buildPlayerInfo(players);
        CardValueDto firstResult = blackjackGame.getCardValues(players, dealer);
        outputView.printFirstCards(playerInfo, firstResult);
    }

    private PlayerNameDto buildPlayerInfo(List<Player> players) {
        List<String> playerNames = blackjackGame.getPlayerNameList(players);
        return new PlayerNameDto(playerNames);
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

}