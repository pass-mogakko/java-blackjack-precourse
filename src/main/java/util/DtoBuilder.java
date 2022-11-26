package util;

import domain.dto.CardValueDto;
import domain.dto.PlayerNameDto;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DtoBuilder {
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

    private List<String> getPlayerNameList(List<Player> players) {
        List<String> playerNames = new ArrayList<>();
        for (Player player : players) {
            playerNames.add(player.getName());
        }
        return playerNames;
    }
}
