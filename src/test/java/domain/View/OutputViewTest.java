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

    @Test
    void printPlayerResultCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Symbol.ACE, Type.CLUB));
        cards.add(new Card(Symbol.ACE, Type.HEART));
        cards.add(new Card(Symbol.ACE, Type.SPADE));
        cards.add(new Card(Symbol.ACE, Type.DIAMOND));

        // 14 출력되어야 함
        outputView.printPlayerResultCards(cards, "pobi");
    }

    @Test
    void printDealerResultCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Symbol.ACE, Type.CLUB));
        cards.add(new Card(Symbol.NINE, Type.HEART));
        cards.add(new Card(Symbol.ACE, Type.SPADE));

        // 21 출력되어야 함
        outputView.printDealerResultCards(cards);
    }

}