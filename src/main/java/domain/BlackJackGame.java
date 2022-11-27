package domain;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.HashMap;
import java.util.Random;

public class BlackJackGame {
    private static final int CARD_NUMBER = 51;
    private static final HashMap<Integer, Card> deck = new HashMap<>();
    private static final CardFactory cardFactory = new CardFactory(deck);

    public static void run() {


    }

    private static int getRandomNumber() {
        Random random = new Random();
        int index = random.nextInt(CARD_NUMBER);
        while(!cardFactory.findNumber(index)){
            index = random.nextInt(CARD_NUMBER);
        }
        return index;
    }
}
