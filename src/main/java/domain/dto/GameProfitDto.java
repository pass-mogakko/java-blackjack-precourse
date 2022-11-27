package domain.dto;

import java.util.List;

public class GameProfitDto {
    private List<Double> playerProfits;
    private double dealerProfit;

    public GameProfitDto(List<Double> playerProfits, double dealerProfit) {
        this.playerProfits = playerProfits;
        this.dealerProfit = dealerProfit;
    }

    public String getPlayerProfit(int idx) {
        return String.format("%.0f", playerProfits.get(idx));
    }

    public String getDealerProfit() {
        return String.format("%.0f", dealerProfit);
    }
}
