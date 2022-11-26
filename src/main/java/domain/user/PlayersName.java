package domain.user;

import constant.Constant;
import constant.ErrorMessage;
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
        List<String> parsedPlayersName = Arrays.stream(playersName.split(Constant.PLAYERS_NAME_SPLIT_DELIMITER))
                .collect(Collectors.toList());
        parsedPlayersName.forEach(this::validatePlayerName);
    }

    private void validatePlayerName(String playerName) {
        if (Pattern.matches(Constant.ONLY_SPACE_REGEX, playerName)) {
            throw new IllegalArgumentException(ErrorMessage.WRONG_PLAYER_NAME_FORM);
        }
    }

    public List<String> get() {
        return playersName;
    }
}
