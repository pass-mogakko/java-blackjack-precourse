package domain.user;


import dto.PlayersNameDto;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayersNameTest {

    @Test
    void 플레이어의_이름_입력값_파싱하기() {
        String input = "가,나다,라마바,사아자차";

        PlayersName playersName = new PlayersName(input);
        PlayersNameDto playersNameDto = playersName.toDto();
        List<String> names = playersNameDto.get();

        Assertions.assertThat(names)
                .containsExactly("가", "나다", "라마바", "사아자차");
    }
}