package domain.user;


import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayersNameTest {

    @Test
    void 플레이어의_이름_입력값_파싱하기() {
        String input = "가,나다,라마바,사아자차";

        PlayersName playersName = new PlayersName(input);
        List<String> names = playersName.get();

        Assertions.assertThat(names)
                .containsExactly("가", "나다", "라마바", "사아자차");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void 플레이어의_이름이_비정상적인_입력인_경우(String input) {
        Assertions.assertThatThrownBy(() -> new PlayersName(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}