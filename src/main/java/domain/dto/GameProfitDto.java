package domain.dto;

import java.util.Map;

public class GameProfitDto {
    private Map<String, Double> playerProfits;
    private double dealerProfit;

    public GameProfitDto(Map<String, Double> playerProfits, double dealerProfit) {
        this.playerProfits = playerProfits;
        this.dealerProfit = dealerProfit;
    }

    public Map<String, Double> getPlayerProfits() {
        return playerProfits;
    }

    public double getPlayerProfit(String playerName) {
        return playerProfits.get(playerName);
    }

    public double getDealerProfit() {
        return dealerProfit;
    }
}
