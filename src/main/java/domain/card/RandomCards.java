package domain.card;

import domain.constant.Constant;
import java.util.List;

public class RandomCards {

    private static RandomCards randomCards = new RandomCards();

    private List<Card> cards;

    private RandomCards() {
    }

    private RandomCards(List<Card> cards) {
        this.cards = cards;
    }

    public static RandomCards newInstance(List<Card> cards) {
        return new RandomCards(cards);
    }

    public static RandomCards getInstance() {
        return randomCards;
    }

    public Card drawCard() {
        return cards.remove(Constant.CARDS_FRONT_INDEX);
    }
}
