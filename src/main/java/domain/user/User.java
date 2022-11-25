package domain.user;

import domain.card.Card;
import domain.card.Cards;

public class User {

    protected Cards cards = new Cards();

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public boolean isPossibleDrawCard() {
        return cards.isPossibleDrawCard();
    }

}
