package domain.user;

import model.validator.GameRuleValidator;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {

    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        GameRuleValidator.validateInitialBettingMoney(bettingMoney);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return name.equals(player.name);
    }
}
