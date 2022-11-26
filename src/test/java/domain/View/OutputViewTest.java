package domain.View;

import static org.junit.jupiter.api.Assertions.*;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;



class OutputViewTest {
    private static final OutputView outputView = new OutputView();

    @Test
    void printDealerCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Symbol.ACE, Type.CLUB));

        outputView.printDealerCards(cards);
    }

    @Test
    void printPlayerCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Symbol.ACE, Type.CLUB));
        cards.add(new Card(Symbol.NINE, Type.HEART));

        outputView.printPlayerCards(cards, "pobi");
    }
}