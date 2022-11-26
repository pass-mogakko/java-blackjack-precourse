package domain.user;

import domain.constant.Constant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersName {

    private final List<String> playersName;

    public PlayersName(String playersName) {
        this.playersName = Arrays.stream(playersName.split(Constant.PLAYERS_NAME_SPLIT_DELIMITER))
                .collect(Collectors.toList());
    }

    public List<String> get() {
        return playersName;
    }
}
