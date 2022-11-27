package model;

import java.util.Map;

public class EarningsDto {

    private final double dealerEarning;
    private final Map<String, Double> playerEarningsByName;

    public EarningsDto(double dealerEarning, Map<String, Double> playerEarningsByName) {
        this.dealerEarning = dealerEarning;
        this.playerEarningsByName = playerEarningsByName;
    }

    public double getDealerEarning() {
        return dealerEarning;
    }

    // TODO 여기서도 Index로 조회하도록 통일?
    public double getPlayerEarningByName(String name) {
        return playerEarningsByName.get(name);
    }
}
