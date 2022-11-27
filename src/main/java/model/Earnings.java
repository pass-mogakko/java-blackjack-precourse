package model;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Earnings {

    private final Map<String, Double> playerEarningsByName;
    private double dealerEarning;

    public Earnings(List<String> playerNames) {
        this.dealerEarning = 0;
        validatePlayerNames(playerNames);
        this.playerEarningsByName = playerNames.stream()
                .collect(Collectors.toMap(Function.identity(), name -> 0.0));
    }

    private void validatePlayerNames(List<String> playerNames) {
        if (playerNames == null) {
            throw new NullPointerException("게임 참가자 이름 정보가 null입니다.");
        }
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("게임 참가자 이름 정보가 존재하지 않습니다.");
        }
    }

    public double getDealerEarning() {
        return dealerEarning;
    }

    public double findPlayerEarningByName(String playerName) {
        Double earning = playerEarningsByName.get(playerName);
        if (earning == null) {
            throw new IllegalArgumentException("해당 이름을 가진 사용자가 존재하지 않습니다.");
        }
        return earning;
    }

    public void moveEarningFromDealerToPlayer(String playerName, double amount) {
        dealerEarning -= amount;
        updatePlayerEarningsByName(playerName, amount);
    }

    public void updatePlayerEarningsByName(String playerName, double amount) {
        playerEarningsByName.put(playerName, findPlayerEarningByName(playerName) + amount);
    }

    @Override
    public String toString() {
        return "dealerEarning=" + dealerEarning + playerEarningsByName.toString();
    }
}
