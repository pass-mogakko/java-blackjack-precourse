package domain.dto;

import domain.user.Player;

import java.util.List;

public class GameResultDto {
    private double totalBettingMoney;
    private boolean dealerWon;
    private boolean playerWon;
    private List<Player> wonPlayers;
    private List<Player> lostPlayers;
    private boolean dealerExceeded;

    public GameResultDto(double totalBettingMoney, boolean dealerWon, boolean playerWon, List<Player> winners, List<Player> losers, boolean dealerExceeded) {
        this.totalBettingMoney = totalBettingMoney;
        this.dealerWon = dealerWon;
        this.playerWon = playerWon;
        this.wonPlayers = winners;
        this.lostPlayers = losers;
        this.dealerExceeded = dealerExceeded;
    }

    public double getTotalBettingMoney() {
        return totalBettingMoney;
    }

    public boolean isDealerWon() {
        return dealerWon;
    }

    public boolean isPlayerWon() {
        return playerWon;
    }

    public List<Player> getWonPlayers() {
        return wonPlayers;
    }

    public List<Player> getLostPlayers() {
        return lostPlayers;
    }

    public boolean isDealerExceeded() {
        return dealerExceeded;
    }
}
