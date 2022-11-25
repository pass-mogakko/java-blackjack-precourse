package dto;

public class BettingMoneyDto {

    private final int money;

    public BettingMoneyDto(int money) {
        this.money = money;
    }

    public int get() {
        return money;
    }
}
