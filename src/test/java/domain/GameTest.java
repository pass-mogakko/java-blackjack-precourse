package domain;

import static org.junit.jupiter.api.Assertions.*;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;



class GameTest {

    @Test
    void isBlackjack() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Symbol.ACE, Type.CLUB));
        cards.add(new Card(Symbol.JACK, Type.SPADE));

        assertTrue(Game.isBlackjack(cards));
    }
}