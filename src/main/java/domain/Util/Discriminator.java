package domain.Util;

import domain.card.Card;

import java.util.List;



public class Discriminator {
    private static final int BLACKJACK_NUMBER = 21;
    private static final int DEALER_HIT_LOWER_LIMIT = 16;


    public static boolean isBlackjack(List<Card> cards) {
        if (Calculator.calculateCards(cards) == BLACKJACK_NUMBER
                && cards.size() == 2) {
            return true;
        }

        return false;
    }

    public static boolean isBust(List<Card> cards) {
        if (Calculator.calculateCards(cards) > BLACKJACK_NUMBER) {
            return true;
        }

        return false;
    }

    public static boolean isShouldHit(List<Card> cards) {
        if (Calculator.calculateCards(cards) <= DEALER_HIT_LOWER_LIMIT) {
            return true;
        }

        return false;
    }
}
