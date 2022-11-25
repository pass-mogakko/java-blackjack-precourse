package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private final List<Card> cards = new ArrayList<>();

    public Cards() {
    }

    public List<String> collectCardToString() {
        return cards.stream()
                .map(Card::cardToString)
                .collect(Collectors.toList());
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
