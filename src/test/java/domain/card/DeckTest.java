package domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("게임 시작 시 카드를 2장씩 나눠주는지 테스트")
    @Test
    void dividedCardFirstTime() {
        Deck deck = new Deck(CardFactory.create());

        List<Card> dividedCards = deck.handOutFirstTime();

        assertThat(dividedCards.size()).isEqualTo(2);
    }

}