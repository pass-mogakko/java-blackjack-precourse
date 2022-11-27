package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Deck {
    List<Card> cards;


    public Deck() {
        cards = new ArrayList<>(CardFactory.create());
        Collections.shuffle(cards);
    }


    public Card drawCard() {
        return cards.remove(0);
    }
}
