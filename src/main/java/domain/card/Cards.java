package domain.card;

import domain.constant.Constant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public List<String> collectCardToString() {
        return cards.stream()
                .map(Card::cardToString)
                .collect(Collectors.toList());
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int computeScore() {
        int cardsScore = sumCardsScoreRegardlessAce();
        int aceCount = countAce();

        while (isNeedScoreAdjustment(cardsScore, aceCount)) {
            aceCount--;
            cardsScore -= Constant.ADJUSTMENT_ACE_SCORE;
        }
        return cardsScore;
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(card -> card.isAce())
                .count();
    }

    private int sumCardsScoreRegardlessAce() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean isNeedScoreAdjustment(int cardsScore, int aceCount) {
        return cardsScore > Constant.TWENTY_ONE && aceCount > Constant.ZERO;
    }

    public boolean isSmallerThan21() {
        return computeScore() <= Constant.TWENTY_ONE;
    }

    public boolean isSmallerThan16() {
        return computeScore() <= Constant.SIXTEEN;
    }
}
