package dto;

import java.util.List;

public class PlayersNameDto {

    private final List<String> playersName;

    public PlayersNameDto(List<String> playersName) {
        this.playersName = playersName;
    }

    public List<String> get() {
        return playersName;
    }
}
