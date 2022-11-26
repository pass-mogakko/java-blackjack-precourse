package domain.dto;

import java.util.List;

public class GameResultDto {

    private final List<String> dealerHasCards;
    private final int dealerScore;
    private final List<PlayerCardsToStringDto> playerCardsToStringDtos;
    private final int dealerBenefit;
    private final List<PlayerBenefitResultDto> playerBenefitResultDtos;

    public GameResultDto(List<String> dealerHasCards, int dealerScore, List<PlayerCardsToStringDto> playerCardsToStringDtos, int dealerBenefit, List<PlayerBenefitResultDto> playerBenefitResultDtos) {
        this.dealerHasCards = dealerHasCards;
        this.dealerScore = dealerScore;
        this.playerCardsToStringDtos = playerCardsToStringDtos;
        this.dealerBenefit = dealerBenefit;
        this.playerBenefitResultDtos = playerBenefitResultDtos;
    }

    public List<String> getDealerHasCards() {
        return dealerHasCards;
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public List<PlayerCardsToStringDto> getPlayerCardsToStringDtos() {
        return playerCardsToStringDtos;
    }

    public int getDealerBenefit() {
        return dealerBenefit;
    }

    public List<PlayerBenefitResultDto> getPlayerBenefitResultDtos() {
        return playerBenefitResultDtos;
    }

}
