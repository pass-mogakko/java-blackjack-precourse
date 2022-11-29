package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import util.calculator.TotalCalculator;
import util.calculator.ResultCalculator;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer implements Participants {

    private static final int FIRST_CARD_INDEX = 0;

    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    public void addCards(List<Card> addCards) {
        cards.addAll(addCards);
    }

    @Override
    public int getTotal() {
        return (int) TotalCalculator.calculateTotal(cards);
    }

    public Card getFirstCard() {
        return cards.get(FIRST_CARD_INDEX);
    }

    public boolean isBlackJack() {
        return ResultCalculator.isBlackJack(cards);
    }

    public List<Card> getCards() {
        return this.cards;
    }
}
