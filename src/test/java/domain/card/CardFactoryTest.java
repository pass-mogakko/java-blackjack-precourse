package domain.card;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardFactoryTest {
    @Test
    void validateCreateDeck() {
        CardFactory cardFactory = new CardFactory();
        HashMap<Integer, Card> deck = cardFactory.createDeck();
        assertThat(deck).hasSize(52);
    }
}
