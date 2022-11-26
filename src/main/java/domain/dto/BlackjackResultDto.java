package domain.dto;

import domain.user.Player;
import java.util.List;

public class BlackjackResultDto {
    private final boolean dealer;
    private final boolean player;
    private final List<Player> winners;

    public BlackjackResultDto(boolean dealerTf, boolean playerTf, List<Player> winners) {
        this.dealer = dealerTf;
        this.player = playerTf;
        this.winners = winners;
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
