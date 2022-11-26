package domain.card;


import constant.GameResult;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    void ACE_1장_점수_계산하기() {
        Card ace = new Card(Symbol.ACE);
        Cards cards = new Cards(List.of(ace));

        int score = cards.computeScore();

        Assertions.assertThat(score)
                .isEqualTo(11);
    }

    @Test
    void ACE_2장_점수_계산하기() {
        Card ace = new Card(Symbol.ACE);
        Cards cards = new Cards(List.of(ace, ace));

        int score = cards.computeScore();

        Assertions.assertThat(score)
                .isEqualTo(12);
    }

    @Test
    void ACE_3장_점수_계산하기() {
        Card ace = new Card(Symbol.ACE);
        Cards cards = new Cards(List.of(ace, ace, ace));

        int score = cards.computeScore();

        Assertions.assertThat(score)
                .isEqualTo(13);
    }

    @Test
    void ACE_1장_KING_1장_점수_계산하기() {
        Card ace = new Card(Symbol.ACE);
        Card king = new Card(Symbol.KING);
        Cards cards = new Cards(List.of(ace, king));

        int score = cards.computeScore();

        Assertions.assertThat(score)
                .isEqualTo(21);
    }

    @Test
    void ACE_1장_KING_2장_점수_계산하기() {
        Card ace = new Card(Symbol.ACE);
        Card king = new Card(Symbol.KING);
        Cards cards = new Cards(List.of(ace, king, king));

        int score = cards.computeScore();

        Assertions.assertThat(score)
                .isEqualTo(21);
    }

    @Test
    void ACE_2장_KING_2장_점수_계산하기() {
        Card ace = new Card(Symbol.ACE);
        Card king = new Card(Symbol.KING);
        Cards cards = new Cards(List.of(ace, ace, king, king));

        int score = cards.computeScore();

        Assertions.assertThat(score)
                .isEqualTo(22);
    }

    @Test
    void 모든_종류의_카드_1장씩_점수_계산하기() {
        List<Card> allCards = Arrays.stream(Symbol.values())
                .map(Card::new)
                .collect(Collectors.toList());
        Cards cards = new Cards(allCards);

        int score = cards.computeScore();

        Assertions.assertThat(score)
                .isEqualTo(85);
    }

    @Test
    void 플레이어카드_블랙잭_딜러카드_블랙잭_결과계산() {
        Card ace = new Card(Symbol.ACE);
        Card king = new Card(Symbol.KING);
        Cards playerCards = new Cards(List.of(ace, king));
        Cards dealerCards = new Cards(List.of(ace, king));

        GameResult gameResult = playerCards.computeGameResult(dealerCards);

        Assertions.assertThat(gameResult)
                .isEqualTo(GameResult.DRAW);
    }

    @Test
    void 플레이어카드_블랙잭_딜러카드_21_결과계산() {
        Card ace = new Card(Symbol.ACE);
        Card king = new Card(Symbol.KING);
        Cards playerCards = new Cards(List.of(ace, king));
        Cards dealerCards = new Cards(List.of(king, king, ace));

        GameResult gameResult = playerCards.computeGameResult(dealerCards);

        Assertions.assertThat(gameResult)
                .isEqualTo(GameResult.BLACKJACK);
    }

    @Test
    void 플레이어카드_블랙잭_딜러카드_20_결과계산() {
        Card ace = new Card(Symbol.ACE);
        Card king = new Card(Symbol.KING);
        Cards playerCards = new Cards(List.of(ace, king));
        Cards dealerCards = new Cards(List.of(king, king));

        GameResult gameResult = playerCards.computeGameResult(dealerCards);

        Assertions.assertThat(gameResult)
                .isEqualTo(GameResult.BLACKJACK);
    }

    @Test
    void 플레이어카드_블랙잭_딜러카드_30_결과계산() {
        Card ace = new Card(Symbol.ACE);
        Card king = new Card(Symbol.KING);
        Cards playerCards = new Cards(List.of(ace, king));
        Cards dealerCards = new Cards(List.of(king, king, king));

        GameResult gameResult = playerCards.computeGameResult(dealerCards);

        Assertions.assertThat(gameResult)
                .isEqualTo(GameResult.BLACKJACK);
    }

    @Test
    void 플레이어카드_20_딜러카드_30_결과계산() {
        Card ace = new Card(Symbol.ACE);
        Card king = new Card(Symbol.KING);
        Cards playerCards = new Cards(List.of(king, king));
        Cards dealerCards = new Cards(List.of(king, king, king));

        GameResult gameResult = playerCards.computeGameResult(dealerCards);

        Assertions.assertThat(gameResult)
                .isEqualTo(GameResult.WIN);
    }

    @Test
    void 플레이어카드_20_딜러카드_블랙잭_결과계산() {
        Card ace = new Card(Symbol.ACE);
        Card king = new Card(Symbol.KING);
        Cards playerCards = new Cards(List.of(king, king));
        Cards dealerCards = new Cards(List.of(ace, king));

        GameResult gameResult = playerCards.computeGameResult(dealerCards);

        Assertions.assertThat(gameResult)
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어카드_20_딜러카드_21_결과계산() {
        Card ace = new Card(Symbol.ACE);
        Card king = new Card(Symbol.KING);
        Cards playerCards = new Cards(List.of(king, king));
        Cards dealerCards = new Cards(List.of(king, king, ace));

        GameResult gameResult = playerCards.computeGameResult(dealerCards);

        Assertions.assertThat(gameResult)
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어카드_20_딜러카드_12_결과계산() {
        Card ace = new Card(Symbol.ACE);
        Card king = new Card(Symbol.KING);
        Cards playerCards = new Cards(List.of(king, king));
        Cards dealerCards = new Cards(List.of(ace, ace));

        GameResult gameResult = playerCards.computeGameResult(dealerCards);

        Assertions.assertThat(gameResult)
                .isEqualTo(GameResult.WIN);
    }
}