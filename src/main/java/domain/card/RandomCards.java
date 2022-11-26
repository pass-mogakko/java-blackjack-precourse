package domain.card;

import constant.Constant;
import java.util.List;

public class RandomCards {

    private static RandomCards randomCards;
    private static List<Card> cards;

    private RandomCards(List<Card> cards) {
        RandomCards.cards = cards;
    }

    public static RandomCards newInstance(List<Card> cards) {
        randomCards = new RandomCards(cards);
        return randomCards;
    }

    public static RandomCards getInstance() {
        return randomCards;
    }

    public Card drawCard() {
        return cards.remove(Constant.CARDS_FRONT_INDEX);
    }
}
