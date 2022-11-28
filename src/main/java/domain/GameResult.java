package domain;

import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class GameResult {
    private final List<Player> players;
    private final Dealer dealer;
    private final List<Integer> score = new ArrayList<>();

    public GameResult(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public List<Integer> getPlayersScore() {
        return score;
    }

    private void setPlayerScore() {
        for (Player player : players) {
            score.add(getPlayerTotalScore(player));
        }
    }

    private int getDealerTotalScore() {
        assert dealer != null;
        return dealer.calculateDealerScore();
    }

    private int getPlayerTotalScore(Player player) {
        assert player != null;
        return player.calculatePlayerScore();
    }
}
