package util;

import domain.card.Card;

import java.util.List;

public class Calculator {

    public int addAllCardScore(List<Card> cards) {
        int sum = 0;
        for (Card card : cards) {
            int score = card.getSymbolScore();
            sum += score;
        }
        if (hasAce(cards))
            sum = optimizeAce(sum);
        return sum;
    }

    private int optimizeAce(int sum) {
        if (sum <= 11) {
            sum += 10;
        }
        return sum;
    }

    private boolean hasAce(List<Card> cards) {
        for (Card card : cards) {
            if (card.getSymbolScore() == 1) {
                return true;
            }
        }
        return false;
    }
}
