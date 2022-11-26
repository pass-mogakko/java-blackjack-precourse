package util;

import domain.dto.CardValueDto;
import domain.dto.GameScoreDto;
import domain.dto.PlayerNameDto;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DtoBuilder {

    Calculator calculator = new Calculator();

    public CardValueDto buildCardValueInfo(List<Player> players, Dealer dealer) {
        Map<String, String> allPlayerCardValues = new HashMap<>();
        for (Player player : players) {
            allPlayerCardValues.put(player.getName(), player.getCardValues());
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

        Map<String, Integer> playerScores = new HashMap<String, Integer>();
        for (Player player : players) {
            playerScores.put(player.getName(), calculator.addAllCardScore(player.getCards()));
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
}
