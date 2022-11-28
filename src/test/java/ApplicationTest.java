import static org.assertj.core.api.Assertions.assertThat;

import controller.GameController;
import domain.card.Card;
import domain.card.CardDistributor;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

class ApplicationTest extends RunTest {

    @ParameterizedTest
    @MethodSource("generateStreamOfPlayerBlackJack")
    void 기능_테스트(List<Card> addingCards) {
        run(addingCards, "pobi, jason, joon", "10000", "20000", "50000", "n", "n");
        assertThat(output())
                .doesNotContain("pobi는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
                .contains("딜러: Q클로버, 7스페이드 - 결과: 17\n" +
                        "pobi카드: A하트, K다이아몬드 - 결과: 21\n" +
                        "jason카드: 9하트, K하트 - 결과: 19\n" +
                        "joon카드: J다이아몬드, 2스페이드 - 결과: 12\n\n" +
                        "## 최종 수익\n" +
                        "딜러: 15000\n" +
                        "pobi: 15000\n" +
                        "jason: 20000\n" +
                        "joon: -50000");
    }

    private static Stream<Arguments> generateStreamOfPlayerBlackJack() {
        List<Card> addingCards = new LinkedList<>(List.of(
                // dealer cards
                new Card(Symbol.QUEEN, Type.CLUB), new Card(Symbol.SEVEN, Type.SPADE),
                // blackjack player cards
                new Card(Symbol.ACE, Type.HEART), new Card(Symbol.KING, Type.DIAMOND),
                // win player cards
                new Card(Symbol.NINE, Type.HEART), new Card(Symbol.KING, Type.HEART),
                // lose player cards
                new Card(Symbol.JACK, Type.DIAMOND), new Card(Symbol.TWO, Type.SPADE)
        ));
        return Stream.of(Arguments.of(addingCards));
    }

    @Override
    void runMain(List<Card> addingCards) {
        new GameController().run(new TestDistributor(addingCards));
    }

    private static class TestDistributor implements CardDistributor {

        private final Queue<Card> addingCards;

        public TestDistributor(List<Card> addingCards) {
            this.addingCards = new LinkedList<>(addingCards);
        }

        @Override
        public Card takeOneCard() {
            return addingCards.poll();
        }
    }
}