package service;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Players;
import dto.UsersDTO;

public class BlackJackGame {

    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    public BlackJackGame(Dealer dealer, Players players, Deck deck) {
        this.dealer = dealer;
        this.players = players;
        this.deck = deck;
    }

    public void handOutFirstTime() {
        deck.handOut(dealer);
        deck.handOutPlayers(players);
    }

    public UsersDTO getUsers() {
        return new UsersDTO(dealer, players);
    }

}
