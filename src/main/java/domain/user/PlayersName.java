package domain.user;

import domain.constant.Constant;
import domain.constant.ErrorMessage;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PlayersName {

    private final List<String> playersName;

    public PlayersName(String playersName) {
        validate(playersName);
        this.playersName = Arrays.stream(playersName.split(Constant.PLAYERS_NAME_SPLIT_DELIMITER))
                .collect(Collectors.toList());
    }

    private void validate(String playersName) {
        if (Pattern.matches(Constant.ONLY_SPACE_REGEX, playersName)) {
            throw new IllegalArgumentException(ErrorMessage.WRONG_PLAYER_NAME);
        }
    }

    public List<String> get() {
        return playersName;
    }
}
