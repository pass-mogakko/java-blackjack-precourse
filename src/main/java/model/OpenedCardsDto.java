package model;

import domain.card.Card;
import domain.user.Player;

import java.util.List;

public class OpenedCardsDto {

    private final List<Card> dealerCards;
    private final List<Player> players;

    public OpenedCardsDto(List<Card> dealerCards, List<Player> players) {
        this.dealerCards = dealerCards;
        this.players = players;
    }

    public List<Card> getDealerCards() {
        return  dealerCards;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
