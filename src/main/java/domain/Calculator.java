package domain;

import domain.card.Card;
import java.util.List;



public class Calculator {
    private static final int BLACKJACK_NUMBER = 21;

    public static int calculateAceCards(int aceCount, int totalScore) {
        for (int count = 0; count < aceCount; count++) {
            if ((totalScore + 10) <= BLACKJACK_NUMBER) {
                totalScore += 10;
            }
        }

        return totalScore;
    }

    public static int calculateCards(List<Card> cards) {
        // ACE가 모두 1이라고 생각하고 덧셈
        int totalScore = cards.stream().mapToInt(Card::getScore).sum();
        int aceCount = getAceCount(cards);

        // 그 후, 21이 초과되지 않을 때 까지 11로 취급하고 더함
        totalScore = calculateAceCards(aceCount, totalScore);

        return totalScore;
    }


    public static int getAceCount(List<Card> cards) {
        return (int)cards.stream().filter(card -> card.isAce()).count();
    }
}
