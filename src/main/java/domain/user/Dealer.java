package domain.user;

import domain.Calculator;
import domain.card.Card;

import domain.card.CardFactory;
import domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        if (Calculator.calculateCards(cards) > 21) {
            return true;
        }

        return false;
    }

    public boolean isShouldHit() {
        if (Calculator.calculateCards(cards) <= 16) {
            return true;
        }

        return false;
    }

    //getter
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
