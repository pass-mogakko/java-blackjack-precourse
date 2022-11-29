package util;

import domain.card.Card;
import domain.card.Symbol;
import java.util.List;

public class TotalCalculator {

    public static double calculate(List<Card> cards) {
        if (cards.contains(Symbol.ACE)) {
            return aceCalculate(cards);
        }
        return calculateTotal(cards);
    }

    public static double calculateTotal(List<Card> cards) {
        return cards.stream()
                .mapToInt(card -> card.getSymbol().getScore())
                .sum();
    }

    public static double aceCalculate(List<Card> cards) {
        double max = calculateTotal(cards) + 10;
        double min = calculateTotal(cards);
        if (max > 21) return min;

        if (Math.abs(21-max) > Math.abs(21-min)) {
            return min;
        }
        return max;
    }

}
