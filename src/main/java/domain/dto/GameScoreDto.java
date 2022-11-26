package domain.dto;

import java.util.Map;

public class GameScoreDto {
    private Map<String, Integer> playerScores;
    private int dealerScore;

    public GameScoreDto(Map<String, Integer> playerScores, int dealerScore) {
        this.playerScores = playerScores;
        this.dealerScore = dealerScore;
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public Map<String, Integer> getPlayerScores() {
        return playerScores;
    }

    public Integer getPlayerScore(String playerName) {
        return playerScores.get(playerName);
    }
}
