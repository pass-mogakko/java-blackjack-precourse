package domain;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.HashMap;
import java.util.List;

import static domain.card.RandomNumber.getRandomNumber;

public class BlackJackGame {
    private final List<Player> players;
    private final Dealer dealer;
    private final HashMap<Integer, Card> deck;

    private static final int FIRST_CARD_COUNT = 2;

    public BlackJackGame(List<Player> players, Dealer dealer) {
        final CardFactory cardFactory = new CardFactory();
        this.players = players;
        this.dealer = dealer;
        this.deck = cardFactory.createDeck();
    }

    public List<Player> getPlayers(){
        return players;
    }

    public Dealer getDealer(){
        return dealer;
    }

    public void run() {

    }

    public void handOutFirstTwoCards() {
        for (int count = 0; count < FIRST_CARD_COUNT; count++) {
            handOutPlayerCard();
            handOutDealerCard();
        }
    }

    private void handOutPlayerCard() {
        for (Player player : players) {
            final int index = getRandomNumber(deck);
            player.addCard(deck.get(index));
            removeCard(index);
        }
    }

    private void handOutDealerCard() {
        final int index = getRandomNumber(deck);
        dealer.addCard(deck.get(index));
        removeCard(index);
    }

    private void removeCard(int index) {
        deck.remove(index);
    }

    public int getDeckSize() {
        return deck.size();
    }
}
