package domain.user;

import domain.card.Card;
import domain.card.Cards;
import java.util.List;

public abstract class User {

    protected Cards cards = new Cards();

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public abstract boolean isPossibleDrawCard();

    public List<String> collectCardToString() {
        return cards.collectCardToString();
    }

    public int computeScore() {
        return cards.computeScore();
    }
}
