package domain.dto;

public class PlayerBenefitResultDto {

    private final String name;
    private final int playerBenefit;

    public PlayerBenefitResultDto(String name, int playerBenefit) {
        this.name = name;
        this.playerBenefit = playerBenefit;
    }

    public String getName() {
        return name;
    }

    public int getPlayerBenefit() {
        return playerBenefit;
    }
}
