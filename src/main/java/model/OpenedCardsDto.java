package model;

import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class OpenedCardsDto {

    // TODO Dto인데 Dealer, Player가 상태 변경 메소드를 지원함. Dto 전용으로 따로 만들거나 할 것
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
