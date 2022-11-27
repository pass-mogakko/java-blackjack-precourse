package domain.dto;

import java.util.Map;

public class GameProfitDto {
    private Map<String, Double> playerProfits;
    private double dealerProfit;

    public GameProfitDto(Map<String, Double> playerProfits, double dealerProfit) {
        this.playerProfits = playerProfits;
        this.dealerProfit = dealerProfit;
    }

    public String getPlayerProfit(String playerName) {
        return String.format("%.0f", playerProfits.get(playerName));
    }

    public String getDealerProfit() {
        return String.format("%.0f", dealerProfit);
    }
}
