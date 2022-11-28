package domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;
import java.util.stream.Stream;

class PlayersTest {

    @DisplayName("참가자 등록: 참여 인원이 유효하지 않을 경우 예외 발생")
    @ParameterizedTest
    @MethodSource("generateStreamWithFullPlayers")
    void enrollWithFullPlayer(Map<String, Double> players) {
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> generateStreamWithFullPlayers() {
    Map<String, Double> players = Map.of(
            "testPlayer1", 10_000.0, "testPlayer2", 10_000.0, "testPlayer3", 10_000.0, "testPlayer4", 10_000.0,
            "testPlayer5", 10_000.0, "testPlayer6", 10_000.0, "testPlayer7", 10_000.0, "testPlayer8", 10_000.0
    );
    return Stream.of(Arguments.of(players));
}
    @DisplayName("참가자 등록: 플레이어의 베팅 금액이 유효하지 않을 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(doubles = {4_999, 10_000_001})
    void enrollWithInvalidBettingMoney(double invalidBettingMoney) {
        assertThatThrownBy(() -> new Players(Map.of("pobi", invalidBettingMoney)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("참가자 카드 지급: 전달받은 사용자 이름이 유효하지 않을 경우 예외 발생")
    @Test
    void addCardByInvalidName() {
        Players players = new Players(Map.of("pobi", 10_000.0));

        assertThatThrownBy(() -> players.addCardByName("wrong", new Card(Symbol.KING, Type.HEART)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}