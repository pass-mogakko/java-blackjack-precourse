package domain.user;

import static org.assertj.core.api.Assertions.*;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

class UserTest {

    @DisplayName("사용자 카드 지급: 이미 가지고 있는 카드와 동일한 카드를 지급받았을 경우 예외 발생")
    @Test
    void addCard() {
        User user = new User();
        Card duplicatedCard = new Card(Symbol.KING, Type.CLUB);
        user.addCard(duplicatedCard);

        assertThatThrownBy(() -> user.addCard(duplicatedCard)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드 합 계산: Ace를 가지고 있는 경우, 21을 넘지 않거나 21에 더 가까운 합으로 계산")
    @ParameterizedTest
    @MethodSource("generateStreamOfCardsAndExpectedScore")
    void addAllScore(List<Card> cards, int expectedScore) {
        User user = new User();
        cards.forEach(user::addCard);

        assertThat(user.addAllScore()).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> generateStreamOfCardsAndExpectedScore() {
        List<Arguments> arguments = new LinkedList<>();
        Card aceCard1 = new Card(Symbol.ACE, Type.CLUB);
        Card aceCard2 = new Card(Symbol.ACE, Type.DIAMOND);
        arguments.add(Arguments.of(List.of(aceCard1, aceCard2),12));
        arguments.add(Arguments.of(List.of(aceCard1, new Card(Symbol.FIVE, Type.CLUB)), 16));
        arguments.add(Arguments.of(List.of(aceCard1, new Card(Symbol.TEN, Type.CLUB)),21));
        arguments.add(Arguments.of(
                List.of(aceCard1, new Card(Symbol.TEN, Type.CLUB), new Card(Symbol.TEN, Type.DIAMOND)), 21));
        return arguments.stream();
    }

    @DisplayName("버스트 여부 확인: 가지고 있는 카드의 합이 21을 넘으면 참을 반환")
    @Test
    void isBust() {
        User user = new User();
        user.addCard(new Card(Symbol.KING, Type.CLUB));
        user.addCard(new Card(Symbol.KING, Type.DIAMOND));

        assertThat(user.isBust()).isFalse();

        user.addCard(new Card(Symbol.TWO, Type.SPADE));

        assertThat(user.isBust()).isTrue();
    }
}