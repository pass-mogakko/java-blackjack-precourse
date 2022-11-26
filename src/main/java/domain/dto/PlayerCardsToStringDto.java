package domain.dto;

import java.util.List;

public class PlayerCardsToStringDto {

    private final String name;
    private final List<String> playerHasCards;

    public PlayerCardsToStringDto(String name, List<String> playerHasCards) {
        this.name = name;
        this.playerHasCards = playerHasCards;
    }

    public String getName() {
        return name;
    }

    public List<String> getPlayerHasCards() {
        return playerHasCards;
    }
}
