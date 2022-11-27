package model;

import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class OpenedCardsDto {

    private final Dealer dealer;
    private final List<Player> players;

    public OpenedCardsDto(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
