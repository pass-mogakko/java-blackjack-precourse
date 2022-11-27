package domain;

import domain.card.Card;
import domain.card.CardFactory;

import java.util.HashMap;
import java.util.Random;

public class BlackJackGame {
    private static final int CARD_NUMBER = 51;

    public static void run(){
        HashMap<Integer, Card> deck = new HashMap<>();
        CardFactory cardFactory = new CardFactory(deck);

    }

    private static int getRandomNumber(){
        Random random = new Random();
        return random.nextInt(CARD_NUMBER);
    }
}
