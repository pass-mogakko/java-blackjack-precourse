package domain.dto;

import domain.user.Player;

import java.util.List;

public class GameResultDto {
    private boolean dealerWon;
    private boolean playerWon;
    private List<Player> wonPlayers;
    private boolean dealerExceeded;

    public GameResultDto(boolean dealerWon, boolean playerWon, List<Player> winners, boolean dealerExceeded) {
        this.dealerWon = dealerWon;
        this.playerWon = playerWon;
        this.wonPlayers = winners;
        this.dealerExceeded = dealerExceeded;
    }
}
