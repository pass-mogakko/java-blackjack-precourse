package util.calculator;

import static org.assertj.core.api.Assertions.*;

import domain.status.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitCalculatorTest {

    @DisplayName("승리일 경우 수익을 베팅액만큼 획득")
    @Test
    void testWinProfit() {
        int profit = ProfitCalculator.calculateProfit(10000, GameResult.WIN);

        assertThat(profit).isEqualTo(10000);
    }

    @DisplayName("무승부일 경우 수익 없음")
    @Test
    void testDrawProfit() {
        int profit = ProfitCalculator.calculateProfit(10000, GameResult.DRAW);

        assertThat(profit).isEqualTo(0);
    }
    @DisplayName("패배일 경우 베팅액 잃음")
    @Test
    void testLoseProfit() {
        int profit = ProfitCalculator.calculateProfit(10000, GameResult.LOSE);

        assertThat(profit).isEqualTo(-10000);
    }

}