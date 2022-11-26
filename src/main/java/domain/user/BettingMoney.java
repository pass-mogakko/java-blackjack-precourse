package domain.user;

public class BettingMoney {

    private final int money;

    public BettingMoney(String money) {
        this.money = Integer.parseInt(money);
    }


    public int get() {
        return money;
    }
}
