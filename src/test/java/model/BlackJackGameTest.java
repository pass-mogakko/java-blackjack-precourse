package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.user.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

class BlackJackGameTest {

    @DisplayName("참가자 등록: 참여 인원이 유효하지 않을 경우 예외 발생")
    @ParameterizedTest
    @MethodSource("generateStreamWithFullGame")
    void enrollWithFullGame(BlackJackGame fullGame) {
        assertThatThrownBy(() -> fullGame.enrollPlayer("testPlayer8", 10_000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> generateStreamWithFullGame() {
        BlackJackGame fullGame = new BlackJackGame();
        for (int index = 1; index < 8; index++) {
            fullGame.enrollPlayer("testPlayer" + index, 10_000);
        }
        return Stream.of(Arguments.of(fullGame));
    }

    @DisplayName("참가자 등록: 플레이어의 베팅 금액이 유효하지 않을 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(doubles = {4_999, 10_000_001})
    void enrollWithInvalidBettingMoney(double invalidBettingMoney) {
        BlackJackGame blackJackGame = new BlackJackGame();

        assertThatThrownBy(() -> blackJackGame.enrollPlayer("pobi", invalidBettingMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("참가자 등록: 이미 동일한 이름의 플레이어가 존재하는 경우 예외 발생")
    @Test
    void enrollWithDuplicatedPlayer() {
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.enrollPlayer("pobi", 10_000);

        assertThatThrownBy(() -> blackJackGame.enrollPlayer("pobi", 20_000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("사용자 카드 지급: 전달받은 인덱스 번호가 유효하지 않을 경우 예외 발생")
    @Test
    void addCardWithIndexOutOfBounds() {
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.enrollPlayer("pobi", 10_000);

        assertThatThrownBy(() -> blackJackGame.addCardToPlayer(1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("카드 오픈: 딜러 카드 전체공개 여부가 참이면 딜러의 카드를 모두 공개")
    @ParameterizedTest
    @MethodSource("generateStreamWithStartedGame")
    void openAllCards(BlackJackGame startedGame) {
        OpenedCardsDto openedCards = startedGame.openAllUserCards(true);
        Dealer dealer = openedCards.getDealer();
        assertThat(dealer.getCards()).hasSize(2);
    }

    @DisplayName("카드 오픈: 딜러 카드 전체공개 여부가 거짓이면 딜러의 카드 중 첫번째 카드만 공개")
    @ParameterizedTest
    @MethodSource("generateStreamWithStartedGame")
    void openCardsExcept2ndDealerCard(BlackJackGame startedGame) {
        OpenedCardsDto openedCards = startedGame.openAllUserCards(false);
        Dealer dealer = openedCards.getDealer();
        assertThat(dealer.getCards()).hasSize(1);
    }

    private static Stream<Arguments> generateStreamWithStartedGame() {
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.enrollPlayer("testPlayer", 10_000);
        blackJackGame.distributeCardsForStart();
        return Stream.of(Arguments.of(blackJackGame));
    }
}