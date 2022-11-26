package domain.dto;

import java.util.List;

public class BenefitResultDto {

    private final int dealerBenefit;
    private final List<PlayerBenefitResultDto> playerBenefitResultDtos;

    public BenefitResultDto(int dealerBenefit, List<PlayerBenefitResultDto> playerBenefitResultDtos) {
        this.dealerBenefit = dealerBenefit;
        this.playerBenefitResultDtos = playerBenefitResultDtos;
    }

    public int getDealerBenefit() {
        return dealerBenefit;
    }

    public List<PlayerBenefitResultDto> getPlayerBenefitResultDtos() {
        return playerBenefitResultDtos;
    }
}
