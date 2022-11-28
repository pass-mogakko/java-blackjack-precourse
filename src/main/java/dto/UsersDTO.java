package dto;

import domain.user.Dealer;
import domain.user.Players;

public class UsersDTO {
    private Dealer dealer;
    private Players players;

    public UsersDTO(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public Players getPlayers() {
        return this.players;
    }
}
