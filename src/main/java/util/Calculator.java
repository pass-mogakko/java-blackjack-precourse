package util;

import domain.card.Card;

import java.util.List;

public class Calculator {
    public int addAllCardScore(List<Card> cards) {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getSymbolScore();
        }
        return sum;
    }
}
