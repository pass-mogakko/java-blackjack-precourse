package domain.user;

import domain.constant.Constant;
import domain.constant.ErrorMessage;
import java.util.regex.Pattern;

public class BettingMoney {

    private final int money;

    public BettingMoney(String money) {
        validate(money);
        this.money = Integer.parseInt(money);
    }

    public void validate(String money) {
        if (!Pattern.matches(Constant.BETTING_MONEY_REGEX, money)) {
            throw new IllegalArgumentException(ErrorMessage.WRONG_BETTING_MONEY_FORM);
        }
        if (Integer.parseInt(money) == 0) {
            throw new IllegalArgumentException(ErrorMessage.BETTING_MONEY_ZERO);
        }
    }

    public int get() {
        return money;
    }
}
