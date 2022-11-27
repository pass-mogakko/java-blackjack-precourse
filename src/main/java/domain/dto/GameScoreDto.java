package domain.dto;

import java.util.List;

public class GameScoreDto {
    private List<Integer> playerScores;
    private int dealerScore;

    public GameScoreDto(List<Integer> playerScores, int dealerScore) {
        this.playerScores = playerScores;
        this.dealerScore = dealerScore;
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public Integer getPlayerScore(int idx) {
        return playerScores.get(idx);
    }
}
