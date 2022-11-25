package domain.user;

import dto.BettingMoneyDto;

public class BettingMoney {

    private final int money;

    public BettingMoney(String money) {
        this.money = Integer.parseInt(money);
    }

    public BettingMoneyDto toDto() {
        return new BettingMoneyDto(money);
    }
}
