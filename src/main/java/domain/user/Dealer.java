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
    private final Deck deck = new Deck();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    public void giveCardToSelf() {
        Card card = deck.drawCard();

        addCard(card);
    }

    public void giveCardToPlayer(Player player) {
        Card card = deck.drawCard();

        player.addCard(card);
    }

    //getter
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
