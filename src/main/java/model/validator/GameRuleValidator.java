package model.validator;

import static domain.rule.GameRule.INITIAL_BETTING_MAX_VALUE;
import static domain.rule.GameRule.INITIAL_BETTING_MIN_VALUE;
import static domain.rule.GameRule.PARTICIPANTS_MAX_COUNT;
import static domain.rule.GameRule.PARTICIPANTS_MIN_COUNT;

public class GameRuleValidator {

    private GameRuleValidator() { }

    public static void validateParticipantsCount(int participantsCount) {
        if (participantsCount < PARTICIPANTS_MIN_COUNT.getValue()) {
            throw new IllegalArgumentException("참여 가능 인원은 최소 " + PARTICIPANTS_MIN_COUNT.getValue() + "명입니다.");
        }
        if (participantsCount > PARTICIPANTS_MAX_COUNT.getValue()) {
            throw new IllegalArgumentException("참여 가능 인원은 최대 " + PARTICIPANTS_MAX_COUNT.getValue() + "명입니다.");
        }
    }

    public static void validateInitialBettingMoney(double bettingMoney) {
        if (bettingMoney < INITIAL_BETTING_MIN_VALUE.getValue()) {
            throw new IllegalArgumentException("베팅 가능 금액은 최소 " + INITIAL_BETTING_MIN_VALUE.getValue() + "원입니다.");
        }
        if (bettingMoney > INITIAL_BETTING_MAX_VALUE.getValue()) {
            throw new IllegalArgumentException("베팅 가능 금액은 최대 " + INITIAL_BETTING_MAX_VALUE.getValue() + "원입니다.");
        }
    }
}
