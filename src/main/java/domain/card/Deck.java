package domain.card;

import java.util.ArrayList;
import java.util.List;



public class Deck {
    List<Card> card;

    public Deck() {
        card = new ArrayList<>(CardFactory.create());
    }


}
