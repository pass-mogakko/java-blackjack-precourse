package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int addAllScore() {
        int totalScore = cards.stream()
                .map(Card::getScore)
                .reduce(0, Integer::sum);
        return addMoreOrNotByAce(totalScore, hasAce(cards));
    }

    private int addMoreOrNotByAce(int totalScore, boolean hasAce) {
        if (totalScore < 12 && hasAce) {
            return totalScore + 10;
        }
        return totalScore;
    }

    private boolean hasAce(List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }
}
