package domain.dto;

import domain.user.Player;
import java.util.List;

public class BlackjackResultDto {
    private final double totalBettingMoney;
    private final boolean dealer;
    private final boolean player;
    private final List<Player> winners;

    public BlackjackResultDto(double totalBettingMoney, boolean dealerTf, boolean playerTf, List<Player> winners) {
        this.totalBettingMoney = totalBettingMoney;
        this.dealer = dealerTf;
        this.player = playerTf;
        this.winners = winners;
    }

    public double getTotalBettingMoney() {
        return totalBettingMoney;
    }

    public boolean isDealer() {
        return dealer;
    }

    public boolean isPlayer() {
        return player;
    }

    public List<Player> getWinners() {
        return winners;
    }
}
