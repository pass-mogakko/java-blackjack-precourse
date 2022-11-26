package dto;

import java.util.List;

public class PlayerHasCardDto {

    private final List<String> playersHasCard;

    public PlayerHasCardDto(List<String> playersHasCard) {
        this.playersHasCard = playersHasCard;
    }

    public List<String> getPlayersHasCard() {
        return playersHasCard;
    }
}
