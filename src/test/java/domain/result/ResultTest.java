package domain.result;

import static org.junit.jupiter.api.Assertions.*;

import domain.status.GameResult;
import domain.user.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {
    
    @DisplayName("플레이어가 이미 결과를 보유하고 있는 경우")
    @Test
    void alreadyHasResult() {
        Player player = new Player("test",10000);
        Result result = new Result();

        result.putResult(player, GameResult.WIN);

        Assertions.assertThat(result.hasResultByPlayer(player)).isEqualTo(true);
    }

    @DisplayName("플레이어가 결과를 보유하고 있지 않는 경우")
    @Test
    void alreadyNoHasResult() {
        Player player = new Player("test",10000);
        Result result = new Result();

        Assertions.assertThat(result.hasResultByPlayer(player)).isEqualTo(false);
    }

}
