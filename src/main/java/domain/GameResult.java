package domain;

import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class GameResult {
    private final List<Player> players;
    private final Dealer dealer;

    public GameResult(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public int getPlayerTotalScore(Player player){
        return player.calculatePlayerScore();
    }

    public int getDealerTotalScore(){
        return dealer.calculateDealerScore();
    }
}
