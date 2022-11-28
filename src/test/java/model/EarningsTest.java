package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import model.dto.Earning;
import model.dto.Earnings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

class EarningsTest {

    @DisplayName("수익 정보 클래스 생성: 전달받은 사용자 이름 리스트가 null 또는 비어있을 경우 예외 발생")
    @ParameterizedTest
    @NullAndEmptySource
    void createEarnings(List<String> playerNames) {
        assertThatThrownBy(() -> new Earnings(playerNames))
                .isInstanceOfAny(NullPointerException.class, IllegalArgumentException.class);
    }

    @DisplayName("수익 업데이트: 존재하지 않는 이름의 사용자 수익 조회 시 예외 발생")
    @Test
    void findPlayerEarningByName() {
        Earnings earnings = new Earnings(List.of("pobi", "jason", "joon"));

        assertThatThrownBy(() -> earnings.moveEarningFromDealerToPlayer("wrong", 1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수익 업데이트: 특정 금액을 사용자 수익에는 더하고, 딜러 수익에는 빼도록 계산")
    @ParameterizedTest
    @ValueSource(doubles = {10_000, -5_000, -1_000_000})
    void updateEarning(double amount) {
        Earnings earnings = new Earnings(List.of("pobi", "jason", "joon"));
        earnings.moveEarningFromDealerToPlayer("pobi", amount);
        Earning dealerEarning = earnings.getDealerEarning();

        assertThat(dealerEarning.getValue()).isEqualTo((-1)*amount);
    }
}