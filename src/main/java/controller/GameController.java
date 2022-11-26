package controller;

import domain.BlackjackGame;
import domain.dto.CardValueDto;
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

    public void run() {
        List<Player> players = createPlayers();
        Dealer dealer = new Dealer();
        blackjackGame.start(players, dealer);
        printFirstResult(players, dealer);

        Object result = blackjackGame.play(players, dealer);
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

    private void printFirstResult(List<Player> players, Dealer dealer) {
        PlayerNameDto playerInfo = dtoBuilder.buildPlayerInfo(players);
        CardValueDto firstResult = dtoBuilder.buildCardValueInfo(players, dealer);
        outputView.printFirstCards(playerInfo, firstResult);
    }

}