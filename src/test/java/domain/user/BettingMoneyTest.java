package domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {

    @Test
    void 플레이어의_베팅_금액_파싱하기() {
        String input = "3000";

        BettingMoney bettingMoney = new BettingMoney(input);
        int money = bettingMoney.get();

        Assertions.assertThat(money)
                .isEqualTo(3000);
    }
}