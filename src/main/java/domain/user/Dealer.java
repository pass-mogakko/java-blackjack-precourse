package domain.user;

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

    //getter
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
