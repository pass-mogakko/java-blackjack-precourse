package domain.card;

import java.util.HashMap;
import java.util.Random;

public class RandomNumber {
    private static final int CARD_NUMBER = 51;

    public static int getRandomNumber(HashMap<Integer, Card> deck) {
        Random random = new Random();
        int index = random.nextInt(CARD_NUMBER);
        while(!findNumber(deck, index)){
            index = random.nextInt(CARD_NUMBER);
        }
        return index;
    }

    private static boolean findNumber(HashMap<Integer, Card> deck, int index) {
        return deck.containsKey(index);
    }
}
