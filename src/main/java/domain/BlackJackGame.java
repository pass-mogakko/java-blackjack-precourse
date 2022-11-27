package domain;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.HashMap;

import static domain.card.RandomNumber.getRandomNumber;

public class BlackJackGame {
    private static final CardFactory cardFactory = new CardFactory();
    private static final HashMap<Integer, Card> deck = cardFactory.createDeck();

    public static void run() {


    }

    private void handOutPlayerCard(Player player) {
        final int index = getRandomNumber(deck);
        player.addCard(deck.get(index));
        deleteCard(index);
    }

    private void handOutDealerCard(Dealer dealer) {
        final int index = getRandomNumber(deck);
        dealer.addCard(deck.get(index));
        deleteCard(index);
    }

    private void deleteCard(int index) {
        deck.remove(index);
    }
}
