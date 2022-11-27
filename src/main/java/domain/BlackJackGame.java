package domain;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.HashMap;
import java.util.Random;

public class BlackJackGame {
    private static final int CARD_NUMBER = 51;
    private static final CardFactory cardFactory = new CardFactory();
    private static final HashMap<Integer, Card> deck = cardFactory.createDeck();

    public static void run() {


    }

    private int getRandomNumber() {
        Random random = new Random();
        int index = random.nextInt(CARD_NUMBER);
        while(!findNumber(index)){
            index = random.nextInt(CARD_NUMBER);
        }
        return index;
    }

    private boolean findNumber(int index) {
        return deck.containsKey(index);
    }

    private void deleteCard(int index) {
        deck.remove(index);
    }
}
