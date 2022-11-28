package model;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Deck;
import model.dto.OpenedCards;
import model.dto.Opening;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

class BlackJackGameTest {

    @DisplayName("딜러 카드 추가: 이미 가지고 있는 카드의 합이 16 이하이면 추가, 17 이상이면 추가하지 않음")
    @ParameterizedTest
    @MethodSource("generateStreamWithStartedGame")
    void addCardToDealerIfValid(BlackJackGame startedGame) {
        Opening opening = startedGame.open(true);
        OpenedCards dealerOpening = opening.getDealerCards();
        boolean expected = (dealerOpening.getScore() < 17);
        boolean hasAdded = startedGame.hitDealer();

        assertThat(hasAdded).isEqualTo(expected);
    }

    @DisplayName("카드 오픈: 딜러 카드 전체공개 여부가 참이면 딜러의 카드를 모두 공개")
    @ParameterizedTest
    @MethodSource("generateStreamWithStartedGame")
    void openAllCards(BlackJackGame startedGame) {
        Opening opening = startedGame.open(true);
        OpenedCards dealerOpening = opening.getDealerCards();

        assertThat(dealerOpening.getCards()).hasSize(2);
    }

    @DisplayName("카드 오픈: 딜러 카드 전체공개 여부가 거짓이면 딜러의 카드 중 첫번째 카드만 공개")
    @ParameterizedTest
    @MethodSource("generateStreamWithStartedGame")
    void openCardsExcept2ndDealerCard(BlackJackGame startedGame) {
        Opening opening = startedGame.open(false);
        OpenedCards dealerOpening = opening.getDealerCards();

        assertThat(dealerOpening.getCards()).hasSize(1);
    }

    private static Stream<Arguments> generateStreamWithStartedGame() {
        BlackJackGame blackJackGame = new BlackJackGame(new Deck(), Map.of("testPlayer", 10_000.0));
        blackJackGame.deal();
        return Stream.of(Arguments.of(blackJackGame));
    }
}