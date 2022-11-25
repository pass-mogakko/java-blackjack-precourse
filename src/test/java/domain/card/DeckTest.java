package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class DeckTest {

    @DisplayName("카드 덱 세팅: 기본 트럼프 카드 세트로부터 한 장이라도 셔플되었는지 확인")
    @Test
    void initializeDeck() {
        Deck deck = new Deck();
        List<Card> trumpCards = CardFactory.create();
        boolean isShuffled = trumpCards.stream()
                .anyMatch(card -> !(card.equals(deck.takeOneCard())));

        assertThat(isShuffled).isTrue();
    }

    @DisplayName("카드 덱 사용: 카드를 꺼낼 때, 덱이 비었을 경우 예외 발생")
    @Test
    void takeOneCard() {
        Deck deck = new Deck();
        for (int count = 0; count < 52; count++) {
            deck.takeOneCard();
        }

        assertThatThrownBy(deck::takeOneCard).isInstanceOf(IllegalStateException.class);
    }
}