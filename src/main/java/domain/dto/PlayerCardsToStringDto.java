package domain.dto;

import java.util.List;

public class PlayerCardsToStringDto {

    private final String name;
    private final List<String> playerHasCards;
    private final int score;

    public PlayerCardsToStringDto(String name, List<String> playerHasCards) {
        this.name = name;
        this.playerHasCards = playerHasCards;
        score = 0;
    }

    public PlayerCardsToStringDto(String name, List<String> playerHasCards, int score) {
        this.name = name;
        this.playerHasCards = playerHasCards;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public List<String> getPlayerHasCards() {
        return playerHasCards;
    }

    public int getScore() {
        return score;
    }
}
