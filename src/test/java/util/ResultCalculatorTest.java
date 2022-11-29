package util;

import static org.assertj.core.api.Assertions.*;

import domain.status.GameResult;
import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.calculator.ResultCalculator;

class ResultCalculatorTest {

    public List<Card> getCards(Symbol...symbols) {
        return Arrays.stream(symbols)
                .map(symbol -> new Card(symbol, Type.SPADE))
                .collect(Collectors.toList());
    }

    @DisplayName("플레이어가 승리하는 경우")
    @Test
    void playerWin() {
        List<Card> dealerCards = getCards(Symbol.FIVE, Symbol.JACK);
        List<Card> playerCards = getCards(Symbol.KING, Symbol.TEN);

        GameResult result = ResultCalculator.compare(dealerCards, playerCards);

        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어가 패배하는 경우")
    @Test
    void playerLose() {
        List<Card> dealerCards = getCards(Symbol.KING, Symbol.TEN);
        List<Card> playerCards = getCards(Symbol.FIVE, Symbol.JACK);

        GameResult result = ResultCalculator.compare(dealerCards, playerCards);

        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("딜러와 플레이어가 무승부하는 경우")
    @Test
    void playerDraw() {
        List<Card> dealerCards = getCards(Symbol.KING, Symbol.TEN);
        List<Card> playerCards = getCards(Symbol.KING, Symbol.TEN);

        GameResult result = ResultCalculator.compare(dealerCards, playerCards);

        assertThat(result).isEqualTo(GameResult.DRAW);
    }

}