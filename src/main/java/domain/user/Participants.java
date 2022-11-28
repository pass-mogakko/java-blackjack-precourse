package domain.user;

import domain.card.Card;
import java.util.List;

public interface Participants {
    void addCard(Card card);

    List<Card> getCards();

    int getTotal();
}
