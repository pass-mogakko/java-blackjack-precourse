package domain.card;

import domain.constant.Constant;
import java.util.List;

public class RandomCards {

    private final List<Card> randomCards;

    public RandomCards(List<Card> randomCards) {
        this.randomCards = randomCards;
    }

    public Card drawCard() {
        return randomCards.remove(Constant.CARDS_FRONT_INDEX);
    }
}
