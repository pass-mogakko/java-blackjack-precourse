package domain.card;

import domain.constant.Constant;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public Card drawCard() {
        return cards.remove(Constant.CARDS_FRONT_INDEX);
    }
}
