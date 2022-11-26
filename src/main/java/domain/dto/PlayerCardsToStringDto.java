package domain.dto;

import java.util.List;

public class PlayerCardsToStringDto {

    private final String name;
    private final List<String> playerHasCards;
    private final int score;
    private final boolean isPossibleDrawCard;

    public PlayerCardsToStringDto(String name, List<String> playerHasCards, boolean isPossibleDrawCard) {
        this.name = name;
        this.playerHasCards = playerHasCards;
        this.score = 0;
        this.isPossibleDrawCard = isPossibleDrawCard;
    }

    public PlayerCardsToStringDto(String name, List<String> playerHasCards, int score) {
        this.name = name;
        this.playerHasCards = playerHasCards;
        this.score = score;
        isPossibleDrawCard = false;
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

    public boolean isPossibleDrawCard() {
        return isPossibleDrawCard;
    }
}
