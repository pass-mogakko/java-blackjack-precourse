package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class BlackJackGameTest {

    @DisplayName("참가자 등록: 참여 인원이 유효하지 않을 경우 예외 발생")
    @ParameterizedTest
    @MethodSource("generateStreamWithInvalidCount")
    void enrollWithInvalidCount(Map<String, Integer> namesWithBettingMoney) {
        BlackJackGame blackJackGame = new BlackJackGame();

        assertThatThrownBy(() -> blackJackGame.enrollPlayers(namesWithBettingMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Map<String, Integer>> generateStreamWithInvalidCount() {
        Map<String, Integer> overRangeParticipants = Map.of(
                "pobi", 5_000, "jason", 10_000, "joon", 20_000, "jobi", 10_000,
                "sobi", 20_000, "wobi", 10_000, "fobi", 50_000, "kobi", 30_000
        );
        Map<String, Integer> underRangeParticipants = new HashMap<>();
        return Stream.of(overRangeParticipants, underRangeParticipants);
    }

    @DisplayName("참가자 등록: 플레이어의 베팅 금액이 유효하지 않을 경우 예외 발생")
    @ParameterizedTest
    @MethodSource("generateStreamWithInvalidBettingMoney")
    void enrollWithInvalidBettingMoney(Map<String, Integer> namesWithBettingMoney) {
        BlackJackGame blackJackGame = new BlackJackGame();

        assertThatThrownBy(() -> blackJackGame.enrollPlayers(namesWithBettingMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Map<String, Integer>> generateStreamWithInvalidBettingMoney() {
        Map<String, Integer> overRangeBettingMoney = Map.of("pobi", 10_000_001);
        Map<String, Integer> underRangeBettingMoney = Map.of("pobi", 4_999);
        return Stream.of(overRangeBettingMoney, underRangeBettingMoney);
    }

    @DisplayName("사용자 카드 지급: 전달받은 인덱스 번호가 유효하지 않을 경우 예외 발생")
    @Test
    void addCardWithIndexOutOfBounds() {
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.enrollPlayers(Map.of("pobi", 10_000, "jason", 20_000));

        assertThatThrownBy(() -> blackJackGame.addCardToPlayer(2)).isInstanceOf(IndexOutOfBoundsException.class);
    }
}