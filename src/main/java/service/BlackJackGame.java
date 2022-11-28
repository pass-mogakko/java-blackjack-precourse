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

    public void divideFirstTime() {
        dealer.addCards(deck.handOutFirstTime());
        players.addCardsFirstTime(deck);
    }

    public void divideMoreCardOfPlayer(String name) {
        players.handOutMoreCardByName(name,deck.handOutMoreCard());
    }

    public void divideMoreCardToDealer() {
        if (dealer.getTotal() < 17) {
            dealer.addCard(deck.handOutMoreCard());
        }
    }

    public boolean isMoreCardToDealer() {
        return dealer.getTotal() < 17;
    }


    public UsersDTO getUsers() {
        return new UsersDTO(dealer, players);
    }

}
