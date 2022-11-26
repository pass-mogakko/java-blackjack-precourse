package domain.user;

import domain.constant.Constant;
import domain.constant.GameResult;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {

    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    // TODO 추가 기능 구현

    public boolean isPossibleDrawCard() {
        return cards.isLessThan21();
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }

    public double computeBenefit(User dealer) {
        GameResult gameResult = cards.computeGameResult(dealer.cards);
        if (gameResult == GameResult.BLACKJACK) {
            return bettingMoney * Constant.BLACKJACK_BONUS_RATE;
        }
        if (gameResult == GameResult.WIN) {
            return bettingMoney;
        }
        if (gameResult == GameResult.LOSE) {
            return -bettingMoney;
        }
        return 0;
    }

    public String getName() {
        return name;
    }
}
