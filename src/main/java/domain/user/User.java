package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        validateDuplicatedCard(card);
        cards.add(card);
    }

    private void validateDuplicatedCard(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException("중복된 카드를 받을 수 없습니다.");
        }
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

    public boolean isBust() {
        return addAllScore() > 21;
    }

    public boolean isBlackJack() {
        return (addAllScore() == 21) && (cards.size() == 2);
    }
}
