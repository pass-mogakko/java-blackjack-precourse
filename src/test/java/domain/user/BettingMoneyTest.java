package domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingMoneyTest {

    @Test
    void 플레이어의_베팅_금액_파싱하기() {
        String input = "3000";

        BettingMoney bettingMoney = new BettingMoney(input);
        int money = bettingMoney.get();

        Assertions.assertThat(money)
                .isEqualTo(3000);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "a", "1a", "0", "-1"})
    void 베팅_금액이_비정상적인_입력인_경우(String input) {
        Assertions.assertThatThrownBy(() ->  new BettingMoney(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}