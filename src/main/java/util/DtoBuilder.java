package util;

import domain.dto.*;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class DtoBuilder {

    Calculator calculator = new Calculator();

    public CardValueDto buildCardValueInfo(List<Player> players, Dealer dealer) {
        List<String> allPlayerCardValues = new ArrayList<>();
        for (Player player : players) {
            allPlayerCardValues.add(player.getCardValues());
        }
        String dealerCardValues = dealer.getCardValues();
        return new CardValueDto(allPlayerCardValues, dealerCardValues);
    }

    public PlayerNameDto buildPlayerInfo(List<Player> players) {
        List<String> playerNames = getPlayerNameList(players);
        return new PlayerNameDto(playerNames);
    }

    public GameScoreDto buildGameScore(List<Player> players, Dealer dealer) {
        int dealerScore = calculator.addAllCardScore(dealer.getCards());

        List<Integer> playerScores = new ArrayList<>();
        for (Player player : players) {
            playerScores.add(calculator.addAllCardScore(player.getCards()));
        }
        return new GameScoreDto(playerScores, dealerScore);
    }

    private List<String> getPlayerNameList(List<Player> players) {
        List<String> playerNames = new ArrayList<>();
        for (Player player : players) {
            playerNames.add(player.getName());
        }
        return playerNames;
    }

    public BlackjackResultDto buildBlackjackResult(List<Player> players, Dealer dealer, List<Player> blackjackPlayers) {
        double totalBettingMoney = calculator.calculateTotalBettingMoney(players);
        boolean dealerBlackjack = dealer.isBlackjack();
        boolean playerBlackjack = blackjackPlayers.size() > 0;
        return new BlackjackResultDto(totalBettingMoney, dealerBlackjack, playerBlackjack, blackjackPlayers);
    }

    public GameResultDto buildGameResult(List<Player> players, Dealer dealer) {
        double totalBettingMoney = calculator.calculateTotalBettingMoney(players);
        List<Player> wonPlayers = buildWonPlayers(players);
        List<Player> lostPlayers = buildLostPlayers(players);
        int maxScore = getMaxScore(wonPlayers, dealer);
        int dealerScore = (calculator.addAllCardScore(dealer.getCards()));
        if (maxScore < dealerScore)
            wonPlayers = new ArrayList<>(List.of());
        return new GameResultDto(totalBettingMoney,dealerScore >= maxScore, maxScore >= dealerScore, wonPlayers, lostPlayers, dealerScore > 21);
    }

    private int getMaxScore(List<Player> wonPlayers, Dealer dealer) {
        if (wonPlayers.size() > 0) {
            return calculator.addAllCardScore(wonPlayers.get(0).getCards());
        }
        if (calculator.addAllCardScore(dealer.getCards()) <= 21) {
            return calculator.addAllCardScore(dealer.getCards());
        }
        return 0;
    }

    private List<Player> buildWonPlayers(List<Player> players) {
        int maxScore = 0;
        List<Player> wonPlayers = new ArrayList<>();
        for (Player player : players) {
            int playerScore = calculator.addAllCardScore(player.getCards());
            if (playerScore <= 21 && playerScore > maxScore) {
                wonPlayers = new ArrayList<>(List.of(player));
                maxScore = playerScore;
            }
            if (playerScore == maxScore) wonPlayers.add(player);
        }
        return wonPlayers;
    }

    private List<Player> buildLostPlayers(List<Player> players) {
        List<Player> lostPlayers = new ArrayList<>();
        for (Player player : players) {
            int playerScore = calculator.addAllCardScore(player.getCards());
            if (playerScore > 21) {
                lostPlayers.add(player);
            }
        }
        return lostPlayers;
    }

    public GameProfitDto buildNormalResultProfit(GameResultDto gameResultDto, List<Player> players, Dealer dealer) {
        List<Double> playerProfits = new ArrayList<>();
        for (Player player : players) {
            playerProfits.add(player.calculateNormalProfit(gameResultDto));
        }
        double dealerPofit = dealer.calculateNormalProfit(gameResultDto, players);
        return new GameProfitDto(playerProfits, dealerPofit);
    }

    public GameProfitDto buildBlackjackResultProfit(BlackjackResultDto blackjackResult, List<Player> players, Dealer dealer) {
        List<Double> playerProfits = new ArrayList<>();
        for (Player player : players) {
            playerProfits.add(player.calculateBlackjackProfit(blackjackResult));
        }
        double dealerPofit = dealer.calculateBlackjackProfit(blackjackResult, players);
        return new GameProfitDto(playerProfits, dealerPofit);
    }
}
