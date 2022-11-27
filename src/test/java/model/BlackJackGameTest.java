package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Deck;
import domain.user.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;
import java.util.stream.Stream;

class BlackJackGameTest {

    @DisplayName("참가자 등록: 참여 인원이 유효하지 않을 경우 예외 발생")
    @ParameterizedTest
    @MethodSource("generateStreamWithFullGame")
    void enrollWithFullGame(Map<String, Double> players) {
        assertThatThrownBy(() -> new BlackJackGame(new Deck(), players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> generateStreamWithFullGame() {
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
        assertThatThrownBy(() -> new BlackJackGame(new Deck(), Map.of("pobi", invalidBettingMoney)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("사용자 카드 지급: 전달받은 사용자 이름이 유효하지 않을 경우 예외 발생")
    @Test
    void addCardWithIndexOutOfBounds() {
        BlackJackGame blackJackGame = new BlackJackGame(new Deck(), Map.of("pobi", 10_000.0));

        assertThatThrownBy(() -> blackJackGame.addCardToPlayer("wrong")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("딜러 카드 추가: 이미 가지고 있는 카드의 합이 16 이하이면 추가, 17 이상이면 추가하지 않음")
    @ParameterizedTest
    @MethodSource("generateStreamWithStartedGame")
    void addCardToDealerIfValid(BlackJackGame startedGame) {
        OpenedCardsDto openedCards = startedGame.openAllUserCards(true);
        Dealer dealer = openedCards.getDealer();
        boolean expected = (dealer.addAllScore() < 17);
        boolean hasAdded = startedGame.addCardToDealerIfValid();

        assertThat(hasAdded).isEqualTo(expected);
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
        BlackJackGame blackJackGame = new BlackJackGame(new Deck(), Map.of("testPlayer", 10_000.0));
        blackJackGame.distributeCardsForStart();
        return Stream.of(Arguments.of(blackJackGame));
    }
}