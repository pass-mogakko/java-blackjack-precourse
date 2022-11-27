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
        this.playerEarningsByName = playerNames.stream()
                .collect(Collectors.toMap(Function.identity(), name -> 0.0));
    }

    public double getDealerEarning() {
        return dealerEarning;
    }

    public double findPlayerEarningByName(String playerName) {
        return playerEarningsByName.get(playerName);
    }

    @Override
    public String toString() {
        return "dealerEarning=" + dealerEarning + playerEarningsByName.toString();
    }
}
